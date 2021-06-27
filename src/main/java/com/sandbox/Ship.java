package com.sandbox;

import java.util.*;


public class Ship {
        int ID ;
        String nameOfShip;
        int yearOfShip;

    ArrayList<ShipPrice> shipPrice = new ArrayList<ShipPrice>();


        public Ship(int ID1 ,
                    String nameShip,
                    int yearShip
                       )
        {
            ID = ID1;
            nameOfShip =nameShip;
            yearOfShip =yearShip;


        }

    void addNewShipData(Date timeFrom, Date timeTo , Double money){
        ShipPrice newShip = new ShipPrice(timeFrom, timeTo, money);
        shipPrice.add(newShip);

    }

    Date changeDate(Date dateThatNeedsChanging, int forHowManyDaysChangeDate ){
        Calendar cal = Calendar.getInstance();

        cal.setTime( dateThatNeedsChanging );
        cal.add( Calendar.DATE, forHowManyDaysChangeDate);
        return dateThatNeedsChanging=cal.getTime();
    }

    boolean checkDateRange(Date givenDate, Date lowestDate, Date highestDate){
        if (givenDate.compareTo(lowestDate) < 0 || givenDate.compareTo(highestDate) > 0)
            return true;
        else
            return false;

    }
    Double calculatePrice(Date givenDate, int days, long changedDays){

        Double money = 0.0;
        for (ShipPrice model : shipPrice) {
            if ((givenDate.compareTo(model.timeFrom) >= 0 && givenDate.compareTo(model.timeTo) <= 0)) {
                givenDate= changeDate(givenDate,days);

                if (givenDate.compareTo(model.timeTo) > 0) {
                    givenDate= changeDate(givenDate,-days);
                    changedDays = 1+((model.timeTo.getTime() - givenDate.getTime())/86400000);
                    money = money + model.money * changedDays / 7;

                    givenDate= changeDate(givenDate,(int) changedDays+1);


                    days = (int) (days - changedDays);


                } else {
                    money = money + model.money * days / 7;
                }
            }
        }
        return money;
    }


        String userRequest(Date givenDate, int days) {
            Double result = 0.0;
            long changedDays = 0;
            String resultOfRequst = null;

            if (checkDateRange( givenDate, shipPrice.get(0).timeFrom, shipPrice.get(shipPrice.size()-1).timeTo)) {
                resultOfRequst = "Uneseni datum je izvan dosega";

            } else {
                givenDate= changeDate(givenDate,days);


                if (givenDate.compareTo(shipPrice.get(shipPrice.size()-1).timeTo) >= 0) {
                    resultOfRequst = "prevelik broj dana najma";
                } else {
                    givenDate= changeDate(givenDate,-days);
                    result = calculatePrice(givenDate, days, changedDays);
                    return "Iznos cijene najma je "  + String.valueOf(result);
                }
            }
            return resultOfRequst ;

        }}
