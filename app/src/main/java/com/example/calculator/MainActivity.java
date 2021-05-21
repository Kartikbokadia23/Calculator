package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double value1 = 0, value2 = 0;
    private int[] numericButtons = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};
    private TextView editTextScreen;
    private Button buttonAdd, buttonSub, buttonMul, buttonDivision, buttonEqual, buttonDel, buttonDot, buttonRemainder;
    boolean addition, subtract, multiplication, division, remainder, decimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNumericOnClickListener();
        editTextScreen = (TextView) findViewById(R.id.textView_display);
        buttonDot = (Button) findViewById(R.id.button_dot);
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonSub = (Button) findViewById(R.id.button_subtract);
        buttonMul = (Button) findViewById(R.id.button_multiply);
        buttonDivision = (Button) findViewById(R.id.button_divide);
        buttonRemainder = (Button) findViewById(R.id.button_remainder);
        buttonDel = (Button) findViewById(R.id.button_delete);
        buttonEqual = (Button) findViewById(R.id.button_equal);




        buttonAdd.setOnClickListener(v -> {
            if (editTextScreen.getText().length() != 0) {
                value1 = Float.parseFloat(editTextScreen.getText() + "");
                addition = true;
                decimal = false;
                editTextScreen.setText(null);
            }
        });

        buttonSub.setOnClickListener(v -> {
            if (editTextScreen.getText().length() != 0) {
                value1 = Float.parseFloat(editTextScreen.getText() + "");
                subtract = true;
                decimal = false;
                editTextScreen.setText(null);
            }
        });

        buttonMul.setOnClickListener(v -> {
            if (editTextScreen.getText().length() != 0) {
                value1 = Float.parseFloat(editTextScreen.getText() + "");
                multiplication = true;
                decimal = false;
                editTextScreen.setText(null);
            }
        });

        buttonDivision.setOnClickListener(v -> {
            if (editTextScreen.getText().length() != 0) {
                value1 = Float.parseFloat(editTextScreen.getText() + "");
                division = true;
                decimal = false;
                editTextScreen.setText(null);
            }
        });

        buttonRemainder.setOnClickListener(v -> {
            if (editTextScreen.getText().length() != 0) {
                value1 = Float.parseFloat(editTextScreen.getText() + "");
                remainder = true;
                decimal = false;
                editTextScreen.setText(null);
            }
        });

        buttonEqual.setOnClickListener(v -> {
            if (addition || subtract || multiplication || division || remainder) {
                value2 = Float.parseFloat(editTextScreen.getText() + "");
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

        buttonDel.setOnClickListener(v -> {
            editTextScreen.setText("");
            value1 = 0.0;
            value2 = 0.0;
        });

        buttonDot.setOnClickListener(v -> {
            if (decimal) {
                //do nothing or you can show the error
            } else {
                editTextScreen.setText(editTextScreen.getText() + ".");
                decimal = true;
            }

        });

    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                editTextScreen.setText(editTextScreen.getText()+""+(button.getText()));
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void add(double value1, double value2){
        editTextScreen.setText(value1 + value2 + "");
        StringBuilder result = new StringBuilder();
        result.append(value1).append(" + ").append(value2).append(" = ").append(value1+value2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        addition = false;
    }

    private void subtract(double value1, double value2){
        editTextScreen.setText(value1 - value2 + "");
        StringBuilder result = new StringBuilder();
        result.append(value1).append(" - ").append(value2).append(" = ").append(value1-value2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        subtract = false;
    }

    private void multiply(double value1, double value2){
        editTextScreen.setText(value1 * value2 + "");
        StringBuilder result = new StringBuilder();
        result.append(value1).append(" x ").append(value2).append(" = ").append(value1*value2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        multiplication = false;
    }

    private void divide(double value1, double value2){
        if(value2 == 0){
            editTextScreen.setText("Can't be divided by zero");
            Toast.makeText(getApplicationContext(), "Can't be divided by zero",Toast.LENGTH_LONG).show();
        }
        else{
            editTextScreen.setText(value1 / value2 + "");
            StringBuilder result = new StringBuilder();
            result.append(value1).append(" / ").append(value2).append(" = ").append(value1/value2);
            Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
            division = false;
        }
    }

    private void remainder(double value1, double value2){
        editTextScreen.setText(value1 % value2 + "");
        StringBuilder result = new StringBuilder();
        result.append(value1).append(" % ").append(value2).append(" = ").append(value1%value2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        remainder = false;
    }
}