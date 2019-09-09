package com.example.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainMenuActivity extends AppCompatActivity {

    private String userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        userData = getIntent().getStringExtra("UserData");

        Button btnBrowseRecipes = findViewById(R.id.btn_browse_recipes);
        btnBrowseRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBrowseRecipes = new Intent(getBaseContext(), BrowseRecipesActivity.class);
                startActivity(openBrowseRecipes);
            }
        });

        Button btnMyAccount = findViewById(R.id.btn_my_account);
        btnMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMyAccountInfo = new Intent(getBaseContext(), MyAccountActivity.class);
                openMyAccountInfo.putExtra("UserData", userData);
                startActivity(openMyAccountInfo);
            }
        });

        Button btnShoppingList = findViewById(R.id.btn_shopping_list);
        btnShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openShoppingList = new Intent(getBaseContext(), ShoppingListActivity.class);
                startActivity(openShoppingList);
            }
        });

        Button btnWatchAd = findViewById(R.id.btn_advertising);
        btnWatchAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: play video ad to user, choose from a vast pool of video to show to user. Possibly code to avoid user see same ad many times?
                Intent openAds = new Intent(getBaseContext(), AdsActivity.class);
                startActivity(openAds);
            }
        });

    }
}
