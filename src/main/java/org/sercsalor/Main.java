package org.sercsalor;

import org.sercsalor.service.JsonConverter;
import org.sercsalor.service.StringTokenizer;

public class Main {

    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println("Invalid argument");
        }

        String filePath = args[0];
        String delimiter = args[1];

        JsonConverter jsonConverter = new JsonConverter(new StringTokenizer(), filePath, delimiter);
        jsonConverter.convert();
    }



}