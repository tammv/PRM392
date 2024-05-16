package com.example.ex5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity1 extends AppCompatActivity {
    public Button myButton1;
    public EditText myUser1;
    public EditText myPass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);
        myButton1 = findViewById(R.id.btnOk1);
        myUser1 = findViewById(R.id.editUser1);
        myPass1 = findViewById(R.id.editPassword1);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myUser1.getText().toString().equals("admin") &&
                        myPass1.getText().toString().equals("12345"))
                {
                    Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}