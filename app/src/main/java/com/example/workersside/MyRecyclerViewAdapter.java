package com.example.workersside;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHodler> {

    MealsForPreparation mealsForPreparation;
    ArrayList<Meal> mealArrayList;


    public MyRecyclerViewAdapter(MealsForPreparation mealsForPreparation, ArrayList<Meal> mealArrayList){
        this.mealsForPreparation = mealsForPreparation;
        this.mealArrayList = mealArrayList;
    }


    @NonNull
    @Override
    public MyRecyclerViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mealsForPreparation.getBaseContext());
        View view = layoutInflater.inflate(R.layout.meal_layout,parent,false);

        return new MyRecyclerViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHodler holder, int position) {
        holder.mMain.setText(mealArrayList.get(position).getMain());
        holder.mSide.setText(mealArrayList.get(position).getSide());
        holder.mDrink.setText(mealArrayList.get(position).getDrink());
    }

    @Override
    public int getItemCount() {
        return mealArrayList.size();
    }
}
