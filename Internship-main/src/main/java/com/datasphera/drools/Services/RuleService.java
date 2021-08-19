package com.datasphera.drools.Services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;

public class RuleService {
    List<String> Rules = new ArrayList<String>();

    private final KieFileSystem kieFileSystem;
    private final KieServices kieServices;

    public RuleService(KieFileSystem kieFileSystem, KieServices kieServices) {
        this.kieFileSystem = kieFileSystem;
        this.kieServices = kieServices;
    }

    public void addRule(String rule) {
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
