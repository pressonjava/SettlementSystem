package com.jpmg.forex.settlementsystem.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DateHandler {

    //Given a date, method to get the day of the week.
    public DayOfWeek getDayofWeek(LocalDate settlementDate) {
        DayOfWeek dayOfWeek = settlementDate.getDayOfWeek();
        return dayOfWeek;
    }

    //Place to hold normal weekend holidays
    public static List<String> getNormalWeekendDays() {
        List<String> normalWeekendDays = new ArrayList();
        normalWeekendDays.add(DayOfWeek.SATURDAY.name());
        normalWeekendDays.add(DayOfWeek.SUNDAY.name());
        return normalWeekendDays;
    }

    //Place to hold mid east country holidays
    public static List<String> getMidEastWeekendDays() {
        List<String> AEDWeekendDays = new ArrayList();
        AEDWeekendDays.add(DayOfWeek.FRIDAY.name());
        AEDWeekendDays.add(DayOfWeek.SATURDAY.name());
        return AEDWeekendDays;
    }

    //Place to hold mid east currencies
    public static List<String> getMidEastCurrenciess() {
        List<String> midEastCurrencies = new ArrayList<>();
        midEastCurrencies.add("AED");
        midEastCurrencies.add("SAR");
        return midEastCurrencies;
    }

   /* This method will return holiday for a specific currency
    * if currency is normal , will return normal weekend holdaiys
    * else will return mid east holidays mentioned above.
    */
    public static List<String> getHolidayForCurrency(String currency) {
        return checkCurrencyIsMiddleEast(currency) ? getMidEastWeekendDays() : getNormalWeekendDays();

    }

    //Check if currency is normal or mid east currency
    public static boolean checkCurrencyIsMiddleEast(String currency) {
        for (String cuurencys : getMidEastCurrenciess()) {
            if (currency.equalsIgnoreCase(cuurencys)) {return true; }
        }
        return false;
    }
}
