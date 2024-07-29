package com.example.calculator_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String oldNumber = "";
    String op = "+";
    boolean isNewop = true;
    EditText ed1;
    Button lastOperatorButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ed1 = findViewById(R.id.editText);
    }

    @SuppressLint("NonConstantResourceId")
    public void numberEvent(View view) {
        if (isNewop) {
            ed1.setText("");
        }
        isNewop = false;
        String number = ed1.getText().toString();
        if (view.getId() == R.id.bu1) {
            number += "1";
        } else if (view.getId() == R.id.bu2) {
            number += "2";
        } else if (view.getId() == R.id.bu3) {
            number += "3";
        } else if (view.getId() == R.id.bu4) {
            number += "4";
        } else if (view.getId() == R.id.bu5) {
            number += "5";
        } else if (view.getId() == R.id.bu6) {
            number += "6";
        } else if (view.getId() == R.id.bu7) {
            number += "7";
        } else if (view.getId() == R.id.bu8) {
            number += "8";
        } else if (view.getId() == R.id.bu9) {
            number += "9";
        } else if (view.getId() == R.id.bu0) {
            number += "0";
        } else if (view.getId() == R.id.buDot) {
            number += ".";
        } else if (view.getId() == R.id.buPlusMinus) {
            number = "-" + number;
        }
        ed1.setText(number);
    }

    public void operatorEvent(View view) {
        resetOperatorButtonBackground();
        isNewop = true;
        oldNumber = ed1.getText().toString();
        Button button = (Button) view;
        button.setBackgroundColor(getResources().getColor(R.color.highlight_color));
        lastOperatorButton = button;

        if (view.getId() == R.id.buDivide) {
            op = "/";
        } else if (view.getId() == R.id.buMultiply) {
            op = "*";
        } else if (view.getId() == R.id.buMinus) {
            op = "-";
        } else if (view.getId() == R.id.buPlus) {
            op = "+";
        }
    }

    public void equalEvent(View view) {
        resetOperatorButtonBackground();
        String newNumber = ed1.getText().toString();
        double result = 0.0;
        if (op.equals("+")) {
            result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
        } else if (op.equals("-")) {
            result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
        } else if (op.equals("*")) {
            result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
        } else if (op.equals("/")) {
            result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
        }
        ed1.setText(String.valueOf(result));
    }

    public void acEvent(View view) {
        resetOperatorButtonBackground();
        ed1.setText("0");
        isNewop = true;
    }

    public void percentEvent(View view) {
        resetOperatorButtonBackground();
        Button button = (Button) view;
        button.setBackgroundColor(getResources().getColor(R.color.highlight_color));
        lastOperatorButton = button;

        double no = Double.parseDouble(ed1.getText().toString()) / 100;
        ed1.setText(String.valueOf(no));
        isNewop = true;
    }

    private void resetOperatorButtonBackground() {
        if (lastOperatorButton != null) {
            lastOperatorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}
