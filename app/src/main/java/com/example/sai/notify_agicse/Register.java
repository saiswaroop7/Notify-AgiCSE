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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sai on 05-08-2017.
 */

public class Register extends AppCompatActivity {
    String mail, pass, fname, repassword, id;
    private EditText email,name,password,repass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://notify-agicse.firebaseio.com/Users");
        Button b1 = (Button) findViewById(R.id.registration);
        email=(EditText) findViewById(R.id.input_email);
        repass=(EditText) findViewById(R.id.input_repassword);
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

                    id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReference.child(id).child("Email").setValue(email.getText().toString());
                    databaseReference.child(id).child("Password").setValue(password.getText().toString());
                    databaseReference.child(id).child("Name").setValue(name.getText().toString());
                    Toast.makeText(Register.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(Register.this,"Registration Error. Please try again",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
    public boolean register()
    {
        mail = email.getText().toString().trim();
        pass = password.getText().toString().trim();
        repassword = repass.getText().toString();
        fname = name.getText().toString();
        boolean valid = true;
        if (mail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }
        if (fname.isEmpty())
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
        if(!pass.equals(repassword))
        {
            repass.setError("Password does not match");
            valid=false;
        }
        else {
            repass.setError(null);
        }
        return valid;
    }
}
