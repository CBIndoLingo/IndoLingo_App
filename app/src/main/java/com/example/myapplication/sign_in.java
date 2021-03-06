package com.example.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in extends AppCompatActivity {

    EditText password, email;
    private FirebaseAuth firebaseAuth;
    Button login;
    ProgressDialog progressDialog;
    ImageView back_welcome;
    TextView forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        email = findViewById(R.id.email_login);
        password = findViewById(R.id.loginpassword);
        login = findViewById(R.id.sign_in_button);
        forgotpass = findViewById(R.id.forgot_password);
        back_welcome = findViewById(R.id.back_button);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendailog();
            }
        });

        back_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),welcome.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(true);

                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(sign_in.this, "Enter email", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(sign_in.this, "Enter password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(sign_in.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    finish();

                                } else {
                                    Toast.makeText(sign_in.this,"Login failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

    }

    private void showProgressBar(boolean b) {
        progressDialog= new ProgressDialog(sign_in.this);
        if(b==true){
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        }
        if(b==false){
            progressDialog.dismiss();
        }
    }

    private void opendailog() {
        final Dialog dialog = new Dialog(sign_in.this);
        dialog.setContentView(R.layout.forgotpass);
        dialog.setCanceledOnTouchOutside(false);

        final EditText emailforgot = dialog.findViewById(R.id.forgetemail);
        Button cancelforgot = dialog.findViewById(R.id.forgotcancel);
        Button okforgot = dialog.findViewById(R.id.forgotok);

        cancelforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        okforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = emailforgot.getText().toString();
                if (TextUtils.isEmpty(s)){
                    Toast.makeText(sign_in.this,"Enter an email",Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(sign_in.this,"Password reset email has been sent to your email.",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(sign_in.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}
