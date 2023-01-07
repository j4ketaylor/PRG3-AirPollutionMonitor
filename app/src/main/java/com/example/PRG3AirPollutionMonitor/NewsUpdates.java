package com.example.PRG3AirPollutionMonitor;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
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

public class NewsUpdates extends AppCompatActivity implements View.OnTouchListener {

    private ListView listView;
    private NewsListAdapter adapter;
    private List<News> newsList;
    private WebView webView;
    private float startY;
    private float currentY;
    private Button newsButton;

    private static final int MIN_HEIGHT_DP = 0;
    private static final int MAX_HEIGHT_DP = 600;

    private int MIN_HEIGHT_PX;
    private int MAX_HEIGHT_PX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_updates);

        listView = findViewById(R.id.list_view);
        webView = findViewById(R.id.news_web_view);
        newsList = new ArrayList<>();
        adapter = new NewsListAdapter(this, newsList);
        listView.setAdapter(adapter);
        newsButton = findViewById(R.id.news_button);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://twitter.com/LondonAir");
        newsButton.setOnTouchListener(this);

        MIN_HEIGHT_PX = (int) (MIN_HEIGHT_DP * getResources().getDisplayMetrics().density);
        MAX_HEIGHT_PX = (int) (MAX_HEIGHT_DP * getResources().getDisplayMetrics().density);

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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return onTouchEvent(motionEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Record the start position
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // Calculate the current position
                currentY = event.getY();
                // Calculate the difference between the start and current position
                float deltaY = currentY - startY ;
                // Change the height of the layout based on the difference
                changeLayoutHeight(deltaY);
                break;
        }
        return true;
    }

    private void changeLayoutHeight(final float deltaY) {
        // Get the layout's current dimensions
        final WebView layout = findViewById(R.id.news_web_view);
        final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();

        // Calculate the new height based on the delta value
        final int startHeight = params.height;
        final int endHeight = (int) (startHeight + deltaY);

        // Create a ValueAnimator to animate the layout's height
        ValueAnimator animator = ValueAnimator.ofInt(startHeight, endHeight);
        animator.setDuration(10);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (endHeight < MIN_HEIGHT_PX) {
                    params.height = MIN_HEIGHT_PX;
                } else if (endHeight > MAX_HEIGHT_PX) {
                    params.height = MAX_HEIGHT_PX;
                } else {
                    params.height = endHeight;
                }
                layout.setLayoutParams(params);
            }
        });
        animator.start();
    }


    private class FetchNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // Make the API request
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String url = null;
            try {
                URL newsUrl = new URL("https://newsdata.io/api/1/news?apikey=pub_152402fe2ab73cd022da4506b473154d3b5ae&q=london%20&country=gb&language=en&category=environment,health,top");
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