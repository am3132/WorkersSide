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

public class MyCollectionRecyclerViewAdapter extends RecyclerView.Adapter<MyCollectionRecyclerViewHolder> {
    private MealsForCollection mealsForCollection;
    private ArrayList<Meal> collectionMealArrayList;



    public MyCollectionRecyclerViewAdapter(MealsForCollection mealsForCollection, ArrayList<Meal> collectionMealArrayList){
        this.mealsForCollection = mealsForCollection;
        this.collectionMealArrayList = collectionMealArrayList;
}


    @NonNull
    @Override
    public MyCollectionRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mealsForCollection.getBaseContext());
        View view = layoutInflater.inflate(R.layout.meal_layout,parent,false);

        return new MyCollectionRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCollectionRecyclerViewHolder holder, final int position) {
        holder.mMain.setText(collectionMealArrayList.get(position).getMain());
        holder.mSide.setText(collectionMealArrayList.get(position).getSide());
        holder.mDrink.setText(collectionMealArrayList.get(position).getDrink());

        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMealToCollectedDatabase(position);
                deleteFromReadyToCollectDatabase(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return collectionMealArrayList.size();
    }
    private void addMealToCollectedDatabase(int position){

        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("CustomerUniId",collectionMealArrayList.get(position).getUserName());
        dataMap.put("Drink",collectionMealArrayList.get(position).getDrink());
        dataMap.put("Main",collectionMealArrayList.get(position).getMain());
        dataMap.put("Side",collectionMealArrayList.get(position).getSide());
        mealsForCollection.db.collection("Collected")

                .add(dataMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(mealsForCollection.getBaseContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                        mealsForCollection.loadDataFromFirebase();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mealsForCollection.getBaseContext(),"Unable to add --4--",Toast.LENGTH_SHORT).show();
                        Log.v("--4--",e.getMessage());
                    }
                });
    }
    private void deleteFromReadyToCollectDatabase(int position){
        mealsForCollection.db.collection("ReadyToCollect")
                .document(collectionMealArrayList.get(position).getUserName())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(mealsForCollection.getBaseContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        mealsForCollection.loadDataFromFirebase();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mealsForCollection.getBaseContext(),"Unable to delete --3--",Toast.LENGTH_SHORT).show();
                        Log.v("--3--",e.getMessage());
                    }
                });
    }
}