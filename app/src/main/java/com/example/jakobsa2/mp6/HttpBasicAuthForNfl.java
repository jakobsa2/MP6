package com.example.jakobsa2.mp6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class HttpBasicAuthForNfl {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://api.mysportsfeeds.com/v2.0/pull/nfl/{season}/week/{week}/games.json");
            String encoding = Base64.getEncoder().encodeToString(("be69037ee-d78b-43c0-9365-3b7e59:MYSPORTSFEEDS").getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
