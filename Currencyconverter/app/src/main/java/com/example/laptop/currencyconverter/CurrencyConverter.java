package com.example.laptop.currencyconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CurrencyConverter extends AppCompatActivity {

    EditText editText;
    Button button1;
    Button button2;
    TextView textView1;
    TextView textView2;
    Double dollar, euro;
    DecimalFormat df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    public void dollar(View view){
        df = new DecimalFormat("$ ###,###.##");
        try {
            String inputtext = editText.getText().toString();
            Double input = Double.parseDouble(inputtext);
            euro = input * 1.1058;
            String uitkomst = df.format(euro);
            textView2.setText(uitkomst);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void euro(View view){
        df = new DecimalFormat("€ ###,###.##");
        try {
            String inputtext = editText.getText().toString();
            Double input = Double.parseDouble(inputtext);
            dollar = input * 0.904322662;
            String uitkomst = df.format(dollar);
            textView2.setText(uitkomst);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_currency_converter, menu);
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
