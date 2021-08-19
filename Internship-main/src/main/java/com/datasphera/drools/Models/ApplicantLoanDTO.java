package com.datasphera.drools.Models;

public class ApplicantLoanDTO {
    Applicant applicant;
    Loan loan;

    public ApplicantLoanDTO(Applicant applicant, Loan loan) {
        this.applicant = applicant;
        this.loan = loan;
    }

    public Applicant getApplicant() {
        return this.applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Loan getLoan() {
        return this.loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
