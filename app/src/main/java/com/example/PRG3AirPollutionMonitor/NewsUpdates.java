package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsUpdates extends AppCompatActivity {

    private ListView listView;
    private NewsListAdapter adapter;
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_updates);

        listView = (ListView) findViewById(R.id.list_view);
        newsList = new ArrayList<>();
        adapter = new NewsListAdapter(this, newsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getUrl()));
                startActivity(browserIntent);
            }
        });

        // Fetch the news articles
        new FetchNews().execute();
    }

    private class FetchNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // Make the API request
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String url = null;
            try {
                URL newsUrl = new URL("https://newsdata.io/api/1/news?apikey=pub_152402fe2ab73cd022da4506b473154d3b5ae&q=london");
                urlConnection = (HttpURLConnection) newsUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                reader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                // Parse the JSON response
                JSONObject root = new JSONObject(builder.toString());
                JSONArray articles = root.getJSONArray("results");
                for (int i = 0; i < articles.length(); i++) {
                    JSONObject articleJson = articles.getJSONObject(i);
                    String title = articleJson.getString("title");
                    String description = articleJson.getString("description");
                    url = articleJson.getString("link");
                    News news = new News(title, description, url);
                    newsList.add(news);
                }
            } catch (Exception e) {
                Log.d("Headers", urlConnection.getHeaderFields().toString());
                Log.e("Exception", e.getMessage(), e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        Log.e("Exception", e.getMessage(), e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}