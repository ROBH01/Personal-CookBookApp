package com.example.cookbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyAccountActivity extends AppCompatActivity {
    private String userData;
    private String usrName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        //TODO:not working, crash at regex
        userData = getIntent().getStringExtra("UserData");

        //setting labels to userData retrieved from the database
        TextView lblFName = findViewById(R.id.txt_my_account_get_username);
        TextView lblLName = findViewById(R.id.txt_my_account_get_email);
        TextView lblUType = findViewById(R.id.txt_my_account_get_membership);

        String[] userDataS = userData.split("/");

        lblFName.setText(userDataS[0]);
        usrName = userDataS[0];
        lblLName.setText(userDataS[1]);
        if (userDataS[2].equals("1")) {
            lblUType.setText("Standard");
        }
        else {
            lblUType.setText("Premium");
        }

        //TODO: show page of upgrade with options
        Button btnUpgradeToPremium = findViewById(R.id.btn_upgrade);
        btnUpgradeToPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openUpgradeActivity = new Intent(getBaseContext(), UpgradeToPremiumActivity.class);
                startActivity(openUpgradeActivity);
            }
        });
    }

    //delete all info about THIS user from the database, also ask user to confirm and let user know if succeeded or not
    public void OnClickDelete(View view) {
        BackgroundWorker bgw = new BackgroundWorker(this);
        bgw.execute("delete",usrName);
    }

    public void logOutUser(View view) {
        Toast toast = Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT);
        toast.show();
    }
}
