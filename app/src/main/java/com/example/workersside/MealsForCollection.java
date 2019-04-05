package com.example.workersside;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MealsForCollection extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Meal> collectionMealArrayList;
    MyCollectionRecyclerViewAdapter adapter;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectionMealArrayList = new ArrayList<>();
        setContentView(R.layout.activity_meals_for_collection);
        setUpRecyclerView();
        setUpFireBase();
        loadDataFromFirebase();
        setUpUpdateButton();


        Button btn3 = findViewById(R.id.to_barcode_scanner);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MealsForCollection.this, BarcodeScanner.class));
            }
        });
    }

    private void setUpUpdateButton() {
        updateButton = findViewById(R.id.collectionUpdateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDataFromFirebase();
            }
        });
    }


    public void loadDataFromFirebase() {

        if(collectionMealArrayList.size()>0)
            collectionMealArrayList.clear();
        db.collection("ReadyToCollect")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot querySnapshot: task.getResult()){
                            Meal meal = new Meal(
                                    querySnapshot.getId(),
                                    querySnapshot.getString("customerUniId"),
                                    querySnapshot.getString("main"),
                                    querySnapshot.getString("snack"),
                                    querySnapshot.getString("drink"));
                            collectionMealArrayList.add(meal);
                        }
                        adapter = new MyCollectionRecyclerViewAdapter(MealsForCollection.this,collectionMealArrayList);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MealsForCollection.this,"Problem --1--",Toast.LENGTH_LONG).show();
                        Log.w("--1--",e.getMessage());
                    }
                });

        for(int i =0; i < collectionMealArrayList.size(); i++){
            Log.d("Data", collectionMealArrayList.get(i).toString());
        }
    }

    public ArrayList<Meal> getCollectionMealArrayList() {return collectionMealArrayList; }


    private void setUpFireBase() {
        db = FirebaseFirestore.getInstance();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.collectionRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
