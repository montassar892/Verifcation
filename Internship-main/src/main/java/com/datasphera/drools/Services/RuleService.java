package com.datasphera.drools.Services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;

import org.springframework.stereotype.Service;

@Service
public class RuleService {
    List<String> Rules = new ArrayList<String>();
    private UserService userService;
    private final KieServices kieServices;

    public RuleService(UserService userService, KieServices kieServices) {
        this.userService = userService;
        this.kieServices = kieServices;
    }

    public void addRule(String rule, String user) {
        KieFileSystem kieFileSystem = null;
        Rules.add(rule);
        if (userService.testUser(user) == false) {
            kieFileSystem = KieServices.Factory.get().newKieFileSystem();
        } else {
            kieFileSystem = userService.getKieFileSystem(user);
        }
        Rules.add(rule);
        kieFileSystem.write("src/main/resources/" + UUID.randomUUID().toString() + ".drl",
                kieServices.getResources().newReaderResource(new StringReader(rule)));
    }

    public void removeRule(String rule) {
        Rules.remove(rule);
    }

    public List<String> getRules() {
        return Rules;
    }
}
