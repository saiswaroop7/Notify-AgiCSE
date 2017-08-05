package com.example.sai.notify_agicse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sai on 05-08-2017.
 */

public class Register extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText name;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
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
                    Toast.makeText(getApplicationContext(),"Please enter valid details!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Success();
                }
            }
        });
    }
    public void Success() {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
    public boolean register()
    {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
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
