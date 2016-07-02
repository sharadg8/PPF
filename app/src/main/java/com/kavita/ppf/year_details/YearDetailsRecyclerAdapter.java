package com.kavita.ppf.year_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View view = LayoutInflater.from(context).inflate(R.layout.year_details_card,parent,false);
        YearDetailsViewHolder vh = new YearDetailsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(position > 0) {
            YearDetailsViewHolder holder = (YearDetailsViewHolder) viewHolder;
            holder.date.setText(itemList.get(position).getMonthString());
            holder.deposit.setText(itemList.get(position).getDepositString());
            holder.balance.setText(itemList.get(position).getBalanceString());
            holder.rate.setText(itemList.get(position).getRateString());
            holder.interest.setText(itemList.get(position).getInterestString());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class YearDetailsViewHolder extends RecyclerView.ViewHolder{
        public TextView date;
        public TextView deposit;
        public TextView balance;
        public TextView rate;
        public TextView interest;

        public YearDetailsViewHolder(View itemView){
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.details_date);
            deposit = (TextView)itemView.findViewById(R.id.details_deposit);
            balance = (TextView)itemView.findViewById(R.id.details_balance);
            rate = (TextView)itemView.findViewById(R.id.details_rate);
            interest = (TextView)itemView.findViewById(R.id.details_interest);
        }

    }
}
