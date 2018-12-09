package com.example.jakobsa2.mp6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * the class for NBA.
 */
public class HttpBasicAuthForNba {
    /**
     * @param args the string to be taken.
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.mysportsfeeds.com/v2.0/pull/nba/{season}/date/{date}/games.{format}");
            String encoding = Base64.getEncoder().encodeToString(("ee556f63-0aba-4fee-bd4c-76479a:MYSPORTSFEEDS").getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
