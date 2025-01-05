package com.example.secondtp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    EditText nom, prenom, telephone, email,search;
    Button deleteEmploye, editEmploye, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        search = findViewById(R.id.Search);
        nom = findViewById(R.id.Nom);
        prenom = findViewById(R.id.Prenom);
        telephone = findViewById(R.id.Telephone);
        email = findViewById(R.id.Email);
        back = findViewById(R.id.back1);
        editEmploye = findViewById(R.id.btnEdit);
        deleteEmploye = findViewById(R.id.btnDelete);
        // Back button click listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i1);
            }
        });
        editEmploye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        deleteEmploye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logic delete
            }
        });












    }
}
