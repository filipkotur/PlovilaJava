package com.sandbox;

import java.util.*;


public class Plovilo {
        int ID ;
        String nazivPlovila;
        int godiste;

    ArrayList<PloviloCijena> Plovilacijena = new ArrayList<PloviloCijena>();

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

    boolean ProvjeriDosegDatum(Date unesenidatum,Date Najnizidatum,Date Najvecidate){
        if (unesenidatum.compareTo(Najnizidatum) < 0 || unesenidatum.compareTo(Najvecidate) > 0)
            return true;
        else
            return false;

    }
    Double IzracunajCijenu(Date unesenidatum,int dani,long brojdana){

        Double cijena = 0.0;
        for (PloviloCijena model : Plovilacijena) {
            if ((unesenidatum.compareTo(model.Vrijemeod) >= 0 && unesenidatum.compareTo(model.VrijemeDo) <= 0)) {
                unesenidatum=Promjenidatum(unesenidatum,dani);

                if (unesenidatum.compareTo(model.VrijemeDo) > 0) {
                    unesenidatum=Promjenidatum(unesenidatum,-dani);
                    brojdana = 1+((model.VrijemeDo.getTime() - unesenidatum.getTime())/86400000);
                    cijena = cijena + model.Cijena * brojdana / 7;

                    unesenidatum=Promjenidatum(unesenidatum,(int) brojdana+1);


                    dani = (int) (dani - brojdana);


                } else {
                    cijena = cijena + model.Cijena * dani / 7;
                }
            }
        }
        return cijena;
    }


        String UpitKorisnika(Date unesenidatum,int dani) {
            Double rezultat = 0.0;
            long brojdana = 0;
            String rezultatProvjere = null;
            Date Najnizidatum = new Date();
            Najnizidatum  = Plovilacijena.get(0).Vrijemeod;
            Date Najvecidate = new Date();
            Najvecidate = Plovilacijena.get(Plovilacijena.size()-1).VrijemeDo;

            if (ProvjeriDosegDatum( unesenidatum, Najnizidatum, Najvecidate)) {
                rezultatProvjere = "Uneseni datum je izvan dosega";

            } else {
                unesenidatum=Promjenidatum(unesenidatum,dani);


                if (unesenidatum.compareTo(Najvecidate) >= 0) {
                    rezultatProvjere = "prevelik broj dana najma";
                } else {
                    unesenidatum=Promjenidatum(unesenidatum,-dani);
                    rezultat = IzracunajCijenu(unesenidatum, dani, brojdana);
                    return "Iznos cijene najma je "  + String.valueOf(rezultat);
                }
            }
            return rezultatProvjere ;

        }}
