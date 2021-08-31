package com.datasphera.drools.Services;

import java.util.HashMap;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

@Service
@Scope("singleton")

public class UserService {
    HashMap<String, KieFileSystem> Users = new HashMap<String, KieFileSystem>();

    public void addUser(String user) {
        // create the kfs instantanly

        KieFileSystem kfs = kieServices().newKieFileSystem();
        Users.put(user, kfs);
    }

    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    public KieFileSystem getKieFileSystem(String user) {
        return Users.get(user);

    }

    public String getUsers() {
        String ch = "";
        for (String key : Users.keySet()) {
            ch = " " + ch + key;
        }
        return ch;
    }

    public boolean testUser(String user) {

        return Users.containsKey(user);
    }
}