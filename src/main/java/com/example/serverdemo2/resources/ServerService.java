package com.example.serverdemo2.resources;

import com.example.serverdemo2.model.Server;
import org.springframework.expression.spel.support.BooleanTypedValue;

import java.io.IOException;
import java.util.Collection;

//all the different functionalities that application have are defined
public interface ServerService {

    //i want to be able to save a server in a database
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;

    //list all the servers
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    BooleanTypedValue delete(Long id);

}
