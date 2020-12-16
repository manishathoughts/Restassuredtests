package com.elsevier.usage.apitests.utils;




import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReaderUtils {
    public static String readJson(String filePath) throws IOException {
        JSONParser parser = new JSONParser();
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fr= new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line= null;
        while ((line = br.readLine()) != null){
            stringBuilder.append(line);
        }
        System.out.println("------------------->"+stringBuilder.toString());
        return stringBuilder.toString();
    }

}
