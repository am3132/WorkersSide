package com.example.workersside;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MealAdapter extends BaseAdapter {

    private final Meal[] meals;
    private final Context mContext;


    public MealAdapter(Context mContext, Meal[] meals){

        this.meals = meals;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        return meals.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
            // 1
            final Meal meal = meals[position];

            // 2
            if (convertView == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.meal_layout, null);
            }

            // 3
           // final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
            final TextView mainText = convertView.findViewById(R.id.textview_main_name);
            final TextView drinkText = convertView.findViewById(R.id.textview_drink_name);
            final TextView sideText = convertView.findViewById(R.id.textview_side_name);


            // 4

            mainText.setText(mContext.getString(meal.getMain()));
            drinkText.setText(mContext.getString(meal.getDrink()));
            sideText.setText(mContext.getString(meal.getSide()));

            return convertView;
        }
    }

