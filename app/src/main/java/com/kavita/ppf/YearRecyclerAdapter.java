package com.kavita.ppf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sharad on 17-Jun-16.
 */

public class YearRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> itemList;

    public YearRecyclerAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.year_card, parent, false);
        YearViewHolder rcv = new YearViewHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        YearViewHolder holder = (YearViewHolder) viewHolder;
        holder.title.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private class YearViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public YearViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.year_title);
        }
    }
}
