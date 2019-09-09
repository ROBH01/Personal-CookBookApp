package com.example.cookbook;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;


public class singleCategoryAdapter extends RecyclerView.Adapter<singleCategoryAdapter.MyViewHolder> {
    private Context mContext;
    private List<Recipee> recipeeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    public singleCategoryAdapter(Context mContext, List<Recipee> recipeeList) {
        this.mContext = mContext;
        this.recipeeList = recipeeList;
    }

    @Override
    public singleCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipee_card, parent, false);

        return new singleCategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final singleCategoryAdapter.MyViewHolder holder, int position) {
        Recipee recipee = recipeeList.get(position);
        holder.title.setText(recipee.getName());
        holder.count.setText(recipee.getNumOfViews() + " views");
        //holder.thumbnail.setImageDrawable(recipee.getThumbnail());
        Glide.with(mContext).load(recipee.getImageLoc()).into(holder.thumbnail);
        // loading recipee cover using Glide library
        //Glide.with(mContext).load(recipee.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipees(holder.thumbnail,holder.title.getText().toString());
            }
        });

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    private void openRecipees(View view,String title) {
        //open the categories recipees
        if(title.equals("Strawberry and Blueberry Pancakes")) {
            BackgroundWorker bkw = new BackgroundWorker(mContext);

            bkw.execute("specificRecipee",title);
        }
        else {
            Toast.makeText(mContext, title+" Not yet implemented", Toast.LENGTH_SHORT).show();
        }
        //Intent openRecipeSpecs = new Intent(mContext, RecipeSpecificationsActivity.class);
        //mContext.startActivity(openRecipeSpecs);
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_recipee, popup.getMenu());
        popup.setOnMenuItemClickListener(new singleCategoryAdapter.MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Added to favourites", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return recipeeList.size();
    }
}
