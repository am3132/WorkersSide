package com.example.workersside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

public class MealsForPreparation extends AppCompatActivity {


    Firebase mRef = new Firebase("https://mealdealed.firebaseio.com/orders");

   ArrayList<String> mItems = new ArrayList<>();
   ArrayAdapter mAdapter;
   ListView mListView;







    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_for_preparation);

       // mListView = ((ListView) findViewById(R.id.ListView));

    }

    protected void onStart() {
        super.onStart();

   mListView.setAdapter(mAdapter);
    }
    private Meal[] meals = {
        new Meal(R.string.All_day_breakfast,R.string.sprite,R.string.crisps),
        new Meal(R.string.prawn_mayo,R.string.coca,R.string.crisps),
        new Meal(R.string.BLT,R.string.buxton,R.string.crisps)
    };
}
