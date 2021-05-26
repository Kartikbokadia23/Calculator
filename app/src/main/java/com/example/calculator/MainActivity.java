package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private float value1 = 0, value2 = 0, result;
    private TextView textResultScreen, textDisplayScreen;
    private boolean addition, subtract, multiplication, division, remainder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNumericOnClickListener();
        setOperatorOnClickListener();
        setFunctionalOnClickListener();
        textResultScreen = (TextView) findViewById(R.id.textView_result);
        textDisplayScreen = (TextView) findViewById(R.id.textView_display);
    }

    private void setNumericOnClickListener() {
        int[] numericButtons = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5,
                R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            textResultScreen.setText(textResultScreen.getText() + "" + (button.getText()));
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        int[] operatorButtons = {R.id.button_add, R.id.button_subtract, R.id.button_multiply, R.id.button_divide,
                R.id.button_remainder};
        View.OnClickListener listener = v -> {
            if (textResultScreen.getText().length() != 0) {
                Button button = (Button) v;
                value1 = Float.parseFloat(textResultScreen.getText() + "");
                String operator = button.getText() + "";
                switch (operator) {
                    case "+":
                        addition = true;
                        break;
                    case "-":
                        subtract = true;
                        break;
                    case "*":
                        multiplication = true;
                        break;
                    case "/":
                        division = true;
                        break;
                    case "%":
                        remainder = true;
                        break;

                }
                textResultScreen.setText(null);
                textDisplayScreen.setText(value1 + " " + button.getText());
            }
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setFunctionalOnClickListener() {
        int[] functionalButtons = {R.id.button_delete, R.id.button_dot, R.id.button_equal};
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            switch (button.getText().toString()) {
                case "=":
                    if ((addition || subtract || multiplication || division || remainder) && textResultScreen.getText().length() != 0) {
                        value2 = Float.parseFloat(textResultScreen.getText() + "");
                        textDisplayScreen.setText(textDisplayScreen.getText() + " " + value2);

                        if (addition) {
                            result = add();
                        } else if (subtract) {
                            result = subtract();
                        } else if (multiplication) {
                            result = multiply();
                        } else if (division) {
                            if(value2==0){
                                textResultScreen.setText("Cant be divided by zero");
                                return;
                            }
                            else{
                                result = divide();
                            }
                        } else if (remainder) {
                            result = remainder();
                        }


                        if (result == (int) result) {
                            textResultScreen.setText((int) result + "");
                        } else {
                            textResultScreen.setText(result + "");
                        }

                        String res = textDisplayScreen.getText() + " = " + textResultScreen.getText();
                        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Second value not found", Toast.LENGTH_LONG).show();
                    }
                    break;

                case ".":
                    textResultScreen.setText(textResultScreen.getText() + ".");
                    break;

                case "Del":
                    textResultScreen.setText("");
                    textDisplayScreen.setText("");
                    value1 = 0;
                    value2 = 0;
                    break;
            }
        };


        for (int id : functionalButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private float add() {
        addition = false;
        return value1 + value2;
    }

    private float subtract() {
        subtract = false;
        return value1 - value2;
    }

    private float multiply() {
        multiplication = false;
        return value1 * value2;
    }

    private float divide() {
        division = false;
        return value1 / value2;
    }

    private float remainder() {
        remainder = false;
        return value1 % value2;

    }
}