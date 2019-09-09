package com.example.cookbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BrowseRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_recipes);

        Button btnMySavedRecipes = findViewById(R.id.btn_my_saved_recipes);
        btnMySavedRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMySavedRecipes = new Intent(getBaseContext(), SavedRecipesActivity.class);
                startActivity(openMySavedRecipes);
            }
        });
    }

    public void onClickViewAll(View view) {
        BackgroundWorker bkw = new BackgroundWorker(this);
        bkw.execute("category");
    }
}