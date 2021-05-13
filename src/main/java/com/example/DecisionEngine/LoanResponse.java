package com.example.DecisionEngine;

public class LoanResponse {
    private double enteredAmount;
    private int enteredPeriod;
    private double proposedAmount;
    private int proposedPeriod;

    public double getEnteredAmount() {
        return enteredAmount;
    }

    public void setEnteredAmount(double enteredAmount) {
        this.enteredAmount = enteredAmount;
    }

    public int getEnteredPeriod() {
        return enteredPeriod;
    }

    public void setEnteredPeriod(int enteredPeriod) {
        this.enteredPeriod = enteredPeriod;
    }

    public double getProposedAmount() {
        return proposedAmount;
    }

    public void setProposedAmount(double proposedAmount) {
        this.proposedAmount = proposedAmount;
    }

    public int getProposedPeriod() {
        return proposedPeriod;
    }

    public void setProposedPeriod(int proposedPeriod) {
        this.proposedPeriod = proposedPeriod;
    }
}
