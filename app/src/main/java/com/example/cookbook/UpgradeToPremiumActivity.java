package com.example.cookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UpgradeToPremiumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_to_premium);
    }

    public void upgradeUser(View view) {
        //this has to be implemented the actual user pay procedure to get the money into our account, for now only a message
        Toast toast = Toast.makeText(this, "(Coming soon)Thank you for your upgrade. Enjoy all the Premium features", Toast.LENGTH_LONG);
        toast.show();
    }

}
