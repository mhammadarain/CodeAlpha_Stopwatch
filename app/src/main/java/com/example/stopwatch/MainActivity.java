package com.example.stopwatch;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stopwatchTextView;
    private ImageView startButton, stopButton, resetButton;
    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();
    private Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchTextView = findViewById(R.id.stopwatchTextView);

        startButton = findViewById(R.id.startButton);

        stopButton = findViewById(R.id.stopButton);

        resetButton = findViewById(R.id.resetButton);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStopwatch();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopStopwatch();
            }
        });



        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetStopwatch();
            }
        });
    }



    private void startStopwatch() {
        if (!isRunning) {
            isRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);



            runnable = new Runnable() {
                @Override
                public void run() {
                    seconds++;
                    updateTimer(seconds);
                    handler.postDelayed(this, 1000); // Update every second
                }
            };
            handler.post(runnable);
        }
    }



    private void stopStopwatch() {
        isRunning = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        handler.removeCallbacks(runnable);
    }



    private void resetStopwatch() {
        isRunning = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        seconds = 0;
        updateTimer(seconds);
        handler.removeCallbacks(runnable);
    }




    private void updateTimer(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        stopwatchTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
    }
}
