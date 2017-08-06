package com.example.sai.notify_agicse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sai on 05-08-2017.
 */

public class Register extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText name;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    String mail;
    String pass;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
        Button b1 = (Button) findViewById(R.id.registration);
        email=(EditText) findViewById(R.id.input_email);
        password=(EditText) findViewById(R.id.input_password);
        name=(EditText) findViewById(R.id.input_name);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                register();
                if(!register())
                {
                    Toast.makeText(getApplicationContext(),"Registration Error",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else {
                    Success();
                }
            }
        });
    }
    public void Success() {
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Register.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    public boolean register()
    {
        mail = email.getText().toString().trim();
        pass = password.getText().toString().trim();
        String fn = name.getText().toString();
        boolean valid = true;
        if (mail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }
        if (fn.isEmpty())
        {
            name.setError("Field must not be empty");
            valid = false;
        } else {
            name.setError(null);
        }

        if (pass.isEmpty() || password.length() < 4 || password.length() > 15) {
            password.setError("Password length must be 4-15 characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
