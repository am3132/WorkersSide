package com.example.workersside;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHodler> {

    private MealsForPreparation mealsForPreparation;
    private ArrayList<Meal> mealArrayList;



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
    public void onBindViewHolder(@NonNull MyRecyclerViewHodler holder, final int position) {
        holder.mMain.setText(mealArrayList.get(position).getMain());
        holder.mSide.setText(mealArrayList.get(position).getSide());
        holder.mDrink.setText(mealArrayList.get(position).getDrink());

        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMealToCollectionDatabase(position);
                deleteFromMealToPrepareDatabase(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealArrayList.size();
    }
    private void addMealToCollectionDatabase(int position){

        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("customerUniId",mealArrayList.get(position).getUserName());
        dataMap.put("drink",mealArrayList.get(position).getDrink());
        dataMap.put("main",mealArrayList.get(position).getMain());
        dataMap.put("snack",mealArrayList.get(position).getSide());
        mealsForPreparation.db.collection("ReadyToCollect").document(mealArrayList.get(position).getId())

                .set(dataMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
    private void deleteFromMealToPrepareDatabase(int position){
        mealsForPreparation.db.collection("orders")
                .document(mealArrayList.get(position).getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(mealsForPreparation.getBaseContext(),"Deleted Succesfully",Toast.LENGTH_SHORT).show();
                        mealsForPreparation.loadDataFromFirebase();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mealsForPreparation.getBaseContext(),"Unable to delete --3--",Toast.LENGTH_SHORT).show();
                        Log.v("--3--",e.getMessage());
                    }
                });
    }
}

