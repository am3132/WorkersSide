package com.example.workersside;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CollectedViewHolder extends RecyclerView.ViewHolder {
    public TextView mMain, mSide,mDrink;
    public CollectedViewHolder(View itemView) {
        super(itemView);

        mMain = itemView.findViewById(R.id.textview_main_name);
        mSide = itemView.findViewById(R.id.textview_side_name);
        mDrink = itemView.findViewById(R.id.textview_drink_name);
    }

}
