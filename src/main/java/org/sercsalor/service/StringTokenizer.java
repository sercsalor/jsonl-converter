package org.sercsalor.service;

import java.util.ArrayList;
import java.util.List;

/**
 *  A utility class to tokenize a string.
 * 
 *  @author Serc Gil Salor 
 */
public class StringTokenizer {

    /**
     * A method to tokenize a string while ignoring quotes
     * 
     * @param str The string value to be tokenized
     * @param delimiter The delimiter
     * @return List of string tokens
     */
    public List<String> splitIgnoreQuotes(String str, char delimiter) {
        List<String> tokens = new ArrayList<>();

        boolean insideQuotes = false;
        int cursor = 0;

        for (int index = 0; index < str.length(); index++) {
            char currentChar = str.charAt(index);

            if (currentChar == '"') {
                insideQuotes = !insideQuotes;
            } else if (currentChar == delimiter) {
                if (!insideQuotes) {
                    String token = str.substring(cursor, index).replace("\"", "");    

                    tokens.add(token);
                    cursor = index + 1;
                }
            }

            if (index == str.length() - 1) {
                tokens.add(str.substring(cursor, str.length()));
            }
        }

        return tokens;
    }

}
