package com.jpmg.forex.settlementsystem.model;

import java.time.LocalDate;

public class Instruction {
    /**
     * Basic Instruction entity variables
     */
    private String entity ;

    public Instruction(String entity, String action, Double agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units, Double pricePerUnit) {
        this.entity = entity;
        this.action = action;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.usd = this.units*this.pricePerUnit*this.agreedFx;
    }

    private String action ;
    private Double agreedFx;
    private String  currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private int units;
    private Double pricePerUnit;
    private Double usd;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Double getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(Double agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getUsd() {
        return this.units*this.pricePerUnit*this.agreedFx;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "entity='" + entity + '\'' +
                ", action='" + action + '\'' +
                ", agreedFx=" + agreedFx +
                ", currency='" + currency + '\'' +
                ", instructionDate=" + instructionDate +
                ", settlementDate=" + settlementDate +
                ", units=" + units +
                ", pricePerUnit=" + pricePerUnit +
                ", usd=" + usd +
                '}';
    }


}
