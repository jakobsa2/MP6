package com.example.jakobsa2.mp6;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class RunNbaApi extends AppCompatActivity {
    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_nba_api);

        //Get month/day/year
        Intent intent = getIntent();

        //Set local values for month/day/year
        int month = intent.getIntExtra(NBA.EXTRA_NUMBER_1, 12);
        int day = intent.getIntExtra(NBA.EXTRA_NUMBER_2, 8);
        int year = intent.getIntExtra(NBA.EXTRA_NUMBER_3, 2018);

        //Set strings for month/day/year
        montH = Integer.toString(month);
        daY = Integer.toString(day);
        yeaR = Integer.toString(year);

        //Correct Strings for month/day/year
        if (montH.length() < 2) {
            montH = "0" + montH;
        }
        if (daY.length() < 2) {
            daY = "0" + daY;
        }

        //Run async task to get info from api
        Get task = new Get();
        task.execute(yeaR + montH + daY);

        //Set up Home Button
        home = findViewById(R.id.home_run_nba_api);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbaApiHome();
            }
        });

    }

    //Go to home
    public void nbaApiHome() {
        Intent open = new Intent(this, MainActivity.class);
        startActivity(open);
    }

    //Output the api results
    public static String[] nbaApi(String input) {
        String[] one = new String[] {input, "a", "b"};
        return one;
    }

    private static String montH;
    private static String daY;
    private static String yeaR;

    private class Get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                String line;
                URL url = new URL("https://api.mysportsfeeds.com/v2.0/pull/nba/2018-2019/date/" + strings[0] +"/games.json");
                String encoding = Base64.getEncoder().encodeToString(("ee556f63-0aba-4fee-bd4c-76479a:MYSPORTSFEEDS").getBytes());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                //connection.setDoOutput(true);
                connection.setRequestProperty("Authorization", "Basic " + encoding);
                InputStream content = (InputStream) connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(content));
                while ((line = in.readLine()) != null) {
                    return line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Error";
        }

        @Override
        protected void onPostExecute (String s) {
            String[] content = nbaApi(s);

            TextView homeTeam = (TextView) findViewById(R.id.nba_api_home);
            TextView awayTeam = (TextView) findViewById(R.id.nba_api_away);
            TextView score = (TextView) findViewById(R.id.nba_api_score);
            homeTeam.setText("" + content[0]);
            awayTeam.setText("" + content[1]);
            score.setText("" + content[2]);
        }
    }
}
