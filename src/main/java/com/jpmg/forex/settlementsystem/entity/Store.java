package com.jpmg.forex.settlementsystem.entity;

import com.jpmg.forex.settlementsystem.model.DailySettledEntity;
import com.jpmg.forex.settlementsystem.model.DailyTalliedUSD;
import com.jpmg.forex.settlementsystem.model.Instruction;

import java.util.ArrayList;
import java.util.List;

//This Store is like a db holding tables, with instructions and info
public class Store {
    private Store(){}
    //ArrayList which will hold the processed instructions.
    public static List<Instruction> entityInstructions = new ArrayList<>();

    //ArrayList for storing the entities Ranked , settlement date wise
    public static List<DailySettledEntity> dailySettledEntities = new ArrayList<>();

    //ArrayList to hold daily incomming and outgoing USD, settlement date wise.
    public static List<DailyTalliedUSD> dailyTalliedUSD = new ArrayList<>();
}