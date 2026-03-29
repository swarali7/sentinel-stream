package com.sentinel.stream.api;

import com.sentinel.stream.core.StreamEngine;
import com.sentinel.stream.model.DataPacket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

/**
 * Handles incoming HTTP traffic.
 * Decouples the API request from the heavy processing logic.
 */
@RestController
@RequestMapping("/api/v1/stream")
public class IngestionController {

    private final StreamEngine streamEngine;

    public IngestionController(StreamEngine streamEngine) {
        this.streamEngine = streamEngine;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingest(@RequestBody String payload) {
        // Step 1: Wrap raw data into our Immutable Model
        DataPacket packet = new DataPacket(
            UUID.randomUUID(),
            payload,
            "REST_API",
            Instant.now(),
            false
        );

        // Step 2: Hand off to the Engine (Non-blocking)
        streamEngine.process(packet);

        // Step 3: Respond immediately 
        return ResponseEntity.accepted().body("Packet accepted for processing: " + packet.id());
    }
}