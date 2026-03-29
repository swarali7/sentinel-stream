package com.sentinel.stream.service.impl;

import com.sentinel.stream.model.DataPacket;
import com.sentinel.stream.service.ScrubberService;
import com.sentinel.stream.util.PrivacyVault;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class BasicScrubberService implements ScrubberService {

    @Override
    public DataPacket scrub(DataPacket packet) {
        // Remove anything that isn't a letter, number, or space
        String sanitizedPayload = packet.payload().replaceAll("[^a-zA-Z0-9 ]", "");
        
        // Return a NEW record (Immutable pattern)
        return new DataPacket(
            packet.id(),
            sanitizedPayload,
            packet.source(),
            Instant.now(), // Update timestamp to 'processed' time
            true           // Mark as encrypted/processed
        );
    }
}