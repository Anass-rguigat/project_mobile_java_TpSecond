package com.example.secondtp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    EditText nom, prenom, telephone, email, search;
    Button deleteEmploye, editEmploye, back ,searchButton;
    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        searchButton = findViewById(R.id.searchButton);
        search = findViewById(R.id.Search);
        nom = findViewById(R.id.Nom);
        prenom = findViewById(R.id.Prenom);
        telephone = findViewById(R.id.Telephone);
        email = findViewById(R.id.Email);
        back = findViewById(R.id.back1);
        editEmploye = findViewById(R.id.btnEdit);
        deleteEmploye = findViewById(R.id.btnDelete);
        dbHelper = new MyDatabaseHelper(this);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        searchButton.setOnClickListener(v -> {
            String searchId = search.getText().toString().trim();
            if (!searchId.isEmpty()) {
                Employe employe = dbHelper.getEmployeByID(Integer.parseInt(searchId));
                if (employe != null) {
                    nom.setText(employe.getNomEmploye());
                    prenom.setText(employe.getPrenomEmploye());
                    telephone.setText(employe.getTelephoneEmploye());
                    email.setText(employe.getEmailEmploye());
                } else {
                    Toast.makeText(this, "Employe not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editEmploye.setOnClickListener(v -> {
            Employe employe = new Employe();
            employe.setIdEmploye(Integer.parseInt(search.getText().toString().trim()));
            employe.setNomEmploye(nom.getText().toString().trim());
            employe.setPrenomEmploye(prenom.getText().toString().trim());
            employe.setTelephoneEmploye(telephone.getText().toString().trim());
            employe.setEmailEmploye(email.getText().toString().trim());
            dbHelper.updateEmploye(employe);
            Toast.makeText(this, "Employe updated", Toast.LENGTH_SHORT).show();
        });

        deleteEmploye.setOnClickListener(v -> {
            int id = Integer.parseInt(search.getText().toString().trim());
            dbHelper.deleteEmploye(id);
            Toast.makeText(this, "Employe deleted", Toast.LENGTH_SHORT).show();
        });
    }
}
