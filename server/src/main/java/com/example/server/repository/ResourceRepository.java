package com.example.server.repository;

import com.example.server.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, String> {
    List<Resource> findByTypeContainingIgnoreCase(String type);
    List<Resource> findByLocationContainingIgnoreCase(String location);
}
