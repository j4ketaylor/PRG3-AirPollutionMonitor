package com.example.PRG3AirPollutionMonitor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getAQITest {

    @Test
    public void test_getAQI_validInput() throws IOException {
        // Create mock objects
        URL url = mock(URL.class);
        HttpURLConnection httpURLConnection = mock(HttpURLConnection.class);
        InputStream inputStream = mock(InputStream.class);
        BufferedReader bufferedReader = mock(BufferedReader.class);

        // Set up mock behavior
        when(url.openConnection()).thenReturn(httpURLConnection);
        when(httpURLConnection.getInputStream()).thenReturn(inputStream);
        when(bufferedReader.readLine()).thenReturn(
                "{\"DailyAirQualityIndex\": {\"LocalAuthority\": {\"Site\": {" +
                        "\"@SiteName\":\"Site Name\"," +
                        "\"@SiteType\":\"Site Type\"," +
                        "\"Species\": {" +
                        "\"@SpeciesCode\":\"Species Code\"," +
                        "\"@SpeciesDescription\":\"Species Description\"," +
                        "\"@AirQualityIndex\":\"123\"" +
                        "}" +
                        "}}}}",
                null);
        when(new BufferedReader(new InputStreamReader(inputStream))).thenReturn(bufferedReader);

        // Set up test input
        String x = "testGroup";
        String data = "";
        String[] expectedOutput = { "Site Name", "Site Type", "Species Description", "123" };

        // Create test object and call method under test
        getAQI getAQI = new getAQI(x, data);
        String[] output = getAQI.AQIOutput();

        // Verify that the output is as expected
        assertArrayEquals(expectedOutput, output);
    }

//    @Test
//    public void test_getAQI_invalidInput() {
//    }
//
//    @Test
//    public void test_getAQI_exceptionsHandled() {
//    }
}