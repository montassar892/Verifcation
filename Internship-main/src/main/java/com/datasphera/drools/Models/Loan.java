package com.datasphera.drools.Models;

public class Loan {

    private Boolean approval;
    private double amount;

    public Loan(Boolean x, double y) {
        this.approval = x;
        this.amount = y;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Boolean getApproval() {
        return this.approval;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "{" +
            " approval='" + approval + "'" +
            ", amount='" + amount + "'" +
            "}";
    }

}
