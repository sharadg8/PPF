package com.kavita.ppf.year_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavita.ppf.R;

import java.util.List;

/**
 * Created by Sharad on 01-Jul-16.
 */

public class YearDetailsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<YearDetailsItem> itemList;

    public YearDetailsRecyclerAdapter(List<YearDetailsItem> itemList){
      this.itemList = itemList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.year_details,parent,false);
        YearDetailsViewHolder vh = new YearDetailsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class YearDetailsViewHolder extends RecyclerView.ViewHolder{
        public YearDetailsViewHolder(View itemView){
            super(itemView);
        }

    }
}
