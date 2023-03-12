package org.sercsalor.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JsonConverter {

    private StringTokenizer stringTokenizer;

    private File file;

    private char delimiter;

    private List<DateTimeFormatter> dateFormats;

    public JsonConverter(StringTokenizer stringTokenizer, String filePath, String delimiter) {
        this.stringTokenizer = stringTokenizer;
        this.file = new File(filePath);
        this.delimiter = delimiter.charAt(0);

        // Supported date formats
        dateFormats = new ArrayList<>();
        dateFormats.add(DateTimeFormatter.ISO_LOCAL_DATE);
        dateFormats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("M/dd/yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd/M/yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/M/dd"));

        dateFormats.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("M-dd-yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd-M-yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy-M-dd"));
    }

    public void convert() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = bufferedReader.readLine();

            // first line would be the key value.
            List<String> keys = stringTokenizer.splitIgnoreQuotes(line, delimiter);

            line = bufferedReader.readLine();

            while (line != null) {
                List<String> values = stringTokenizer.splitIgnoreQuotes(line, delimiter);

                String json = IntStream.range(0, keys.size()).filter(index -> !values.get(index).isEmpty())
                        .mapToObj(index -> toJson(keys.get(index), values.get(index))).collect(Collectors.joining(","));
                json = "{" + json + "}";
                
                System.out.println(json);   
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toJson(String key, String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }

        value = transformValue(value);

        return quoteString(key) + ": " + value;
    }

    private String transformValue(String value) {
        LocalDate localDate = null;
        boolean isDateValue = true;

        // Check if Date
        for (DateTimeFormatter dateFormat : dateFormats) {
            try {
                localDate = LocalDate.parse(value, dateFormat);

                isDateValue = true;
                break;
            } catch (DateTimeParseException ex) {
                isDateValue = false;
            }
        }

        if (isDateValue) {
            return quoteString(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        // Check if Integer
        try {
            Integer.valueOf(value);

            return value;
        } catch (NumberFormatException ex) {
        }

        return quoteString(value);
    }

    private String quoteString(String str) {
        return "\"" + str + "\"";
    }
}
