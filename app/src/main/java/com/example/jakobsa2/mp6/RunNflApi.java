package com.example.jakobsa2.mp6;

import android.content.Intent;
import android.os.AsyncTask;
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

public class RunNflApi extends AppCompatActivity {
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_nfl_api);

        //Get month/day/year
        Intent intent = getIntent();

        //Set local values for month/day/year
        int month = intent.getIntExtra(NFL.EXTRA_NUMBER_4, 12);
        int day = intent.getIntExtra(NFL.EXTRA_NUMBER_5, 8);
        int year = intent.getIntExtra(NFL.EXTRA_NUMBER_6, 2018);

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
        Get1 task = new Get1();
        task.execute(yeaR + montH + daY);


        //Set up Home Button
        home = findViewById(R.id.home_run_nfl_api);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nflApiHome();
            }
        });

    }

        //Go to home
    public void nflApiHome() {
        Intent open = new Intent(this, MainActivity.class);
        startActivity(open);
    }

        //Output the api results
    public static String[] nflApi(String input) {
        int awayId = input.indexOf("awayScoreTotal");
        int awayTeam = input.indexOf("abbreviation");
        int homeId = input.indexOf("homeScoreTotal");
        int homeTeam = input.indexOf("abbreviation", input.indexOf("abbreviation") + 1);
        String x = input.substring(awayId + 16, input.indexOf(',', awayId + 16));
        String y = input.substring(homeId + 16, input.indexOf(',', homeId + 16));
        String[] returnMe = {x, input.substring(awayTeam + 15, awayTeam + 18), y, input.substring(homeTeam + 15, homeTeam + 17)};
        return returnMe;
    }

    private static String montH;
    private static String daY;
    private static String yeaR;

    private class Get1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                String line;
                URL url = new URL("https://api.mysportsfeeds.com/v2.0/pull/nfl/current/date/" + strings[0] + "/games.json");
                String encoding = Base64.getEncoder().encodeToString(("be6037ee-d78b-43c0-9365-3b7e59:MYSPORTSFEEDS").getBytes());
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
            String[] content = nflApi(s);

            TextView homeTeam = (TextView) findViewById(R.id.nfl_away_score);
            TextView awayTeam = (TextView) findViewById(R.id.nfl_away_team);
            TextView homeScore = (TextView) findViewById(R.id.nfl_home_score);
            TextView awayScore = (TextView) findViewById(R.id.nfl_home_team);

            homeTeam.setText("" + content[0]);
            awayTeam.setText("" + content[1]);
            homeScore.setText("" + content[2]);
            awayScore.setText("" + content[3]);
        }
    }
}

