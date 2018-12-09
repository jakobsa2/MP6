package com.example.jakobsa2.mp6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Base64;

/**
 * the class for NBA.
 */


public class HttpBasicAuthForNba {
    private static volatile String line = "12";
    public static String nbaapi() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.mysportsfeeds.com/v2.0/pull/nba/2018-2019/date/20181208/games.json");
                    String encoding = Base64.getEncoder().encodeToString(("ee556f63-0aba-4fee-bd4c-76479a:MYSPORTSFEEDS").getBytes());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    //connection.setDoOutput(true);
                    connection.setRequestProperty("Authorization", "Basic " + encoding);
                    InputStream content = (InputStream) connection.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(content));
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                        //return line;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        thread.start();
        return line;
    }
}
