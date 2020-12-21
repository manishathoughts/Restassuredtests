package com.elsevier.usage.apitests.utils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CsvAndTsvReaderUtils {

    public static final String CSV_DELIMITER = ",";
    public static final String TSV_DELIMITER = "\t";


    public static Map<Integer, List<String>> csvAndTsvReader(String response, String delimiter, String path) throws IOException {


        Map<Integer, List<String>> rowMap = new HashMap<>();
        int count=0;
        if (response != null && !response.isEmpty()) {
            try (Scanner scanner = new Scanner(response)) {
                while (scanner.hasNextLine()) {
                    rowMap.put(count,getRecordFromLine(scanner.nextLine(), delimiter));
                    count++;
                }
            }
        } else {
            try (Scanner scanner = new Scanner(new File(path))) {
                while (scanner.hasNextLine()) {
                    rowMap.put(count,getRecordFromLine(scanner.nextLine(), delimiter));
                    count++;
                }
            }
        }
        return rowMap;
    }

    private static List<String> getRecordFromLine(String line, String delimiter) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(delimiter);
            String currentLine = null;
            while (rowScanner.hasNext()) {
                currentLine = rowScanner.next();
                if (currentLine.equals("\"Created\"")) {
                    break;
                } else {
                    values.add(currentLine);
                }
            }
        }
        return values;
    }
}
