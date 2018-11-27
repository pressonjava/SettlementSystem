package com.jpmg.forex.settlementsystem.services;

import com.jpmg.forex.settlementsystem.entity.Store;
import com.jpmg.forex.settlementsystem.model.DailySettledEntity;
import com.jpmg.forex.settlementsystem.model.DailyTalliedUSD;
import com.jpmg.forex.settlementsystem.model.Instruction;
import com.jpmg.forex.settlementsystem.util.DateHandler;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//Service implementation to process instructions and generate reports.
public class ForexHandlerImpl implements  ForexHandler{

    //Update the actual settlement date, if falls on weekend and store it in memmory
    public void saveInstruction(Instruction instruction){
        checkInstructionSettlementDate(instruction);
        Store.entityInstructions.add(instruction);
    }

    //Method to rank and store the entities, day wise based on USD
    public List<DailySettledEntity> getDailySettledEntity(LocalDate settlementDate){
        Store.dailySettledEntities.clear();
        Store.dailySettledEntities.addAll(rankEntitiesDaily("B",settlementDate));
        Store.dailySettledEntities.addAll(rankEntitiesDaily("S",settlementDate));
        //Store.dailySettledEntities.forEach(System.out::println);
        return Store.dailySettledEntities;
    }

    // update the memory, to store the daily settlement details.
    public DailyTalliedUSD getDailyTalliedUSD(LocalDate settlementDate){
        Store.dailyTalliedUSD.clear();
        Store.dailyTalliedUSD.add(tallyUSDDaily(settlementDate));
        return Store.dailyTalliedUSD.get(0);
    }

    //service to calculate the incoming usd and outgoing for a date
    public DailyTalliedUSD tallyUSDDaily(LocalDate settlementDate){
        List<DailySettledEntity> dailySettledEntities = getDailySettledEntity(settlementDate);
        DailyTalliedUSD dailyTalliedUSD = new DailyTalliedUSD();
        dailyTalliedUSD.setSettlementDate(settlementDate);
        dailyTalliedUSD.setIncommingUSD(dailySettledEntities.stream().filter(e -> e.getAction().equalsIgnoreCase("B")).mapToDouble(e -> e.getUsd()).sum());
        dailyTalliedUSD.setOutgoingUSD(dailySettledEntities.stream().filter(e -> e.getAction().equalsIgnoreCase("S")).mapToDouble(e -> e.getUsd()).sum());
        return dailyTalliedUSD;
    }

    //Method to correct the settlement date, based on currency and its weekend days
    public Instruction checkInstructionSettlementDate(Instruction instruction){
        List<String> holidayForThisCurrency = DateHandler.getHolidayForCurrency(instruction.getCurrency());
        instruction.setSettlementDate(changeSettlementDate(instruction.getSettlementDate(),holidayForThisCurrency));
        return instruction;
    }

    //Actual working day for the settlement date
    public LocalDate changeSettlementDate(LocalDate settlementDate,List<String> holidays){
        String settlementDay = settlementDate.getDayOfWeek().name();
        if(holidays.get(0).equalsIgnoreCase(settlementDay)) settlementDate = settlementDate.plusDays(2);
        else if(holidays.get(1).equalsIgnoreCase(settlementDay)) settlementDate = settlementDate.plusDays(1);
        return settlementDate;
    }



    public List<DailySettledEntity> rankEntitiesDaily(String action, LocalDate settlementDate){
        AtomicInteger index = new AtomicInteger();
        List<DailySettledEntity> rankedDailyLists = Store.entityInstructions.stream().filter( (en) -> en.getSettlementDate().equals(settlementDate) &&
                en.getAction().equalsIgnoreCase(action)).
                sorted(Comparator.comparing(Instruction::getUsd).reversed()).map((en) -> { DailySettledEntity dse = new DailySettledEntity();
            dse.setAction(en.getAction());
            dse.setEntity(en.getEntity());
            dse.setUsd(en.getUsd());
            dse.setSettlementDate(en.getSettlementDate());
            dse.setRank(index.incrementAndGet());
            return dse;
        }).collect(Collectors.toList());
        return rankedDailyLists;
    }

}
