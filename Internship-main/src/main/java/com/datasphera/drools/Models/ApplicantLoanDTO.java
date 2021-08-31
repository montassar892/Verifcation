package com.datasphera.drools.Models;

public class ApplicantLoanDTO {
    Applicant applicant;
    Loan loan;
    String User;

    public ApplicantLoanDTO(Applicant applicant, Loan loan, String User) {
        this.applicant = applicant;
        this.loan = loan;
        this.User = User;
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

    public String getUser() {
        return this.User;
    }

    public void setUser(String User) {
        this.User = User;
    }

}
