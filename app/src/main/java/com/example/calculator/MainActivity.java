package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double value1 = 0, value2 = 0;
    private int[] numericButtons = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};
    private TextView textResultScreen, textDisplayScreen;
    private int[] operatorButtons = {R.id.button_add, R.id.button_subtract, R.id.button_multiply, R.id.button_divide, R.id.button_remainder};
    private boolean addition, subtract, multiplication, division, remainder, decimal=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNumericOnClickListener();
        setOperatorOnClickListener();
        textResultScreen = (TextView) findViewById(R.id.textView_result);
        textDisplayScreen = (TextView) findViewById(R.id.textView_display);
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                textResultScreen.setText(textResultScreen.getText()+""+(button.getText()));
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textResultScreen.getText().length() != 0) {
                    Button button = (Button) v;
                    value1 = (decimal) ? Float.parseFloat(textResultScreen.getText() + "") : Integer.parseInt(textResultScreen.getText() + "");
                    String operator = button.getText()+"";
                    textDisplayScreen.setText(value1+" "+button.getText());
                    switch (operator){
                        case "+":
                            addition = true;
                            decimal = false;
                            break;
                        case "-":
                            subtract = true;
                            decimal = false;
                            break;
                        case "*":
                            multiplication = true;
                            decimal = false;
                            break;
                        case "/":
                            division = true;
                            decimal = false;
                            break;
                        case "%":
                            remainder = true;
                            decimal = false;
                            break;

                    }
                    textResultScreen.setText(null);
                }
            }
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }


        findViewById(R.id.button_equal).setOnClickListener(v -> {
            if ((addition || subtract || multiplication || division || remainder) && textResultScreen.getText().length() != 0) {
                value2 = (decimal) ? Float.parseFloat(textResultScreen.getText() + "") : Integer.parseInt(textResultScreen.getText() + "");
                textDisplayScreen.setText(textDisplayScreen.getText()+" "+value2);
            }
            else{
                Toast.makeText(getApplicationContext(), "Second value not found",Toast.LENGTH_LONG).show();
            }

            if (addition) {
                add(value1, value2);
            }

            if (subtract) {
                subtract(value1, value2);
            }

            if (multiplication) {
                multiply(value1, value2);
            }

            if (division) {
                divide(value1, value2);
            }

            if (remainder) {
                remainder(value1, value2);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(v -> {
            textResultScreen.setText("");
            textDisplayScreen.setText("");
            value1 = 0;
            value2 = 0;
        });

        findViewById(R.id.button_dot).setOnClickListener(v -> {
            if (decimal) {
                //do nothing or you can show the error
            } else {
                textResultScreen.setText(textResultScreen.getText() + ".");
                decimal = true;
            }
        });

    }

    private void add(double value1, double value2){
        textResultScreen.setText(value1 + value2 + "");
        String result = value1 + " + " + value2 + " = " +(value1+value2);
        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        addition = false;
    }

    private void subtract(double value1, double value2){
        textResultScreen.setText(value1 - value2 + "");
        String result = value1 + " - " + value2 + " = " +(value1-value2);
        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        subtract = false;
    }

    private void multiply(double value1, double value2){
        textResultScreen.setText(value1 * value2 + "");
        String result = value1 + " * " + value2 + " = " +(value1*value2);
        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        multiplication = false;
    }

    private void divide(double value1, double value2){
        if(value2 == 0){
            textResultScreen.setText("Can't be divided by zero");
            Toast.makeText(getApplicationContext(), "Can't be divided by zero",Toast.LENGTH_LONG).show();
        }
        else{
            textResultScreen.setText(value1 / value2 + "");
            String result = value1 + " / " + value2 + " = " +(value1/value2);
            Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
            division = false;
        }
    }

    private void remainder(double value1, double value2){
        textResultScreen.setText(value1 % value2 + "");
        String result = value1 + " % " + value2 + " = " +(value1%value2);
        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        remainder = false;
    }
}