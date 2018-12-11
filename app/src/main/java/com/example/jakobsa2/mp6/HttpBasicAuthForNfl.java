package com.example.jakobsa2.mp6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class HttpBasicAuthForNfl {

    public static String main(String[] args) {

        try {
            String line;
            URL url = new URL("https://api.mysportsfeeds.com/v2.0/pull/nfl/current/date/20181209/games.json");
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
}
