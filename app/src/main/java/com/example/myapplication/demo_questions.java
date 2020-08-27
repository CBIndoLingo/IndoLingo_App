package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class demo_questions extends AppCompatActivity {

    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_questions);

        close= findViewById(R.id.demo_close_question);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(demo_questions.this,welcome.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
