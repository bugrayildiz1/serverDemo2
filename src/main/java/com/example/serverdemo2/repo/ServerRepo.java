package com.example.serverdemo2.repo;

import com.example.serverdemo2.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);

}
