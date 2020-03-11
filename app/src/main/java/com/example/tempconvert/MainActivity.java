package com.example.tempconvert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText fahBox;
    private EditText celBox;
    private RadioButton FtoCRadio;
    private ConstraintLayout layout;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color = 0xffffffff;
        layout = findViewById(R.id.layout);
        fahBox = findViewById(R.id.fahBox);
        celBox = findViewById(R.id.celBox);
        FtoCRadio = findViewById(R.id.FtoCRadio);
        celBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                convert(v);
                return false;
            }
        });
        fahBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               convert(v);
                return false;
            }
        });
    }

    public void colorPressed(View v){
        Intent i = new Intent(this, ColorActivity.class);
        i.putExtra("COLOR", color);
        startActivityForResult(i, 1);
    }

    public void helpPressed(View v){
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        color = data.getIntExtra("COLOR", 0xffffffff);
        layout.setBackgroundColor(color);

    }

    public void convert (View v){
        if (FtoCRadio.isChecked()) {
            String input = fahBox.getText().toString();
            if (input.length() > 0) {
                double fahValue = Double.parseDouble(input);
                double celValue = (fahValue - 32) * (5.0 / 9.0);
                celBox.setText(String.format("%.1f", celValue)); //formats to one decimal place
            } else {
                Toast.makeText(this, "No Fahrenheit value entered", Toast.LENGTH_SHORT).show();
            }
        } else {
            String input = celBox.getText().toString();
            if (input.length() > 0) {
                double celValue = Double.parseDouble(input);
                double fahValue = (celValue * (9.0 / 5.0)) + 32;
                fahBox.setText(String.format("%.1f", fahValue));
            } else {
                Toast.makeText(this, "No Celsius value entered", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
