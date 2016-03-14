package com.example.laptop.eierwekker;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class EierWekker extends AppCompatActivity {

    ImageView yoshiegg;
    ImageView yoshi;
    TextView countdownTextView;
    Button startButton;
    Button resetButton;
    SeekBar seekBar;
    int timerTijd;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eier_wekker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        timerTijd = 30;

        yoshiegg = (ImageView) findViewById (R.id.yoshiegg);
        yoshi = (ImageView) findViewById(R.id.yoshi);

        countdownTextView = (TextView) findViewById(R.id.countdownTextView);
        startButton = (Button) findViewById(R.id.goButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(timerTijd);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timerTijd = progress;
                setTimerText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void setTimerText(){
        int minuten = timerTijd / 60;
        int seconden = timerTijd - minuten * 60;
        String tijdString = minuten + ":" + seconden;
        if (seconden < 10) {
            tijdString = minuten + ":0" + seconden;
        }
        countdownTextView.setText(tijdString);
    }

    public void startStopTimer(View view) {



        if (timer != null) {
            timer.cancel();
            startButton.setText("Hatch!");
            timer = null;
            seekBar.setAlpha(1f);

        }
        else {
            startButton.setText("Cancel");


            timer = new CountDownTimer(timerTijd * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                   timerTijd = (int) millisUntilFinished /1000;
                   setTimerText();
                    yoshi.setAlpha(0f);
                    yoshiegg.setAlpha(1f);
                    seekBar.setAlpha(0f);


                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yoshi);
                    mediaPlayer.start();
                    countdownTextView.setText("");
                    timer = null;
                    yoshi.setAlpha(1f);
                    yoshiegg.setAlpha(0f);
                    startButton.setAlpha(0f);
                    resetButton.setAlpha(1f);
                    seekBar.setAlpha(0f);


            }
        };
        timer.start();
        }
    }

    public void reset (View view) {
        timerTijd = 30;
        startButton.setAlpha(1f);
        resetButton.setAlpha(0f);
        yoshiegg.setAlpha(1f);
        yoshi.setAlpha(0f);
        seekBar.setAlpha(1f);
        setTimerText();
        seekBar.setProgress(timerTijd);
        startButton.setText("Hatch!");





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eier_wekker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
