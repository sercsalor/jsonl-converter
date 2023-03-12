package org.sercsalor.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonConverterTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final String expectedResult = "{\"firstName\": \"Wolfgang\",\"middleName\": \"Amadeus\",\"lastName\": \"Mozart\",\"gender\": \"Male\",\"dateOfBirth\": \"1756-01-27\",\"salary\": 1000}" +
    "\n{\"firstName\": \"Albert\",\"lastName\": \"Einstein\",\"gender\": \"Male\",\"dateOfBirth\": \"1955-04-18\",\"salary\": 2000}" +
    "\n{\"firstName\": \"Marie, Salomea\",\"middleName\": \"Skłodowska |\",\"lastName\": \"Curie\",\"gender\": \"Female\",\"dateOfBirth\": \"1934-07-04\",\"salary\": 3000}\n";
    

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testInput1() {
        JsonConverter jsonConverter = new JsonConverter(new StringTokenizer(), "src/test/resources/input-1.txt", ",");
        jsonConverter.convert();

        Assert.assertEquals(expectedResult, outputStreamCaptor.toString());
    }

    @Test
    public void testInput2() {
        JsonConverter jsonConverter = new JsonConverter(new StringTokenizer(), "src/test/resources/input-2.txt", "|");
        jsonConverter.convert();

        Assert.assertEquals(expectedResult, outputStreamCaptor.toString());
    }

    @After
    public void teardown() {
        System.setOut(standardOut);
    }

}
