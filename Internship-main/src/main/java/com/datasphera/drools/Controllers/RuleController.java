package com.datasphera.drools.Controllers;

import java.util.List;

import com.datasphera.drools.Models.Applicant;
import com.datasphera.drools.Models.ApplicantLoanDTO;
import com.datasphera.drools.Models.Loan;
import com.datasphera.drools.Services.RuleService;
import com.datasphera.drools.Services.UserService;
import com.datasphera.drools.Models.UsersRules;

//import org.dmg.pmml.True;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RuleController {

    private final Logger log = LoggerFactory.getLogger(RuleController.class);
    private UserService userService;
    private final RuleService ruleService;

    public RuleController(UserService userService, RuleService ruleService) {
        this.userService = userService;
        this.ruleService = ruleService;
    }

    @PostMapping("rule")
    public String addRule(@RequestBody UsersRules u) {

        ruleService.addRule(u.getRule(), u.getUser());
        return u.toString();

    }

    @PostMapping("run")
    public ApplicantLoanDTO fireRule(@RequestBody ApplicantLoanDTO applicantLoan) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = null;
        if (userService.testUser(applicantLoan.getUser()) == true) {
            kieFileSystem = userService.getKieFileSystem(applicantLoan.getUser());
        } else {
            userService.addUser(applicantLoan.getUser());
            kieFileSystem = userService.getKieFileSystem(applicantLoan.getUser());

        }

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        KieSession kieSession = kieContainer.newKieSession();
        log.info("Adding applicant: " + applicantLoan.getApplicant());
        kieSession.insert(applicantLoan.getApplicant());
        log.info("Adding loan: " + applicantLoan.getLoan());
        kieSession.insert(applicantLoan.getLoan());
        log.info("Adding user:" + applicantLoan.getUser());
        kieSession.insert(applicantLoan.getUser());

        int ruleCount = kieSession.fireAllRules();
        log.info("Executed " + ruleCount + " rules");

        log.info("New applicant: " + applicantLoan.getApplicant());
        kieSession.insert(applicantLoan.getApplicant());
        log.info("New loan: " + applicantLoan.getLoan());
        kieSession.insert(applicantLoan.getLoan());

        kieSession.dispose();
        return new ApplicantLoanDTO(applicantLoan.getApplicant(), applicantLoan.getLoan(), applicantLoan.getUser());
    }
}
