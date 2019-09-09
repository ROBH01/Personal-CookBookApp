package com.example.cookbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import java.util.List;


public class RecipeeAdapter extends RecyclerView.Adapter<RecipeeAdapter.MyViewHolder> {
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

    public RecipeeAdapter(Context mContext, List<Recipee> recipeeList) {
        this.mContext = mContext;
        this.recipeeList = recipeeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipee_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Recipee recipee = recipeeList.get(position);
        holder.title.setText(recipee.getName());
        holder.count.setText(recipee.getNumOfViews() + " Recipees");
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

    }

    private void openRecipees(View view,String title) {
        //open the categories recipees
        BackgroundWorker bkw = new BackgroundWorker(mContext);
        bkw.execute("recipees", title);
    }


    @Override
    public int getItemCount() {
        return recipeeList.size();
    }
}