package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class welcome extends AppCompatActivity {

    TextView login;
    Button getstarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        login = findViewById(R.id.log_in_link);
        getstarted = findViewById(R.id.get_started_button);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(welcome.this,R.style.BottomSheetDialogTheme);
                bottomSheetDialog.setContentView(R.layout.get_started_dialog);
                bottomSheetDialog.setCanceledOnTouchOutside(false);

                ImageButton close;
                Button demo, register;

                close= bottomSheetDialog.findViewById(R.id.close_dialog);
                demo= bottomSheetDialog.findViewById(R.id.demo_button);
                register= bottomSheetDialog.findViewById(R.id.register_button);
                bottomSheetDialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                demo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),demo_activity.class));
                        finish();
                    }
                });

                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),register.class));
                        finish();
                    }
                });


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sign_in.class));
                finish();
            }
        });
    }
}
