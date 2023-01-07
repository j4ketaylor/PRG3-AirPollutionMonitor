package com.example.PRG3AirPollutionMonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {

    private Context context;
    private List<News> newsList;

    public NewsListAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.news_article, parent, false);
        }

        News news = newsList.get(position);

        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_title);
        textViewTitle.setText(news.getTitle());

        TextView textViewDescription = (TextView) view.findViewById(R.id.text_view_description);

        // Limit description to 300 characters
        int charLimit = 300;
        String fullDescription = news.getDescription();
        String description = fullDescription.substring(0, Math.min(fullDescription.length(), charLimit));

        // Add " ..." to the end of the description if it was cut off
        if (fullDescription.length() > charLimit) {
            description += " ...";
        }

        // Set the description to default if it is empty
        if (description != null && !description.equals("null")) {
            textViewDescription.setText(description);
        } else {
            textViewDescription.setText("No description available");
        }

        return view;
    }
}