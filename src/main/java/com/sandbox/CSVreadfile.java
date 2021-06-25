package com.sandbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CSVreadfile {
    BufferedReader reader;
    String linija;
    public CSVreadfile(){

        try {
            File file = new File("cjenik.csv");

             reader = new BufferedReader(new FileReader(file));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    String SljedeciRed(){
        try {
            linija = reader.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return linija;

    }



}
