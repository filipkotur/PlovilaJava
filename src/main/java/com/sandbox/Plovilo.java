package com.sandbox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDate;

import java.util.concurrent.atomic.AtomicReference;

public class Plovilo {
        int ID ;
        String nazivPlovila;
        int godiste;

    Map<Date, Double> PeriodizaVozila = new HashMap<>();
    ArrayList<PloviloCijena> Plovilacijena = new ArrayList<PloviloCijena>();
    List<Date> Periodi = new ArrayList<Date>();

    public Plovilo()
        {

        }

        public Plovilo(int ID1 ,
                       String nazivNautike,
                       int godiste1

                       )
        {
            ID = ID1;
            nazivPlovila=nazivNautike;
            godiste=godiste1;


        }

    void DodajPlovilacijena(Date datumod,Date datumdo ,Double novci){
        PloviloCijena noviobjekt = new PloviloCijena(datumod, datumdo, novci);
        Plovilacijena.add(noviobjekt);

    }

    Date Promjenidatum(Date datumKojiTrebaPromjenit,int zaKolikoDanaPromjena ){
        Calendar cal = Calendar.getInstance();

        cal.setTime( datumKojiTrebaPromjenit );
        cal.add( Calendar.DATE, zaKolikoDanaPromjena);
        return datumKojiTrebaPromjenit=cal.getTime();
    }

        String ProvjeriDatum(Date unesenidatum,int dani) {
            Double rezultat = 0.0;
            long brojdana = 0;
            SimpleDateFormat PromjenaStringa=new SimpleDateFormat("dd.MM.yyyy");
            String najmanjidatum ="01.01.2021";
            String NajveciDatum ="31.12.2021";
            String rezultatProvjere = null;
            Date Najvecidate = new Date();
            Date Najnizidatum = new Date();

            try{
                Najvecidate=PromjenaStringa.parse(NajveciDatum);
                Najnizidatum=PromjenaStringa.parse(najmanjidatum);
            }catch (ParseException e){e.printStackTrace();}
            if ((unesenidatum.compareTo(Najnizidatum) < 0 || unesenidatum.compareTo(Najvecidate) > 0)) {
                rezultatProvjere = "Uneseni datum je izvan dosega";

            } else {
                unesenidatum=Promjenidatum(unesenidatum,dani);


                if (unesenidatum.compareTo(Najvecidate) >= 0) {
                    rezultatProvjere = "prevelik broj dana najma";
                } else {
                    unesenidatum=Promjenidatum(unesenidatum,-dani);


                    for (PloviloCijena model : Plovilacijena) {
                        if ((unesenidatum.compareTo(model.Vrijemeod) >= 0 && unesenidatum.compareTo(model.VrijemeDo) <= 0)) {
                            unesenidatum=Promjenidatum(unesenidatum,dani);

                            if (unesenidatum.compareTo(model.VrijemeDo) > 0) {
                                unesenidatum=Promjenidatum(unesenidatum,-dani);
                                brojdana = 1+((model.VrijemeDo.getTime() - unesenidatum.getTime())/86400000);
                                rezultat = rezultat + model.Cijena * brojdana / 7;

                                unesenidatum=Promjenidatum(unesenidatum,(int) brojdana-1);


                                dani = (int) (dani - brojdana+1);


                            } else {
                                rezultat = rezultat + model.Cijena * dani / 7;
                            }
                        }
                    }
                    return "Iznos cijene najma je "  + String.valueOf(rezultat);


                }
                return rezultatProvjere ;
            }
            return rezultatProvjere ;

        }}
