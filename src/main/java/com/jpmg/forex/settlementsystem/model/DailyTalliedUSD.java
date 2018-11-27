package com.jpmg.forex.settlementsystem.model;


import java.time.LocalDate;

//This class will hold the total USD settled  by JPMG date wise
public class DailyTalliedUSD {

//    DailyTalliedUSD dailyTalliedUSD = new DailyTalliedUSD();

    public Double incommingUSD  ;
    public Double outgoingUSD  ;
    public LocalDate settlementDate ;

    public Double getIncommingUSD() {
        return incommingUSD;
    }

    public void setIncommingUSD(Double incommingUSD) {
        this.incommingUSD = incommingUSD;
    }

    public Double getOutgoingUSD() {
        return outgoingUSD;
    }

    public void setOutgoingUSD(Double outgoingUSD) {
        this.outgoingUSD = outgoingUSD;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    @Override
    public String toString() {
        return "DailyTalliedUSD{" +
                "incommingUSD='" + incommingUSD + '\'' +
                ", outgoingUSD='" + outgoingUSD + '\'' +
                ", settlementDate=" + settlementDate +
                '}';
    }
}
