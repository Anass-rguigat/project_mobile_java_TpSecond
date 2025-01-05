package com.example.secondtp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button exit, addBtn;
    ListView lvs;
    List<HashMap<String, String>> listeElts;
    ListEtdAdapter adapter;
    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvs = findViewById(R.id.listeStds);
        exit = findViewById(R.id.exit1);
        addBtn = findViewById(R.id.addBtn);

        dbHelper = new MyDatabaseHelper(this);

        // Fetch data from SQLite
        listeElts = dbHelper.getAllEmployes();

        // Initialize the adapter with the fetched data
        adapter = new ListEtdAdapter(listeElts, this);
        lvs.setAdapter(adapter);

        exit.setOnClickListener(v -> finish());

        addBtn.setOnClickListener(v -> {
            Intent i1 = new Intent(getApplicationContext(), Activity1.class);
            startActivity(i1);
        });
    }
}
