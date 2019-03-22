package com.example.workersside;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CollectedRecyclerViewAdapter extends RecyclerView.Adapter<CollectedViewHolder> {

    MealCollected mealCollected;
    ArrayList<Meal> mealArrayList;

    public CollectedRecyclerViewAdapter(MealCollected mealCollected, ArrayList<Meal> mealArrayList){
        this.mealCollected = mealCollected;
        this.mealArrayList = mealArrayList;
    }

    @NonNull
    @Override
    public CollectedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mealCollected.getBaseContext());
        View view = layoutInflater.inflate(R.layout.collected_meal_layout,parent,false);

        return new CollectedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectedViewHolder holder, final int position) {
        holder.mMain.setText(mealArrayList.get(position).getMain());
        holder.mSide.setText(mealArrayList.get(position).getSide());
        holder.mDrink.setText(mealArrayList.get(position).getDrink());

    }

    @Override
    public int getItemCount() {
        return mealArrayList.size();
    }

}
