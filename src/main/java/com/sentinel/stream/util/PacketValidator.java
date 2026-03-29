package com.sentinel.stream.util;

import com.sentinel.stream.model.DataPacket;

public class PacketValidator {

    public static void validate(DataPacket packet){
        if (packet == null) {
            throw new IllegalArgumentException("Packet cannot be null");
        }

        if (packet.payload() == null || packet.payload().isBlank()) {
            throw new IllegalArgumentException("Payload cannot be null or blank");
        }
        if (packet.id() == null)
        {
            throw new IllegalArgumentException("Packet must be a valid UUID");
        }
    }

}
