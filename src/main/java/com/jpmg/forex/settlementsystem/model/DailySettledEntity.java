package com.jpmg.forex.settlementsystem.model;

import java.time.LocalDate;
//This class will hold entity and its total transaction done per day.
//will also hold the rank of each entity based on it total USD transaction per day.

public class DailySettledEntity {

    private String entity ;
    private String action ;
    private LocalDate settlementDate;
    private Double usd;
    private Integer rank;

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

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "DailySettledEntity{" +
                "entity='" + entity + '\'' +
                ", action='" + action + '\'' +
                ", settlementDate=" + settlementDate +
                ", usd=" + usd +
                ", rank=" + rank +
                '}';
    }


}
