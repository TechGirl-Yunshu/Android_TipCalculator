package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView totalResultTextView;
    private TextView textViewSeekbar;
    private int seekBarResult;
    private float enteredBillFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmount = (EditText) findViewById(R.id.billAmountID);
        seekBar = (SeekBar) findViewById(R.id.percentageSeekBarID);
        calculateButton = (Button) findViewById(R.id.calculateButtonID);
        totalResultTextView = (TextView) findViewById(R.id.resultID);
        textViewSeekbar = (TextView) findViewById(R.id.textViewSeekbarID);

        calculateButton.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewSeekbar.setText(String.valueOf(seekBar.getProgress()) + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarResult = seekBar.getProgress();


            }
        });

    }

    @Override
    public void onClick(View v){
        calculate();

    }

    public void calculate(){
        float tipResult = (float) 0.0;
        float totalResult = (float) 0.0;
        if (!enteredAmount.getText().toString().equals("")) {
            enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());
            tipResult = enteredBillFloat * seekBarResult / 100;
            totalResult = tipResult + enteredBillFloat;
            totalResultTextView.setText("The tip will be: $" + String.valueOf(tipResult) + '\n' + "The total price will be: $" + String.valueOf(totalResult));
        } else {
            Toast.makeText(MainActivity.this, "Please enter a valid bill amount.", Toast.LENGTH_LONG).show();
        }

    }
}