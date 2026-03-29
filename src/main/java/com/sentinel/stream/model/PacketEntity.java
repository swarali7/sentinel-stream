package com.sentinel.stream.model;

import jakarta.persistence.*;
import lombok.*; // This imports @Getter, @Setter, @Builder, etc.
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "processed_packets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // This provides the .builder() method
public class PacketEntity {
    @Id
    private UUID id;
    
    @Column(columnDefinition = "TEXT")
    private String cleanPayload;
    
    private String origin;
    private Instant processedAt;
}