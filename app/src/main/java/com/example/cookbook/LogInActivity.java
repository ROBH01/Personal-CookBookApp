package com.example.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LogInActivity extends AppCompatActivity {

    private boolean emailValidationPassed, passwordValidationPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //go to recover screen
        TextView recoverLink = findViewById(R.id.recover_password);
        recoverLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recovery = new Intent(getBaseContext(), RecoverActivity.class);
                startActivity(recovery);
            }
        });

        //go to sign up screen
        TextView signUp = findViewById(R.id.sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(register);
            }
        });

    }

    public void onClickLogin(View view) {
        EditText email = findViewById(R.id.email_login);
        EditText password = findViewById(R.id.password_login);

        // get String's from EditText fields
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        // run validation checks
        validateEmail(emailString);
        if (emailValidationPassed) validatePassword(passwordString);

        if (emailValidationPassed && passwordValidationPassed) {
            // validation has passed. log the user in
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, emailString, passwordString);
        }
    }

    private void validateEmail(String email) {
        emailValidationPassed = true; // reset validation variable to run validation checks again

        if (email.isEmpty()) {
            emailValidationPassed = false;

            Toast toast = Toast.makeText(this, "Please enter an email.", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // check email against regex
            emailValidationPassed = false;

            Toast toast = Toast.makeText(this, "Please enter a valid email.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void validatePassword(String password) {
        passwordValidationPassed = true; // reset validation variable to run validation checks again

        // password regex - min. 8 characters length, at least 1 letter and 1 number
        Pattern PASSWORD_PATTERN = Pattern.compile("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Za-z]).*");

        if (password.isEmpty()) {
            passwordValidationPassed = false;

            Toast toast = Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) { // check password against regex
            passwordValidationPassed = false;

            Toast toast = Toast.makeText(this, "Your password must be 8 or more characters long, contain letters and numbers.", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}