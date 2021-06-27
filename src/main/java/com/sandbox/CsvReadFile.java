package com.sandbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CsvReadFile {
    BufferedReader reader;
    String line;
    public CsvReadFile(){

        try {
            File file = new File("cjenik.csv");

             reader = new BufferedReader(new FileReader(file));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    String nextLine(){
        try {
            line = reader.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return line;

    }



}
