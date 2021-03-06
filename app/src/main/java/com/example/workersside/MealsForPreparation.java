package com.example.workersside;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MealsForPreparation extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Meal> mealArrayList;
    MyRecyclerViewAdapter adapter;
    Button updateButton;


    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        mealArrayList = new ArrayList<>();
        setContentView(R.layout.activity_meals_for_preparation);
        setUpRecyclerView();
        setUpFireBase();
        loadDataFromFirebase();
        setUpUpdateButton();

    }

    private void setUpUpdateButton() {
        updateButton = findViewById(R.id.mUpdateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDataFromFirebase();
            }
        });
    }


    public void loadDataFromFirebase() {

        if(mealArrayList.size()>0)
            mealArrayList.clear();
            db.collection("orders")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot querySnapshot: task.getResult()){
                            Meal meal = new Meal(
                                    querySnapshot.getId(),
                                    querySnapshot.getString("customerUniId"),
                                    querySnapshot.getString("main"),
                                    querySnapshot.getString("drink"),
                                    querySnapshot.getString("snack"));
                            mealArrayList.add(meal);
                        }
                        adapter = new MyRecyclerViewAdapter(MealsForPreparation.this,mealArrayList);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MealsForPreparation.this,"Problem --1--",Toast.LENGTH_LONG).show();
                    Log.w("--1--",e.getMessage());
                }
            });

        for(int i =0; i < mealArrayList.size(); i++){
            Log.d("Data", mealArrayList.get(i).toString());
        }
    }


    private void setUpFireBase() {
        db = FirebaseFirestore.getInstance();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
