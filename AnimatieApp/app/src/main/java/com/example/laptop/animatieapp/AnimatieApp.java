package com.example.laptop.animatieapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class AnimatieApp extends AppCompatActivity {

    ImageView haunter;
    ImageView gengar;
    boolean evolve;
    long duurAnimatie;
    SeekBar seekBar;
    RadioButton fadeButton;
    RadioButton translateButton;
    RadioButton rotateButton;
    boolean isFade;
    boolean isTranslate;
    boolean isRotate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animatie_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        haunter = (ImageView) findViewById(R.id.haunter);
        gengar = (ImageView) findViewById(R.id.gengar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                duurAnimatie = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        fadeButton = (RadioButton) findViewById(R.id.fadeRadioButton);
        fadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = true;
                isTranslate = false;
                isRotate = false;

                if(evolve){
                    gengar.setAlpha(0f);
                    gengar.setTranslationX(0f);
                    gengar.setScaleX(1f);
                    gengar.setScaleY(1f);
                }
                else {
                    haunter.setAlpha(0f);
                    haunter.setTranslationX(0f);
                    haunter.setScaleX(1f);
                    haunter.setScaleY(1f);
                }
            }
        });

        translateButton = (RadioButton) findViewById(R.id.translateRadioButton);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = false;
                isTranslate = true;
                isRotate = false;

                if (evolve) {
                    gengar.setAlpha(1f);
                    gengar.setTranslationX(-1000f);
                    gengar.setScaleX(1f);
                    gengar.setScaleY(1f);
                } else {
                    haunter.setAlpha(1f);
                    haunter.setTranslationX(1000f);
                    haunter.setScaleX(1f);
                    haunter.setScaleY(1f);
                }
            }
        });

        rotateButton = (RadioButton) findViewById(R.id.rotateRadioButton);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = false;
                isTranslate = false;
                isRotate = true;

                if (evolve) {
                    gengar.setAlpha(1f);
                    gengar.setTranslationX(0f);
                    gengar.setScaleX(0f);
                    gengar.setScaleY(0f);
                } else {
                    haunter.setAlpha(1f);
                    haunter.setTranslationX(0f);
                    haunter.setScaleX(0f);
                    haunter.setScaleY(0f);
                }
            }
        });
        isFade = true;
        fadeButton.setChecked(true);
        evolve = true;
        duurAnimatie = 2000l;
        seekBar.setProgress((int) duurAnimatie);
        gengar.setAlpha(0f);



    }

    public void animate(View view) {

        if (isFade) {
            fade();
        } else if (isTranslate) {
            rotate();
        } else {
            rotateAndScale();
        }
        evolve = !evolve;
    }

        private void fade() {
            if (evolve) {
                haunter.animate().alpha(0f).setDuration(duurAnimatie);
                gengar.animate().alpha(1f).setDuration(duurAnimatie);
            }
            else {
                haunter.animate().alpha(1f).setDuration(duurAnimatie);
                gengar.animate().alpha(0f).setDuration(duurAnimatie);
            }

        }


        private void rotate() {
            if (evolve) {
                haunter.animate().translationX(1000l).setDuration(duurAnimatie);
                gengar.animate().translationX(0f).setDuration(duurAnimatie);
            }
            else {
                haunter.animate().translationX(0l).setDuration(duurAnimatie);
                gengar.animate().translationX(-1000f).setDuration(duurAnimatie);
            }

        }

        private void rotateAndScale() {
            if (evolve) {
                haunter.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(duurAnimatie);
                gengar.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(duurAnimatie);
            } else {
                haunter.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(duurAnimatie);
                gengar.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(duurAnimatie);
            }

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animatie_app, menu);
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
