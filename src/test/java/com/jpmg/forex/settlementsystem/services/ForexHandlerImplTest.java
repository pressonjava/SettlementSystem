package com.jpmg.forex.settlementsystem.services;

import com.jpmg.forex.settlementsystem.entity.Store;
import com.jpmg.forex.settlementsystem.model.DailySettledEntity;
import com.jpmg.forex.settlementsystem.model.DailyTalliedUSD;
import com.jpmg.forex.settlementsystem.model.Instruction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Basic tests, for analysing the code functionality.
@RunWith(PowerMockRunner.class)
@PrepareForTest(Store.class)
public class ForexHandlerImplTest {

    @InjectMocks
    private ForexHandlerImpl forexHandler;

    @Before
    public void setUp(){
        //load sample instructions to Store which holds the instructions in memory.
        Instruction instruction1 = new Instruction("foo", "B", 0.22, "AED", LocalDate.of(2018, 11, 25), LocalDate.of(2018, 11, 25), 100, 105.50);
        Instruction instruction2 = new Instruction("bar", "S", 0.22, "AED", LocalDate.of(2018, 11, 25), LocalDate.of(2018, 11, 25), 180, 105.50);
        Instruction instruction3 = new Instruction("das", "B", 0.50, "SGP", LocalDate.of(2018, 11, 24), LocalDate.of(2018, 11, 24), 190, 105.50);
        Instruction instruction4 = new Instruction("joe", "S", 0.50, "SGP", LocalDate.of(2018, 11, 24), LocalDate.of(2018, 11, 24), 120, 105.50);
        Instruction instruction5 = new Instruction("don", "S", 0.50, "SGP", LocalDate.of(2018, 11, 24), LocalDate.of(2018, 11, 24), 110, 105.50);
        Instruction instruction6 = new Instruction("lux", "B", 0.50, "AED", LocalDate.of(2018, 11, 24), LocalDate.of(2018, 11, 24), 100, 105.50);
        forexHandler.saveInstruction(instruction1);
        forexHandler.saveInstruction(instruction2);
        forexHandler.saveInstruction(instruction3);
        forexHandler.saveInstruction(instruction4);
        forexHandler.saveInstruction(instruction5);
        forexHandler.saveInstruction(instruction6);
    }

    /* When System receives the instructions and Report is generated to
     * calculate the incoming and outgoing USD, for particular date
     * This test ensures, USD for particular requested date is validated
     * incoming instruction for 26th actual settlement date,
     */

    @Test
    public void getDailyTalliedUSD() {
        DailyTalliedUSD dtd = new DailyTalliedUSD();
        List<DailyTalliedUSD> dailyTalliedUSDArrayList = new ArrayList<>();
        dailyTalliedUSDArrayList.add(dtd);
        DailyTalliedUSD dailyTalliedUSD1 = forexHandler.getDailyTalliedUSD(LocalDate.of(2018, 11, 26));
        Assert.assertEquals(dailyTalliedUSD1.getIncommingUSD(), 10022.50, .00);
        Assert.assertEquals(dailyTalliedUSD1.getOutgoingUSD(), 12132.50, .00);
    }

   /* When System receives the instructions and Report is generated to rank the entities based on currency
    * A list is generated, with rankings based on USD
    * This test ensures, rank is generated, with rank 1 in the top list
    */
    @Test
    public void getDailySettledEntity() {
        List<DailySettledEntity> dailySettledEntityList = forexHandler.getDailySettledEntity(LocalDate.of(2018, 11, 25));
        Assert.assertTrue(dailySettledEntityList.size()>3);
        Assert.assertEquals(dailySettledEntityList.get(0).getRank(),1);
    }


   /* If settlemnt Date falls on a holiday, settlement date is to be updated to next working day.
    * Given currency AED, and instruction for settlement is given Friday 23rd
    * For AED, holiday is Friday and Saturday.
    * The code updates the settlement date to 25th sunday.
    */
    @Test
    public void checkInstructionSettlementDate() {
        Instruction instruction1 = new Instruction("foo", "B", 0.22, "AED", LocalDate.of(2018, 11, 22), LocalDate.of(2018, 11, 23), 100, 105.50);
        forexHandler.checkInstructionSettlementDate(instruction1);
        Assert.assertEquals(instruction1.getSettlementDate().getDayOfMonth(),25);
    }



   /* If settlemnt Date falls on a holiday, settlement date is to be updated to next working day.
    * Given currency SGP, and instruction for settlement is given sunday 25th
    * For normal currency, holiday is Saturday and Sunday
    * The code updates the settlement date to 26th monday.
    */
    @Test
    public void checksInstructionSettlementDate() {
        Instruction instruction1 = new Instruction("foo", "B", 0.22, "AED", LocalDate.of(2018, 11, 23), LocalDate.of(2018, 11, 25), 100, 105.50);
        forexHandler.checkInstructionSettlementDate(instruction1);
        Assert.assertEquals(instruction1.getSettlementDate().getDayOfMonth(),25);
    }
}