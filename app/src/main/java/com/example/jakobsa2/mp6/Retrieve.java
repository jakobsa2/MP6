package com.example.jakobsa2.mp6;

import android.os.AsyncTask;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class Retrieve extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... urls) {
        String line;
        try {
            URL url = new URL("https://api.mysportsfeeds.com/v2.0/pull/nba/2018-2019/date/20181208/games.json");
            String encoding = Base64.getEncoder().encodeToString(("ee556f63-0aba-4fee-bd4c-76479a:MYSPORTSFEEDS").getBytes());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                return line;
            }
        } catch (Exception e) {
            line = "Exception Reached";
            e.printStackTrace();
        }
        return line;
    }

 //   protected void onPostExecute(RSSFeed feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    //}
}