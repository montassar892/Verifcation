package com.datasphera.drools.Controllers;

import java.util.List;

import com.datasphera.drools.Models.Applicant;
import com.datasphera.drools.Models.ApplicantLoanDTO;
import com.datasphera.drools.Models.Loan;
import com.datasphera.drools.Services.RuleService;
import com.datasphera.drools.Models.UsersRules;

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
// @Configuration
// @ComponentScan(basePackageClasses = RuleService.class)
@RequestMapping("/api")
public class RuleController {

    private final Logger log = LoggerFactory.getLogger(RuleController.class);

    private final KieContainer kieContainer;

    private final RuleService ruleService;

    public RuleController(KieContainer kieContainer, RuleService ruleService) {
        this.kieContainer = kieContainer;
        this.ruleService = ruleService;
    }

    @PostMapping("rule")
    public String addRule(@RequestBody UsersRules u) {

        ruleService.addRule(u.getRule());
        return u.toString();

    }

    @PostMapping("run")
    public ApplicantLoanDTO addRule(@RequestBody ApplicantLoanDTO applicantLoan) {
        KieSession kieSession = kieContainer.newKieSession();
        log.info("Adding applicant: " + applicantLoan.getApplicant());
        kieSession.insert(applicantLoan.getApplicant());
        log.info("Adding loan: " + applicantLoan.getLoan());
        kieSession.insert(applicantLoan.getLoan());

        int ruleCount = kieSession.fireAllRules();
        log.info("Executed " + ruleCount + " rules");

        log.info("New applicant: " + applicantLoan.getApplicant());
        kieSession.insert(applicantLoan.getApplicant());
        log.info("New loan: " + applicantLoan.getLoan());
        kieSession.insert(applicantLoan.getLoan());
        kieSession.dispose();
        return new ApplicantLoanDTO(applicantLoan.getApplicant(), applicantLoan.getLoan());
    }
}
