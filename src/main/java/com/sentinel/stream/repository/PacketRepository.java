package com.sentinel.stream.repository;

import com.sentinel.stream.model.PacketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PacketRepository extends JpaRepository<PacketEntity, UUID> {
    // Standard CRUD methods (save, findById, etc.) are automatically included.
}