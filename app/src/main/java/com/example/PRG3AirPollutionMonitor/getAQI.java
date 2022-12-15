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

    public String AQIOutput() {
        try {

            List<String> species_list = Arrays.asList(
                    "Carbon Monoxide", "Nitrogen Dioxide", "Ozone", "PM10 Particulate", "PM2.5 Particulate", "Sulphur Dioxide", "Asthma Index");

            Hashtable<String, Double> my_dict = new Hashtable<String, Double>();

            my_dict.put("Carbon Monoxide", 0.0);
            my_dict.put("Nitrogen Dioxide", 0.0);
            my_dict.put("Ozone", 0.0);
            my_dict.put("PM10 Particulate", 0.0);
            my_dict.put("PM2.5 Particulate", 0.0);
            my_dict.put("Sulphur Dioxide", 0.0);

            Hashtable<String, Integer> my_dict_no_of_entries = new Hashtable<String, Integer>();

            my_dict_no_of_entries.put("Carbon Monoxide", 0);
            my_dict_no_of_entries.put("Nitrogen Dioxide", 0);
            my_dict_no_of_entries.put("Ozone", 0);
            my_dict_no_of_entries.put("PM10 Particulate", 0);
            my_dict_no_of_entries.put("PM2.5 Particulate", 0);
            my_dict_no_of_entries.put("Sulphur Dioxide", 0);

            Hashtable<String, Integer> air_pollution_ratings = new Hashtable<String, Integer>();

            Hashtable<String, String> colour_list = new Hashtable<String, String>();

            colour_list.put("Carbon Monoxide", "");
            colour_list.put("Nitrogen Dioxide", "");
            colour_list.put("Ozone", "");
            colour_list.put("PM10 Particulate", "");
            colour_list.put("PM2.5 Particulate", "");
            colour_list.put("Sulphur Dioxide", "");
            colour_list.put("Asthma Index", "");


            URL new_url = new URL("https://api.erg.ic.ac.uk/AirQuality/Daily/MonitoringIndex/Latest/GroupName=" + x + "/Json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new_url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                data = data + line;
            }

            JSONObject myObject = new JSONObject(data);
            JSONObject groupName = myObject.getJSONObject("DailyAirQualityIndex").getJSONObject("LocalAuthority");

            JSONArray arr = groupName.getJSONArray("Site");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject sites = arr.getJSONObject(i);
                try {
                    JSONObject species = sites.getJSONObject("Species");
                    my_dict.put(species.get("@SpeciesDescription").toString(), Double.parseDouble(species.get("@AirQualityIndex").toString()) + (my_dict.get(species.get("@SpeciesDescription").toString())));
                    my_dict_no_of_entries.put(species.get("@SpeciesDescription").toString(), my_dict_no_of_entries.get(species.get("@SpeciesDescription").toString()) + 1);

                } catch (Exception e) {
                    JSONArray species_array = sites.getJSONArray("Species");
                    for (int j = 0; i <= species_array.length(); j++) {
                        try {
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

            for (int i = 0; i < 6; i++) {
                if (my_dict_no_of_entries.get(species_list.get(i)) == 0) {
                    my_dict_no_of_entries.put(species_list.get(i), 1);
                }
            }

            air_pollution_ratings.put("Carbon Monoxide", Math.round((float) (my_dict.get("Carbon Monoxide") / my_dict_no_of_entries.get("Carbon Monoxide"))));
            air_pollution_ratings.put("Nitrogen Dioxide", Math.round((float) (my_dict.get("Nitrogen Dioxide") / my_dict_no_of_entries.get("Nitrogen Dioxide"))));
            air_pollution_ratings.put("Ozone", Math.round((float) (my_dict.get("Ozone") / my_dict_no_of_entries.get("Ozone"))));
            air_pollution_ratings.put("PM10 Particulate", Math.round((float) (my_dict.get("PM10 Particulate") / my_dict_no_of_entries.get("PM10 Particulate"))));
            air_pollution_ratings.put("PM2.5 Particulate", Math.round((float) (my_dict.get("Sulphur Dioxide") / my_dict_no_of_entries.get("Sulphur Dioxide"))));
            air_pollution_ratings.put("Sulphur Dioxide", Math.round((float) (my_dict.get("Sulphur Dioxide") / my_dict_no_of_entries.get("Sulphur Dioxide"))));

            Integer asthma_index = (Math.round((float) (0.25 * ((my_dict.get("Ozone") / my_dict_no_of_entries.get("Ozone"))
                    + 2 * (my_dict.get("Nitrogen Dioxide") / my_dict_no_of_entries.get("Nitrogen Dioxide"))
                    + (my_dict.get("Sulphur Dioxide") / my_dict_no_of_entries.get("Sulphur Dioxide"))
                    + 2 * (my_dict.get("PM2.5 Particulate") / my_dict_no_of_entries.get("PM2.5 Particulate"))))));


            String asthma_index_color;
            if (asthma_index <= 1) {
                colour_list.put("Asthma Index","\uD83D\uDFE2");
            } else if (asthma_index <= 2) {
                colour_list.put("Asthma Index","\uD83D\uDFE0");
            } else {
                colour_list.put("Asthma Index","\uD83D\uDD34");
            }

            return(
                    "Carbon Monoxide:   " + air_pollution_ratings.get("Carbon Monoxide") + " " + colour_list.get("Carbon Monoxide") +"\n" +
                            "Nitrogen Dioxide:     " + air_pollution_ratings.get("Nitrogen Dioxide") + " " + colour_list.get("Nitrogen Dioxide") +"\n" +
                            "Ozone:                        " + air_pollution_ratings.get("Ozone") + " " + colour_list.get("Ozone") +"\n" +
                            "PM10 Particulate:    " + air_pollution_ratings.get("PM10 Particulate") + " " + colour_list.get("PM10 Particulate") +"\n" +
                            "PM2.5 Particulate:   " + air_pollution_ratings.get("PM2.5 Particulate") + " " + colour_list.get("PM2.5 Particulate") +"\n" +
                            "Sulphur Dioxide:       " + air_pollution_ratings.get("Sulphur Dioxide") + " " + colour_list.get("Sulphur Dioxide") +"\n\n" +
                            "Asthma Index:           " + asthma_index + " " + colour_list.get("Asthma Index"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "0";

    }
}