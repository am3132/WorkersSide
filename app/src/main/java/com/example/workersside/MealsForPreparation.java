package com.example.workersside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MealsForPreparation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_for_preparation);
        GridView gridView = findViewById(R.id.gridview);
        MealAdapter MealAdapter = new MealAdapter(this,meal);
        gridView.setAdapter(MealAdapter);
    }
    private Meal[] meal = {
        new Meal(R.string.All_day_breakfast,R.string.sprite,R.string.crisps),
        new Meal(R.string.prawn_mayo,R.string.coca,R.string.crisps),
        new Meal(R.string.BLT,R.string.buxton,R.string.crisps)
    };
}
