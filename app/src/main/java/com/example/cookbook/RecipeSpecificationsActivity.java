package com.example.cookbook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;

public class RecipeSpecificationsActivity extends AppCompatActivity {

    private String userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_specifications);

        userData = getIntent().getStringExtra("content");

        String[] fullSplit = userData.substring(1).split(">");
        String[] headerInfo = fullSplit[0].split("/");
        String[] difficultyInfo = fullSplit[1].split("/");
        String[] ingredientsInf = fullSplit[2].substring(1).split("/");
        String[] ingredientInf2 = fullSplit[3].substring(1).split("/");
        String[] instructionsResult = fullSplit[4].split("/");

        TextView txtCategory = findViewById(R.id.lbl_specs_category);
        TextView txtRecipeeName = findViewById(R.id.recipe_specs_title);
        TextView txtDifficulty = findViewById(R.id.lbl_difficulty);
        TextView txtPreperation = findViewById(R.id.lbl_preparation);
        TextView txtCookingTime = findViewById(R.id.lbl_cooking_time);
        TextView txtServings = findViewById(R.id.lbl_servings);
        TextView txtCost = findViewById(R.id.lbl_cost);

        ImageView recipeeImage = findViewById(R.id.img_specs_picture);
//        String imageloc = "http://student40106.bucomputing.uk/cookbook/images/"+headerInfo[1].replaceAll("\\s+","")+".png";
//        Drawable drawable = LoadImageFromWeb(imageloc);
//        recipeeImage.setImageDrawable(drawable);
//        recipeeImage.invalidateDrawable(drawable);

        LinearLayout linearLayout = findViewById(R.id.singleView);


        txtCategory.setText(headerInfo[0]);
        txtRecipeeName.setText(headerInfo[1]);
        txtDifficulty.setText("Difficulty: "+difficultyInfo[0]);
        txtPreperation.setText("Preperation Time: "+difficultyInfo[1]);
        txtCookingTime.setText("Cooking Time: "+difficultyInfo[2]);
        txtServings.setText("Servings: "+difficultyInfo[3]);
        txtCost.setText("Cost: "+difficultyInfo[4]);

        txtCategory.invalidate();
        txtRecipeeName.invalidate();
        txtDifficulty.invalidate();
        txtPreperation.invalidate();
        txtCookingTime.invalidate();
        txtServings.invalidate();
        txtCost.invalidate();

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        //layoutParams.gravity = Gravity.LEFT;
        layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)

        TextView[] txtIngredients = new TextView[ingredientsInf.length];
        for(int i = 0; i < txtIngredients.length; i++) {
            txtIngredients[i] = new TextView(this);

            txtIngredients[i].setLayoutParams(layoutParams);
            txtIngredients[i].setText(ingredientsInf[i]+" : "+ingredientInf2[i]);
            txtIngredients[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            txtIngredients[i].setBackgroundColor(0xffffdbdb); // hex color 0xAARRGGBB
            linearLayout.addView(txtIngredients[i]);
        }

        LayoutParams layoutParams2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        //layoutParams.gravity = Gravity.LEFT;
        layoutParams2.setMargins(10, 10, 10, 50); // (left, top, right, bottom)
        TextView lbl_instructions = new TextView(this);

        lbl_instructions.setLayoutParams(layoutParams2);
        lbl_instructions.setText("Instructions:");
        lbl_instructions.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        linearLayout.addView(lbl_instructions);

        // Create new LinearLayout for method stuff
        LinearLayout instructionLayout = new LinearLayout(this);
        instructionLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        instructionLayout.setOrientation(LinearLayout.VERTICAL);
        instructionLayout.setBackgroundColor(0xff99ccff);

        LayoutParams imageParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        //layoutParams.gravity = Gravity.LEFT;
        imageParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)


        ImageView[] instructionImages = new ImageView[instructionsResult.length];
        TextView[] instructionsText = new TextView[instructionsResult.length];

        for(int i = 0; i < instructionsText.length; i++) {
            String[] SplitR = instructionsResult[i].split("<");
            String txtS = SplitR[0];
            String txtURL = SplitR[1];

            instructionImages[i] = new ImageView(this);
            instructionImages[i].setLayoutParams(imageParams);
            new DownloadImageTask((ImageView) instructionImages[i])
                    .execute("http://student40106.bucomputing.uk/cookbook/images/"+txtURL);

            instructionImages[i].getLayoutParams().height += 50;

            instructionsText[i] = new TextView(this);
            instructionsText[i].setLayoutParams(layoutParams);
            instructionsText[i].setText(txtS);
            instructionsText[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            linearLayout.addView(instructionsText[i]);

            linearLayout.addView(instructionImages[i]);
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public static Drawable LoadImageFromWeb(String url) {
        Drawable d = null;
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            d = Drawable.createFromStream(is, "src name");
            return d;
        }
        catch (Exception e) {
            return null;
        }
    }
}
