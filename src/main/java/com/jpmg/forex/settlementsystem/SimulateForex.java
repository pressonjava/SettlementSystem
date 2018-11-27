package com.jpmg.forex.settlementsystem;

import com.jpmg.forex.settlementsystem.entity.Store;
import com.jpmg.forex.settlementsystem.model.Instruction;
import com.jpmg.forex.settlementsystem.services.ForexHandler;
import com.jpmg.forex.settlementsystem.services.ForexHandlerImpl;

import java.time.LocalDate;

public class SimulateForex {
    public static void main(String args[]){
       Instruction instruction1 = new Instruction("foo","B",0.22,"AED", LocalDate.of(2018,11,25),LocalDate.of(2018,11,25),100,104.50);
       Instruction instruction2 = new Instruction("bar","S",0.22,"AED", LocalDate.of(2018,11,25),LocalDate.of(2018,11,25),180,110.50);
       Instruction instruction3= new Instruction("das","B",0.50,"SGP", LocalDate.of(2018,11,24),LocalDate.of(2018,11,24),190,57.50);
       Instruction instruction4 = new Instruction("joe","S",0.50,"SGP", LocalDate.of(2018,11,24),LocalDate.of(2018,11,24),120,57.50);
       Instruction instruction5 = new Instruction("don","S",0.50,"SGP", LocalDate.of(2018,11,24),LocalDate.of(2018,11,24),110,65.57);
       Instruction instruction6 = new Instruction("lux","B",0.50,"AED", LocalDate.of(2018,11,24),LocalDate.of(2018,11,24),100,105.5);


       //Load instructions into the jpmg system
       //Assume these are the instructions received by jpmg
       //saveInstruction will process the incoming instructions, process it, and update in memory.
       ForexHandler fx = new ForexHandlerImpl();
       fx.saveInstruction(instruction1);
       fx.saveInstruction(instruction2);
       fx.saveInstruction(instruction3);
       fx.saveInstruction(instruction4);
       fx.saveInstruction(instruction5);
       fx.saveInstruction(instruction6);
       fx.saveInstruction(instruction6);

       //instructions being processed , like correcting the settlement date
       //is stored in a hashmap, and is fetched and displayed
       Store.entityInstructions.forEach(System.out::println);

       //Report for a settlement Date
       LocalDate settlementDate = LocalDate.of(2018,11,25);


       //Entities being ranked for a particular settlement date and printed.
       fx.getDailySettledEntity(settlementDate).forEach(System.out::println);

       //Dail settled instructions are aggregated for incomming and outgoing USD.
       fx.getDailyTalliedUSD(settlementDate);

    }
}
