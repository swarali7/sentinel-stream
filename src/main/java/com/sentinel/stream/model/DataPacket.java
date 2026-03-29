package com.sentinel.stream.model;

import java.time.Instant;
import java.util.UUID;

public record DataPacket(
    UUID id,                //avoids leaking internal sequence information
    String payload,
    String source,
    Instant createdAt,
    boolean isEncrypted
) {}