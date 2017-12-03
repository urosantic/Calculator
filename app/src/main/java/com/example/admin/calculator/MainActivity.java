package com.example.admin.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView displayNum;

    String display = "";
    String sign = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayNum = findViewById(R.id.etDisplay);

    }

    public void updateDisplay() {
        displayNum.setText(display);
    }

    public void buttonClicked(View view) {
            Button button = (Button) view;
            display += button.getText().toString();
            updateDisplay();

    }

    public void buttonClear(View view) {
        display = "";
        updateDisplay();
        sign = "";
    }

    public void clickSing(View view) {
        if (!(display.equals(""))) {
            Button button = (Button) view;
            if (sign.equals("")) {
                sign = button.getText().toString();
                display += sign;
            } else {
                display = display.substring(0, display.length() - 1) + button.getText().toString();
            }
            updateDisplay();
        }
        else {
            Toast.makeText(getApplicationContext(),"Press a number first",Toast.LENGTH_SHORT).show();
        }
    }

    public void equals(View view) {
        Pattern pattern = Pattern.compile("\\d+[-/+x%]\\d+");
        Matcher matcher = pattern.matcher(display);

        if (matcher.matches()) {

            String[] parts = display.split("[-/+x%]");

            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[1]);


            if (sign.equals("+")) {
                display = Double.toString(num1 + num2);
            } else if (sign.equals("-")) {
                display = Double.toString(num1 - num2);
            } else if (sign.equals("x")) {
                display = Double.toString(num1 * num2);
            } else if (sign.equals("/")) {
                display = Double.toString(num1 / num2);
            } else if (sign.equals("%")) {
                display = Double.toString(num1 % num2);
            }

            updateDisplay();
            sign = "";
        }
        else {
            Toast.makeText(MainActivity.this,"Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void xFacctorial(View view) {
        Pattern pattern = Pattern.compile("\\d+|\\d+.\\d*");
        Matcher matcher = pattern.matcher(display);

        if (matcher.matches()) {
            double num = Double.parseDouble(display);
            double temp = 1;
            if (view.getId() == R.id.buttonXFac) {
                for (double i = 1; i <= num; i++) {
                    temp *= i;
                }
                display = String.valueOf(temp);
            }
            else if (view.getId() == R.id.buttonX2) {
                temp = num * num;
                display = String.valueOf(temp);
            }
            updateDisplay();
        }
    }

}
