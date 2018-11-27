package com.jpmg.forex.settlementsystem.services;

import com.jpmg.forex.settlementsystem.model.DailySettledEntity;
import com.jpmg.forex.settlementsystem.model.DailyTalliedUSD;
import com.jpmg.forex.settlementsystem.model.Instruction;

import java.time.LocalDate;
import java.util.List;

//Service that contains business logic for processing JPMG instructions, and exposed for usage

public interface ForexHandler {
    public Instruction checkInstructionSettlementDate(Instruction instruction);
    public void saveInstruction(Instruction instruction);
    public List<DailySettledEntity> getDailySettledEntity(LocalDate settlementDate);
    public DailyTalliedUSD getDailyTalliedUSD(LocalDate settlementDate);
}
