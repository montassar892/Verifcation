package com.datasphera.drools.Configuration;

import java.io.IOException;
import java.io.StringReader;

import com.datasphera.drools.Services.RuleService;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class DroolsConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RuleService getRuleService() throws IOException {
        RuleService service = new RuleService(kieFileSystem(), kieServices());
        return service;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
        return kieFileSystem;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public KieContainer kieContainer(KieFileSystem kieFileSystem) throws IOException {
        System.out.println("Creating Kie Container!");
        final KieRepository kieRepository = kieRepository();
        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        return kieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    private KieRepository kieRepository() {
        final KieRepository kieRepository = kieServices().getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
        return kieRepository;
    }

    private KieServices kieServices() {
        return KieServices.Factory.get();
    }
}