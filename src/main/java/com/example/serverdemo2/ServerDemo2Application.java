package com.example.serverdemo2;

import com.example.serverdemo2.model.Server;
import com.example.serverdemo2.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.serverdemo2.enumaration.Status.SERVER_UP;

@SpringBootApplication
public class ServerDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(ServerDemo2Application.class, args);
    }


    @Bean
    CommandLineRunner run(ServerRepo serverRepo) {
        return args -> {
            serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linuxz", "16GB", "Personal Computer"
                    ,"http://localhost:8080/server/image/server1.png", SERVER_UP ));
            serverRepo.save(new Server(null, "192.168.1.50", "Boontoo", "31GB", "Personal Computer"
                    ,"http://localhost:8080/server/image/server1.png", SERVER_UP ));
            serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "69GB", "Personal Computer"
                    ,"http://localhost:8080/server/image/server1.png", SERVER_UP ));
            serverRepo.save(new Server(null, "192.168.1.24", "KALI CANDIR", "150GB", "Personal Computer"
                    ,"http://localhost:8080/server/image/server1.png", SERVER_UP ));
        };
    }

}
