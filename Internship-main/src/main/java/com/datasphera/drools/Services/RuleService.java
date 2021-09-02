package com.datasphera.drools.Services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.LogFactory;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class RuleService {
    List<String> Rules = new ArrayList<String>();
    private final Logger log = LoggerFactory.getLogger(RuleService.class);
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
        String x = UUID.randomUUID().toString();
        log.info("ID of the kieFileSystem: " + x);

        kieFileSystem.write("src/main/resources/" + x + ".drl",
                kieServices.getResources().newReaderResource(new StringReader(rule)));

    }

    public void removeRule(String rule) {
        Rules.remove(rule);
    }

    public List<String> getRules() {
        return Rules;
    }
}
