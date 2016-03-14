package com.example.laptop.braintrainerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BrainTrainerApp extends AppCompatActivity {

    TextView highScoreTextView;
    TextView newHighTextView;
    int highScore;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_trainer_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = this.getPreferences(Context.MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0);

        highScoreTextView = (TextView) findViewById(R.id.highScoreTextView);
        newHighTextView = (TextView) findViewById(R.id.newHighTextView);
        newHighTextView.setText("");

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);

        if (score > highScore) {
            highScore = score;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore", highScore);
            editor.commit();
            newHighTextView.setText("Nieuwe high score, gefeliciteerd!");

        }
        else if(score <= highScore && score > 0){
            newHighTextView.setText("Je score = "+String.valueOf(score));
        }

        highScoreTextView.setText("High score: " + highScore);




    }

    public void start(View view){
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brain_trainer_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset_highscore) {
            highScore = 0;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore", highScore);
            editor.commit();
            highScoreTextView.setText("High score: 0");
            newHighTextView.setText("");

        }

        return super.onOptionsItemSelected(item);
    }
}
