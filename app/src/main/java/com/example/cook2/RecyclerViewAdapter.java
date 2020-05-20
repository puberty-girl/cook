package com.example.cook2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cook2.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<Recipes> mRecipes = new ArrayList<>();


    public void setItems(List<Recipes> recipes) {
        mRecipes.addAll(recipes);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mRecipes.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d( TAG, "onBindViewHolder: called.");
        holder.bind(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    } //кол-во объектов в листайтеме

    public class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView image ;
        TextView imageName;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            imageName=itemView.findViewById(R.id.image_name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }

        public void bind(Recipes recipes) {
            imageName.setText(recipes.getName());
            Glide.with (itemView.getContext()).asBitmap().load(recipes.getImage())//откуда идет
                    .into(image);
            String photoUrl = recipes.getImage();
            image.setVisibility(photoUrl != null ? View.VISIBLE : View.GONE);
        }
    }
}
