package com.example.jakobsa2.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RunNflApi extends AppCompatActivity {
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_nfl_api);
        Intent intent = getIntent();
        int month = intent.getIntExtra(NFL.EXTRA_NUMBER_4, 12);
        int day = intent.getIntExtra(NFL.EXTRA_NUMBER_5, 25);
        int year = intent.getIntExtra(NFL.EXTRA_NUMBER_6, 2018);

        TextView m = (TextView) findViewById(R.id.nfl_api_month);
        TextView d = (TextView) findViewById(R.id.nfl_api_day);
        TextView y = (TextView) findViewById(R.id.nfl_api_year);
        m.setText("" + month);
        d.setText("" + day);
        y.setText("" + year);

        home = findViewById(R.id.home_run_nfl_api);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nflApiHome();
            }
        });
    }

    public void nflApiHome() {
        Intent open = new Intent(this, MainActivity.class);
        startActivity(open);
    }
}
