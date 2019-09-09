package com.example.cookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecoverActivity extends AppCompatActivity {

    private boolean validationPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        final Button btnRecoverPassword = findViewById(R.id.btn_recover_pwd);
        btnRecoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.email_recover);
                String emailString = email.getText().toString(); // get String from EditText

                validateEmail(emailString); // validate email

                if (validationPassed) {
                    // validation has passed
                    //TODO: send email to user to recover password
                    recoverPassword(btnRecoverPassword);
                }
            }
        });
    }

    private void validateEmail(String email) {
        validationPassed = true; // reset validation variable to run validation checks again

        if (email.isEmpty()) {
            validationPassed = false;

            Toast toast = Toast.makeText(this, "Please enter an email.", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // check email against regex
            validationPassed = false;

            Toast toast = Toast.makeText(this, "Please enter a valid email.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void recoverPassword(View view) {
        Toast toast = Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT);
        toast.show();
    }

}
