package com.example.sai.notify_agicse;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by Sai on 17-09-2017.
 */

public class Forgot extends AppCompatActivity {
    String mail;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot);
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final EditText ed = (EditText) findViewById(R.id.input_email);
        final AlertDialog alertDialog = new AlertDialog.Builder(Forgot.this).create();
        alertDialog.setTitle("Reset Successful!");
        alertDialog.setMessage("The reset link has been sent to your registered email.");
        Button b1 = (Button) findViewById(R.id.reset_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = ed.getText().toString();
                firebaseAuth.sendPasswordResetEmail(mail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Could not reset password. Please try again.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
