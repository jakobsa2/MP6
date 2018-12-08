package com.example.jakobsa2.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button nba;
    private Button nfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nba = findViewById(R.id.main_nba);
        nba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNba();
            }
        });
        nfl = findViewById(R.id.main_nfl);
        nfl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNfl();
            }
        });
    }

    public void openNba() {
        Intent open = new Intent(this, NBA.class);
        startActivity(open);
    }
    public void openNfl() {
        Intent open = new Intent(this, NFL.class);
        startActivity(open);
    }
}
