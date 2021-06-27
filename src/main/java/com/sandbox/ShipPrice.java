package com.sandbox;
import java.util.*;

public class ShipPrice {
    Double money;
    Date timeFrom;
    Date timeTo;
    public ShipPrice(Date dateFrom, Date dateTo, Double dollars){
        timeFrom=dateFrom;
        timeTo=dateTo;
        money=dollars;
    }

}
