package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText password, email,cnfpassword;
    private FirebaseAuth firebaseAuth;
    Button signup;
    ImageView back_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        email = findViewById(R.id.email_signup);
        password = findViewById(R.id.signuppassword);
        signup = findViewById(R.id.sign_up_button);
        back_welcome = findViewById(R.id.back_button1);
        firebaseAuth = FirebaseAuth.getInstance();
        cnfpassword = findViewById(R.id.signupcnfpassword);

        back_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),welcome.class));
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1 = password.getText().toString();
                String password2 = cnfpassword.getText().toString();
                String Email = email.getText().toString();
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    Toast.makeText(register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1.length() < 6) {
                    Toast.makeText(register.this, "Password should contain minimum 6 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1.equals(password2)) {
                    firebaseAuth.createUserWithEmailAndPassword(Email, password1)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(register.this, "Registeration failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });

                }
                else {
                    Toast.makeText(register.this, "Password not Equals to confirm password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
