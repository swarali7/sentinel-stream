package com.sentinel.stream.core;

import com.sentinel.stream.model.DataPacket;
import com.sentinel.stream.service.ScrubberService;
import com.sentinel.stream.util.PacketValidator;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class StreamEngine {

    private final ScrubberService scrubberService;
    // This executor creates a new Virtual Thread for every single task.
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public StreamEngine(ScrubberService scrubberService) {
        this.scrubberService = scrubberService;
    }

    public void process(DataPacket packet) {
        // Step 1: Validate before wasting resources (Fail Fast)
        PacketValidator.validate(packet);

        // Step 2: Submit to a Virtual Thread for parallel processing
        executor.submit(() -> {
            try {
                DataPacket cleanPacket = scrubberService.scrub(packet);
                System.out.println("Processed Packet ID: " + cleanPacket.id());
            } catch (Exception e) {
                System.err.println("Error processing packet: " + e.getMessage());
            }
        });
    }
}