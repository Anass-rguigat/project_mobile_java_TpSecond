package com.example.secondtp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button exit, addBtn;
    ListView lvs;
    List<Employe> employeList;
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

        employeList = dbHelper.getAllEmployes();
        adapter = new ListEtdAdapter(employeList, this);
        lvs.setAdapter(adapter);

        exit.setOnClickListener(v -> finish());
        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity1.class);
            startActivity(intent);
        });
    }
}
