package com.datasphera.drools.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

@Service
@Scope("singleton")

public class UserService {
    private HashMap<String, KieFileSystem> Users = new HashMap<String, KieFileSystem>();

    public void addUser(String user) {
        // create the kfs instantanly
        KieFileSystem kfs = null;
        if (testUser(user) == false) {
            kfs = kieServices().newKieFileSystem();
            Users.put(user, kfs);
        } else {
            System.out.println("User exists");
        }
    }

    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    public KieFileSystem getKieFileSystem(String user) {
        return Users.get(user);

    }

    public List<String> getUsers() {
        List<String> l = new ArrayList<String>();
        for (String key : Users.keySet()) {
            l.add(key);
        }
        return l;

        // String ch = "";
        // for (String key : Users.keySet()) {
        // ch = " " + ch + key;
        // }
        // return ch;
    }

    public boolean testUser(String user) {

        return Users.containsKey(user);
    }
}