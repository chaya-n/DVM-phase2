package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class HistoryPage extends AppCompatActivity {

    Button clrbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        DatabaseHelper databaseHelper1=new DatabaseHelper(HistoryPage.this);
        List<String> history=databaseHelper1.getHistory();

        RecyclerViewHelper adapter = new RecyclerViewHelper(this,history);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clrbtn=findViewById(R.id.clrbtn);
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper1.clearHistory();
                finish();
            }
        });
    }
}