package com.kavita.ppf.interest_rate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavita.ppf.R;

import java.util.List;

/**
 * Created by Sharad on 24-Jun-16.
 */

public class InterestRateRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<InterestRateItem> itemList;

    public InterestRateRecyclerAdapter(List<InterestRateItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.yearly_limit_card, parent, false);
        InterestRateViewHolder vh = new InterestRateViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class InterestRateViewHolder extends RecyclerView.ViewHolder {

        public InterestRateViewHolder(View itemView) {
            super(itemView);
        }
    }
}
