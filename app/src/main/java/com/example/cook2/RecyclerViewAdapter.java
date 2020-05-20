package com.example.cook2;

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

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<Recipes> mRecipes = new ArrayList<>();
    private OnRecipesListener mOnRecipesListener;

    public RecyclerViewAdapter(OnRecipesListener mOnRecipesListener) {
        this.mOnRecipesListener = mOnRecipesListener;
    }

    public RecyclerViewAdapter() {
    }

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
        ViewHolder holder = new ViewHolder(view, mOnRecipesListener);
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

    public class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image ;
        TextView imageName;
        RelativeLayout parentLayout;
        OnRecipesListener mOnRecipesListener;
        public ViewHolder(@NonNull View itemView, OnRecipesListener onRecipesListener) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            imageName=itemView.findViewById(R.id.image_name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
            mOnRecipesListener = onRecipesListener;
            itemView.setOnClickListener(this);
        }

        public void bind(Recipes recipes) {
            imageName.setText(recipes.getName());
            Glide.with (itemView.getContext()).asBitmap().load(recipes.getImage())//откуда идет
                    .into(image);
            String photoUrl = recipes.getImage();
            image.setVisibility(photoUrl != null ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnRecipesListener.onRecipesClick(getAdapterPosition());
        }
    }

    public interface OnRecipesListener {
        void onRecipesClick(int position);
    }
}
