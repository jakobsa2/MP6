package com.example.jakobsa2.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NBA extends AppCompatActivity {
    public static final String EXTRA_NUMBER_1 = "com.example.jakobsa2.finalprojectui.EXTRA_NUMBER_1";
    public static final String EXTRA_NUMBER_2 = "com.example.jakobsa2.finalprojectui.EXTRA_NUMBER_2";
    public static final String EXTRA_NUMBER_3 = "com.example.jakobsa2.finalprojectui.EXTRA_NUMBER_3";

    private Button home;
    private Button toNfl;
    private Button nbaLookup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb);
        home = findViewById(R.id.home_nba);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbaHome();
            }
        });
        toNfl = findViewById(R.id.to_nfl);
        toNfl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNfl();
            }
        });
        nbaLookup = findViewById(R.id.nba_lookup);
        nbaLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbaLookup();
            }
        });
    }

    public void nbaHome() {
        Intent open = new Intent(this, MainActivity.class);
        startActivity(open);
    }
    public void toNfl() {
        Intent open = new Intent(this, NFL.class);
        startActivity(open);
    }
    public void nbaLookup() {
        EditText nbaMonth = (EditText) findViewById(R.id.nba_month);
        int sendNbaMonth = Integer.parseInt(nbaMonth.getText().toString());
        EditText nbaDay = (EditText) findViewById(R.id.nba_day);
        int sendNbaDay = Integer.parseInt(nbaDay.getText().toString());
        EditText nbaYear = (EditText) findViewById(R.id.nba_year);
        int sendNbaYear = Integer.parseInt(nbaYear.getText().toString());

        Intent intent = new Intent(this, RunNbaApi.class);
        intent.putExtra(EXTRA_NUMBER_1, sendNbaMonth);
        intent.putExtra(EXTRA_NUMBER_2, sendNbaDay);
        intent.putExtra(EXTRA_NUMBER_3, sendNbaYear);
        startActivity(intent);
    }
}
