package com.sentinel.stream.core;

import com.sentinel.stream.model.DataPacket;
import com.sentinel.stream.service.ScrubberService;
import com.sentinel.stream.util.PacketValidator;
import org.springframework.stereotype.Service;
import com.sentinel.stream.model.PacketEntity;
import com.sentinel.stream.repository.PacketRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class StreamEngine {

    private final ScrubberService scrubberService;
    private final PacketRepository packetRepository;
    // This executor creates a new Virtual Thread for every single task.
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public StreamEngine(ScrubberService scrubberService, PacketRepository packetRepository) {
        this.scrubberService = scrubberService;
        this.packetRepository = packetRepository;
    }

    public void process(DataPacket packet) {
        // Step 1: Validate before wasting resources (Fail Fast)
        PacketValidator.validate(packet);

        // Step 2: Submit to a Virtual Thread for parallel processing
        executor.submit(() -> {
            try {
                DataPacket cleanData = scrubberService.scrub(packet);


                // Map the Record to the Entity for Persistence
                PacketEntity entity = PacketEntity.builder()
                    .id(cleanData.id())
                    .cleanPayload(cleanData.payload())
                    .origin(cleanData.source())
                    .processedAt(cleanData.createdAt())
                    .build();

                // Save to Postgres
                packetRepository.save(entity);
                System.out.println("Saved Packet ID: " + entity.getId());

            } catch (Exception e) {
                System.err.println("Persistence Error:" + e.getMessage());
            }
        });
    }
}