package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double input1 = 0, input2 = 0;
    private int[] numericButtons = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
    private TextView edt1;
    private Button buttonAdd, buttonSub, buttonMul, buttonDivision, buttonEqual, buttonDel, buttonDot, Remainder;
    boolean Addition, Subtract, Multiplication, Division, mRemainder, decimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNumericOnClickListener();
        edt1 = (TextView) findViewById(R.id.display);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        Remainder = (Button) findViewById(R.id.Remainder);
        buttonDel = (Button) findViewById(R.id.buttonDel);
        buttonEqual = (Button) findViewById(R.id.buttoneql);




        buttonAdd.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Addition = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonSub.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Subtract = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonMul.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Multiplication = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonDivision.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Division = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        Remainder.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                mRemainder = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonEqual.setOnClickListener(v -> {
            if (Addition || Subtract || Multiplication || Division || mRemainder) {
                input2 = Float.parseFloat(edt1.getText() + "");
            }

            if (Addition) {
                add(input1, input2);
            }

            if (Subtract) {
                subtract(input1, input2);
            }

            if (Multiplication) {
                multiply(input1, input2);
            }

            if (Division) {
                divide(input1, input2);
            }
            if (mRemainder) {
                remainder(input1, input2);
            }
        });

        buttonDel.setOnClickListener(v -> {
            edt1.setText("");
            input1 = 0.0;
            input2 = 0.0;
        });

        buttonDot.setOnClickListener(v -> {
            if (decimal) {
                //do nothing or you can show the error
            } else {
                edt1.setText(edt1.getText() + ".");
                decimal = true;
            }

        });

    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                edt1.setText(edt1.getText()+""+(button.getText()));
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void add(double input1, double input2){
        edt1.setText(input1 + input2 + "");
        StringBuilder result = new StringBuilder();
        result.append(input1).append(" + ").append(input2).append(" = ").append(input1+input2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        Addition = false;
    }

    private void subtract(double input1, double input2){
        edt1.setText(input1 - input2 + "");
        StringBuilder result = new StringBuilder();
        result.append(input1).append(" - ").append(input2).append(" = ").append(input1-input2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        Subtract = false;
    }

    private void multiply(double input1, double input2){
        edt1.setText(input1 * input2 + "");
        StringBuilder result = new StringBuilder();
        result.append(input1).append(" x ").append(input2).append(" = ").append(input1*input2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        Multiplication = false;
    }

    private void divide(double input1, double input2){
        if(input2 == 0){
            edt1.setText("Can't be divided by zero");
            Toast.makeText(getApplicationContext(), "Can't be divided by zero",Toast.LENGTH_LONG).show();
        }
        else{
            edt1.setText(input1 / input2 + "");
            StringBuilder result = new StringBuilder();
            result.append(input1).append(" / ").append(input2).append(" = ").append(input1/input2);
            Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
            Division = false;
        }
    }

    private void remainder(double input1, double input2){
        edt1.setText(input1 % input2 + "");
        StringBuilder result = new StringBuilder();
        result.append(input1).append(" % ").append(input2).append(" = ").append(input1%input2);
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
        mRemainder = false;
    }
}