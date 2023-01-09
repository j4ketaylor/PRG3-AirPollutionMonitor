package com.example.PRG3AirPollutionMonitor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class getAQI {
    private String x;
    private String data;

    public getAQI (String x, String data) {
        this.x = x;
        this.data = data;
    }

    public String[] AQIOutput() {
        try {

            //Different types of particulates used throughout the class
            List<String> species_list = Arrays.asList(
                    "Carbon Monoxide", "Nitrogen Dioxide", "Ozone", "PM10 Particulate", "PM2.5 Particulate", "Sulphur Dioxide", "Asthma Index");

            Hashtable<String, Double> my_dict = new Hashtable<String, Double>();

            //Sets initial total values of each particulate
            my_dict.put("Carbon Monoxide", 0.0);
            my_dict.put("Nitrogen Dioxide", 0.0);
            my_dict.put("Ozone", 0.0);
            my_dict.put("PM10 Particulate", 0.0);
            my_dict.put("PM2.5 Particulate", 0.0);
            my_dict.put("Sulphur Dioxide", 0.0);

            Hashtable<String, Integer> my_dict_no_of_entries = new Hashtable<String, Integer>();

            //Sets initial value of number of particulate data points
            my_dict_no_of_entries.put("Carbon Monoxide", 0);
            my_dict_no_of_entries.put("Nitrogen Dioxide", 0);
            my_dict_no_of_entries.put("Ozone", 0);
            my_dict_no_of_entries.put("PM10 Particulate", 0);
            my_dict_no_of_entries.put("PM2.5 Particulate", 0);
            my_dict_no_of_entries.put("Sulphur Dioxide", 0);

            Hashtable<String, Integer> air_pollution_ratings = new Hashtable<String, Integer>();

            Hashtable<String, String> colour_list = new Hashtable<String, String>();

            colour_list.put("Asthma Index", "");

            //Connects to the LAQN API and gets Json formatted data for the selected borough x
            URL new_url = new URL("https://api.erg.ic.ac.uk/AirQuality/Daily/MonitoringIndex/Latest/GroupName=" + x + "/Json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new_url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                data = data + line;
            }

            //Sorts through JSON object to get all species information
            JSONObject myObject = new JSONObject(data);
            JSONObject groupName = myObject.getJSONObject("DailyAirQualityIndex").getJSONObject("LocalAuthority");
            try {
                JSONArray arr = groupName.getJSONArray("Site");
                for (int i = 0; i < arr.length(); i++) {
                    //Multiple sites in the borough
                    JSONObject sites = arr.getJSONObject(i);
                    try {
                        JSONObject species = sites.getJSONObject("Species");
                        //Adds data about each particulate to its relevant hashtable entry
                        my_dict.put(species.get("@SpeciesDescription").toString(), Double.parseDouble(species.get("@AirQualityIndex").toString()) + (my_dict.get(species.get("@SpeciesDescription").toString())));
                        my_dict_no_of_entries.put(species.get("@SpeciesDescription").toString(), my_dict_no_of_entries.get(species.get("@SpeciesDescription").toString()) + 1);

                    } catch (Exception e) {
                        JSONArray species_array = sites.getJSONArray("Species");
                        for (int j = 0; j <= species_array.length(); j++) {
                            try {
                                //Adds data about each particulate to its relevant hashtable entry
                                JSONObject species = species_array.getJSONObject(j);
                                my_dict.put(species.get("@SpeciesDescription").toString(),
                                        Double.parseDouble(species.get("@AirQualityIndex").toString()) +
                                                (my_dict.get(species.get("@SpeciesDescription").toString())));

                                my_dict_no_of_entries.put(species.get("@SpeciesDescription").toString(), my_dict_no_of_entries.get(species.get("@SpeciesDescription").toString()) + 1);
                            } catch (Exception f) {
                                break;
                            }
                        }
                    }
                }
            } catch (JSONException g) {
                //Only one site in the borough therefore no need to index through them
                JSONObject obj = groupName.getJSONObject("Site");
                try {
                    JSONArray species_array = obj.getJSONArray("Species");
                    for (int k = 0; k < species_array.length(); k++) {
                        JSONObject species = species_array.getJSONObject(k);
                        my_dict.put(species.get("@SpeciesDescription").toString(),
                                Double.parseDouble(species.get("@AirQualityIndex").toString()) +
                                        (my_dict.get(species.get("@SpeciesDescription").toString())));

                        my_dict_no_of_entries.put(species.get("@SpeciesDescription").toString(), my_dict_no_of_entries.get(species.get("@SpeciesDescription").toString()) + 1);
                    }
                } catch (JSONException h) {
                    //Only one type of pollutant therefore no need to iterate through them
                    JSONObject species_object = obj.getJSONObject("Species");
                    my_dict.put(species_object.get("@SpeciesDescription").toString(),
                            Double.parseDouble(species_object.get("@AirQualityIndex").toString()) +
                                    (my_dict.get(species_object.get("@SpeciesDescription").toString())));

                    my_dict_no_of_entries.put(species_object.get("@SpeciesDescription").toString(), my_dict_no_of_entries.get(species_object.get("@SpeciesDescription").toString()) + 1);
                }
            }

            //Ensures that there is no division by 0 errors when normalising air pollution ratings below
            for (int i = 0; i < 6; i++) {
                if (my_dict_no_of_entries.get(species_list.get(i)) == 0) {
                    my_dict_no_of_entries.put(species_list.get(i), 1);
                }
            }

            //Calculates weighted air pollution ratings depending on sum of each individual particulate occurance divided by number of appearances
            air_pollution_ratings.put("Carbon Monoxide", Math.round((float) (my_dict.get("Carbon Monoxide") / my_dict_no_of_entries.get("Carbon Monoxide"))));
            air_pollution_ratings.put("Nitrogen Dioxide", Math.round((float) (my_dict.get("Nitrogen Dioxide") / my_dict_no_of_entries.get("Nitrogen Dioxide"))));
            air_pollution_ratings.put("Ozone", Math.round((float) (my_dict.get("Ozone") / my_dict_no_of_entries.get("Ozone"))));
            air_pollution_ratings.put("PM10 Particulate", Math.round((float) (my_dict.get("PM10 Particulate") / my_dict_no_of_entries.get("PM10 Particulate"))));
            air_pollution_ratings.put("PM2.5 Particulate", Math.round((float) (my_dict.get("Sulphur Dioxide") / my_dict_no_of_entries.get("Sulphur Dioxide"))));
            air_pollution_ratings.put("Sulphur Dioxide", Math.round((float) (my_dict.get("Sulphur Dioxide") / my_dict_no_of_entries.get("Sulphur Dioxide"))));

            //Calculates asthma index based on 4 most harmful particulates to asthma sufferers
            Integer asthma_index = (Math.round((float) (0.25 * ((my_dict.get("Ozone") / my_dict_no_of_entries.get("Ozone"))
                    + 2 * (my_dict.get("Nitrogen Dioxide") / my_dict_no_of_entries.get("Nitrogen Dioxide"))
                    + (my_dict.get("Sulphur Dioxide") / my_dict_no_of_entries.get("Sulphur Dioxide"))
                    + 2 * (my_dict.get("PM2.5 Particulate") / my_dict_no_of_entries.get("PM2.5 Particulate"))))));

            //Sets colour to green or red, depending on safety of area as determined by asthma index
            if (asthma_index <= 2) {
                colour_list.put("Asthma Index","\uD83D\uDFE2");
            } else {
                colour_list.put("Asthma Index","\uD83D\uDFE0");
            }

            //Returns a String array of individual ratings for each particulate and an asthma index
            return new String[] {("Carbon Monoxide:   " + air_pollution_ratings.get("Carbon Monoxide") +"\n" +
                            "Nitrogen Dioxide:     " + air_pollution_ratings.get("Nitrogen Dioxide") +"\n" +
                            "Ozone:                        " + air_pollution_ratings.get("Ozone") + "\n" +
                            "PM10 Particulate:    " + air_pollution_ratings.get("PM10 Particulate") + "\n" +
                            "PM2.5 Particulate:   " + air_pollution_ratings.get("PM2.5 Particulate") + "\n" +
                            "Sulphur Dioxide:       " + air_pollution_ratings.get("Sulphur Dioxide") + "\n\n" +
                            "Asthma Index:           " + asthma_index + " " + colour_list.get("Asthma Index") + "\n").toString(), asthma_index.toString()};

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new String[]{"0","0"};

    }
}
