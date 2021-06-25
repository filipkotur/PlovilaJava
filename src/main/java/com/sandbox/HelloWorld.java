package com.sandbox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HelloWorld {



    public static void main(String... args) {
        CSVreadfile reader = new CSVreadfile();

        String linija =reader.SljedeciRed();
        linija = linija.substring(3,linija.length());
                String[] DatumiuStringu = linija.split(",|-");
                SimpleDateFormat PromjenaStringa=new SimpleDateFormat("dd.MM.yyyy");
                Date[] date2=new Date[DatumiuStringu.length];
                int i=0;
                try{for (String datum : DatumiuStringu) {
                    date2[i] = PromjenaStringa.parse(datum);
                    i++;
                }}catch (ParseException e){e.printStackTrace();}

                List<Plovilo> objects = new ArrayList<Plovilo>();
                System.out.println(Arrays.toString(date2));
                int brojac =1;
                while (brojac<6 && (linija = reader.SljedeciRed()) != null) {
                    String[]  PodacioVozilu = linija.split(",");
                    Plovilo obj = new Plovilo(Integer.parseInt(PodacioVozilu[0]),PodacioVozilu[1],Integer.parseInt(PodacioVozilu[2]));

                    int brojnik =0;
                    for (int zbroj=0; zbroj<date2.length ;zbroj+=2) {
                        obj.DodajPlovilacijena( date2[zbroj],date2[zbroj+1],Double.parseDouble(PodacioVozilu[3+brojnik]));
                        brojnik++;
                    }
                    objects.add(obj);

                    brojac++;
            }

                int prviId = Integer.parseInt(args[5]);
                try{Date drugiDatum = PromjenaStringa.parse(args[6]);
                int treciBrojdana=Integer.parseInt(args[7]);
                for(Plovilo model : objects) {
                    if (model.ID == prviId) {
                            System.out.println("Odgovor:" + model.UpitKorisnika(drugiDatum,treciBrojdana));

                    }

                }
                    }catch (Exception e) {
                    e.printStackTrace();
                }





    }
}
