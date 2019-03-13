package com.example.workersside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MealsForPreparation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_for_preparation);
        GridView gridView = findViewById(R.id.gridview);
        final MealAdapter MealAdapter = new MealAdapter(this,meals);
        gridView.setAdapter(MealAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Meal meal = meals[position];
                meal.toggleFavorite();

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                MealAdapter.notifyDataSetChanged();
            }
        });
    }

    private Meal[] meals = {
        new Meal(R.string.All_day_breakfast,R.string.sprite,R.string.crisps),
        new Meal(R.string.prawn_mayo,R.string.coca,R.string.crisps),
        new Meal(R.string.BLT,R.string.buxton,R.string.crisps)
    };
}
