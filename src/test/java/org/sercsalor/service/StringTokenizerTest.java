package org.sercsalor.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringTokenizerTest {

    private StringTokenizer stringTokenizer;

    @Before
    public void init() {
        stringTokenizer = new StringTokenizer();
    }

    @Test
    public void shouldIgnoreQuotes() {
        String value = "\"Marie, Salomea\",Skłodowska |,Curie,Female,04-07-1934,3000";
        List<String> tokens = stringTokenizer.splitIgnoreQuotes(value, ',');
        List<String> expectedTokens = List.of("Marie, Salomea", "Skłodowska |", "Curie", "Female", "04-07-1934",
                "3000");

        Assert.assertEquals(expectedTokens, tokens);
        Assert.assertEquals(6, tokens.size());
    }

}
