package com.example.secondtp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {

    EditText nom, prenom, telephone, email;
    Button submitSave, getEmploye, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        nom = findViewById(R.id.Nom);
        prenom = findViewById(R.id.Prenom);
        telephone = findViewById(R.id.Telephone);
        email = findViewById(R.id.Email);
        submitSave = findViewById(R.id.btnSubmit);
        back = findViewById(R.id.back1);
        getEmploye = findViewById(R.id.getEmploye);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i1);
            }
        });

        getEmploye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), Activity2.class);
                startActivity(i1);
            }
        });

        submitSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomInput = nom.getText().toString().trim();
                String prenomInput = prenom.getText().toString().trim();
                String telephoneInput = telephone.getText().toString().trim();
                String emailInput = email.getText().toString().trim();

                if (nomInput.isEmpty() || prenomInput.isEmpty() || telephoneInput.isEmpty() || emailInput.isEmpty()) {
                    Toast.makeText(Activity1.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Create an Employe object and set its properties
                    Employe employe = new Employe();
                    employe.setNomEmploye(nomInput);
                    employe.setPrenomEmploye(prenomInput);
                    employe.setTelephoneEmploye(telephoneInput);
                    employe.setEmailEmploye(emailInput);

                    MyDatabaseHelper db = new MyDatabaseHelper(Activity1.this);
                    db.addEmploye(employe); // Now using the Employe object

                    Toast.makeText(Activity1.this, "Added successfully", Toast.LENGTH_SHORT).show();

                    // Reset input fields after successful insertion
                    nom.setText("");
                    prenom.setText("");
                    telephone.setText("");
                    email.setText("");
                }
            }
        });
    }
}
