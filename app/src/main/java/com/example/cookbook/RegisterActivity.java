package com.example.cookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private boolean usernameValidationPassed, emailValidationPassed, passwordValidationPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView termsConditionsLink = findViewById(R.id.txt_terms);
        termsConditionsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Terms and Conditions coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickRegister(View view) {
        EditText firstName = findViewById(R.id.first_name_new_account);
        EditText lastName = findViewById(R.id.last_name_new_account);
        EditText username = findViewById(R.id.username_new_account);
        EditText email = findViewById(R.id.email_new_account);
        EditText password = findViewById(R.id.password_new_account);
        EditText passwordConfirm = findViewById(R.id.password_confirm_new_account);
        CheckBox termsConditions = findViewById(R.id.terms_conditions_checkbox);

        // get String's from EditText fields
        String firstNameString = firstName.getText().toString();
        String lastNameString = lastName.getText().toString();
        String usernameString = username.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String passwordConfirmString = passwordConfirm.getText().toString();

        // run validation checks
        validateUsername(usernameString);
        if (usernameValidationPassed) validateEmail(emailString);
        if (emailValidationPassed) validatePassword(passwordString, passwordConfirmString);

        if (usernameValidationPassed && emailValidationPassed && passwordValidationPassed) {
            // validation has passed. check terms & conditions have been agreed to
            if (!termsConditions.isChecked()) {
                Toast toast = Toast.makeText(this, "Please agree to the Cook Book Terms and Conditions.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                // terms & conditions have been agreed to
                // register the user
                String type = "register";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, emailString, passwordString, firstNameString, lastNameString, usernameString);
            }
        }
    }

    private void validateUsername(String username) {
        usernameValidationPassed = true; // reset validation variable to run validation checks again

        // username regex - can only contain letters, numbers, underscores and dashes
        Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");

        if (username.isEmpty()) {
            usernameValidationPassed = false;

            Toast toast = Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT);
            toast.show();
        } else if (username.length() < 3) {
            usernameValidationPassed = false;

            Toast toast = Toast.makeText(this, "Your username is too short!\nPlease enter 3 characters or more.", Toast.LENGTH_SHORT);
            toast.show();
        } else if (username.length() > 12) {
            usernameValidationPassed = false;

            Toast toast = Toast.makeText(this, "Your username is too long!\nPlease enter 12 characters or less.", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!USERNAME_PATTERN.matcher(username).matches()) { // check username against regex
            usernameValidationPassed = false;

            Toast toast = Toast.makeText(this, "Your username must only contain letters, numbers, underscores and dashes.", Toast.LENGTH_SHORT);
            toast.show();
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

    private void validatePassword(String password, String passwordConfirm) {
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
        } else if (passwordConfirm.isEmpty()) {
            passwordValidationPassed = false;

            Toast toast = Toast.makeText(this, "Please confirm your password.", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!passwordConfirm.matches(password)) {
            passwordValidationPassed = false;

            Toast toast = Toast.makeText(this, "Your passwords don't match!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
