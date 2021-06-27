package com.sandbox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HelloWorld {



    public static void main(String... args) {
        CsvReadFile reader = new CsvReadFile();

        String line =reader.nextLine();
        line = line.substring(3,line.length());
                String[] dateAsString = line.split(",|-");
                SimpleDateFormat changingString=new SimpleDateFormat("dd.MM.yyyy");
                Date[] date2=new Date[dateAsString.length];
                int i=0;
                try{for (String date : dateAsString) {
                    date2[i] = changingString.parse(date);
                    i++;
                }}catch (ParseException e){e.printStackTrace();}

                List<Ship> objects = new ArrayList<Ship>();
                System.out.println(Arrays.toString(date2));
                while ((line= reader.nextLine())!=null)
                {
                    if(line.length() > 0) {
                        String[] shipData = line.split(",");
                        Ship obj = new Ship(Integer.parseInt(shipData[0]), shipData[1], Integer.parseInt(shipData[2]));

                        int counter = 0;
                        for (int addition = 0; addition < date2.length; addition += 2) {
                            obj.addNewShipData(date2[addition], date2[addition + 1], Double.parseDouble(shipData[3 + counter]));
                            counter++;
                        }
                        objects.add(obj);

                    }
            }

                int firstID = Integer.parseInt(args[5]);
                try{Date dateOfUser = changingString.parse(args[6]);
                int numberOfDaysOfUser=Integer.parseInt(args[7]);
                for(Ship model : objects) {
                    if (model.ID == firstID) {
                            System.out.println("Odgovor:" + model.userRequest(dateOfUser,numberOfDaysOfUser));
                    break;
                    }

                }
                    }catch (Exception e) {
                    e.printStackTrace();
                }





    }
}
