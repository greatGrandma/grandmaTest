package com.example.preenz.lanceapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewExample";
    private List<LanceData> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LanceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new LanceAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        // Downloading data from below url
        final String url = "http://stacktips.com/?json=get_recent_posts&count=45";
        new AsyncHttpTask().execute(url);

        prepareMovieData();
    }

    private void prepareMovieData() {
        LanceData movie = new LanceData("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new LanceData("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new LanceData("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new LanceData("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new LanceData("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new LanceData("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new LanceData("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new LanceData("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new LanceData("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new LanceData("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new LanceData("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new LanceData("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new LanceData("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new LanceData("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new LanceData("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new LanceData("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    //AsynTask
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
//                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
//            progressBar.setVisibility(View.GONE);

            if (result == 1) {
//                adapter = new MyRecyclerAdapter(FeedListActivity.this, feedsList);
//                mRecyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this, "Fetched data successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }

    }

//    private void parseResult(String result) {
//        try {
//            JSONObject response = new JSONObject(result);
//            JSONArray posts = response.optJSONArray("posts");
//            feedsList = new ArrayList<>();
//
//            for (int i = 0; i < posts.length(); i++) {
//                JSONObject post = posts.optJSONObject(i);
//                FeedItem item = new FeedItem();
//                item.setTitle(post.optString("title"));
//                item.setThumbnail(post.optString("thumbnail"));
//
//                feedsList.add(item);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
