package com.example.serverdemo2.service.implementation;

import com.example.serverdemo2.enumaration.Status;
import com.example.serverdemo2.model.Server;
import com.example.serverdemo2.repo.ServerRepo;
import com.example.serverdemo2.resources.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.spel.support.BooleanTypedValue;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.example.serverdemo2.enumaration.Status.SERVER_DOWN;
import static com.example.serverdemo2.enumaration.Status.SERVER_UP;
import static org.springframework.expression.spel.support.BooleanTypedValue.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;

    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers}");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();

    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public BooleanTypedValue delete(Long id) {
        log.info("Deleting server: {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = { "server1.png, server2.png, server3.png, server4.png "};

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
