package com.example.timecomparison;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        final TextView phoneTime = findViewById(R.id.phoneTime);
        TextView timeNewYork = findViewById(R.id.timeNewYork);
        TextView timeLondon = findViewById(R.id.timeLondon);
        TextView timeTokyo = findViewById(R.id.timeTokyo);

        changeTime(phoneTime,"default");
        changeTime(timeNewYork,"America/New_York");
        changeTime(timeLondon,"Europe/Warsaw");
        changeTime(timeTokyo,"Asia/Tokyo");
    }

    private void setCurrentTime(final TextView textview, final String timeZone){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if(timeZone.equals("default")){sdf.setTimeZone(TimeZone.getDefault());}
        else {sdf.setTimeZone(TimeZone.getTimeZone(timeZone));}
        String dateString = sdf.format(date);
        textview.setText(dateString);
    }

    private void changeTime(final TextView textview, final String timeZone){
        setCurrentTime(textview, timeZone);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Date date = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                if(timeZone.equals("default")){sdf.setTimeZone(TimeZone.getDefault());}
                                else {sdf.setTimeZone(TimeZone.getTimeZone(timeZone));}
                                String dateString = sdf.format(date);
                                textview.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }
}


