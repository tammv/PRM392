package com.example.ex5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calc1 extends AppCompatActivity {

    EditText edt1, edt2, edtresult;
    Button btnadd, btnsub, btnmol, btndiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calc);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edtresult = findViewById(R.id.edtresult);

        btnadd = findViewById(R.id.btnadd);
        btnsub = findViewById(R.id.btnsub);
        btnmol = findViewById(R.id.btnmol);
        btndiv = findViewById(R.id.btndiv);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd(v);
            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSub(v);
            }
        });

        btnmol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMol(v);
            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDiv(v);
            }
        });
    }

    private boolean validateInputs() {
        if (edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter values in both fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void onAdd(View view) {
        if (validateInputs()) {
            double num1 = Double.parseDouble(edt1.getText().toString());
            double num2 = Double.parseDouble(edt2.getText().toString());
            double result = num1 + num2;
            edtresult.setText(String.valueOf(result));
        }
    }

    public void onSub(View view) {
        if (validateInputs()) {
            double num1 = Double.parseDouble(edt1.getText().toString());
            double num2 = Double.parseDouble(edt2.getText().toString());
            double result = num1 - num2;
            edtresult.setText(String.valueOf(result));
        }
    }

    public void onDiv(View view) {
        if (validateInputs()) {
            double num1 = Double.parseDouble(edt1.getText().toString());
            double num2 = Double.parseDouble(edt2.getText().toString());
            if (num2 != 0) {
                double result = num1 / num2;
                edtresult.setText(String.valueOf(result));
            } else {
                Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onMol(View view) {
        if (validateInputs()) {
            double num1 = Double.parseDouble(edt1.getText().toString());
            double num2 = Double.parseDouble(edt2.getText().toString());
            double result = num1 * num2;
            edtresult.setText(String.valueOf(result));
        }
    }
}
