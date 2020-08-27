package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class demo_activity extends AppCompatActivity {

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_activity);

        back=findViewById(R.id.demo_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(demo_activity.this,welcome.class);
                startActivity(intent);
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.language_demo_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Language_data[] language_data= new Language_data[]{
                new Language_data(R.drawable.english_flag,"English"),
                new Language_data(R.drawable.india_flag,"Hindi"),
                new Language_data(R.drawable.china_flag,"Chinese"),

        };

        LanguageAdapter languageAdapter= new LanguageAdapter(language_data,demo_activity.this);
        recyclerView.setAdapter(languageAdapter);

    }

}
