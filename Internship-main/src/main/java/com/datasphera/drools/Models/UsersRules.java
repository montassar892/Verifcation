package com.datasphera.drools.Models;

public class UsersRules {

    public String rule;
    public String User;

    public UsersRules(String rule, String User) {
        this.rule = rule;
        this.User = User;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getUser() {
        return this.User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    @Override
    public String toString() {
        return "{" + " User='" + getUser() + "'" + "\n Rule='" + getRule() + "'" + "}";
    }

}
