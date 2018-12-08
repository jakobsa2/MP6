package com.example.jakobsa2.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NFL extends AppCompatActivity {
    public static final String EXTRA_NUMBER_4 = "com.example.jakobsa2.finalprojectui.EXTRA_NUMBER_4";
    public static final String EXTRA_NUMBER_5 = "com.example.jakobsa2.finalprojectui.EXTRA_NUMBER_5";
    public static final String EXTRA_NUMBER_6 = "com.example.jakobsa2.finalprojectui.EXTRA_NUMBER_6";

    private Button home;
    private Button toNba;
    private Button nflLookup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfl);
        home = findViewById(R.id.home_nfl);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nflHome();
            }
        });
        toNba = findViewById(R.id.to_nba);
        toNba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNba();
            }
        });
        nflLookup = findViewById(R.id.nfl_lookup);
        nflLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nflLookup();
            }
        });
    }

    public void nflHome() {
        Intent open = new Intent(this, MainActivity.class);
        startActivity(open);
    }
    public void toNba() {
        Intent open = new Intent(this, NBA.class);
        startActivity(open);
    }
    public void nflLookup() {
        EditText nflMonth = (EditText) findViewById(R.id.nfl_month);
        int sendNflMonth = Integer.parseInt(nflMonth.getText().toString());
        EditText nflDay = (EditText) findViewById(R.id.nfl_day);
        int sendNflDay = Integer.parseInt(nflDay.getText().toString());
        EditText nflYear = (EditText) findViewById(R.id.nfl_year);
        int sendNflYear = Integer.parseInt(nflYear.getText().toString());

        Intent intent = new Intent(this, RunNflApi.class);
        intent.putExtra(EXTRA_NUMBER_4, sendNflMonth);
        intent.putExtra(EXTRA_NUMBER_5, sendNflDay);
        intent.putExtra(EXTRA_NUMBER_6, sendNflYear);
        startActivity(intent);
    }
}
