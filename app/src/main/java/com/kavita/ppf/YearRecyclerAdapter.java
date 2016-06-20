package com.kavita.ppf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Sharad on 17-Jun-16.
 */

public class YearRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<YearItem> itemList;

    public YearRecyclerAdapter(List<YearItem> itemList) {
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
        DecimalFormat df = new DecimalFormat("-##");
        DecimalFormat nf = new DecimalFormat("##,##,##,###");
        YearViewHolder holder = (YearViewHolder) viewHolder;
        holder.title.setText(""+itemList.get(position).getYear()+String.format("-%02d", (itemList.get(position).getYear()+1)%100));
        holder.invest.setText(nf.format(itemList.get(position).getInvest()));
        holder.interest.setText(nf.format(itemList.get(position).getInterest()));
        holder.balance.setText(nf.format(itemList.get(position).getBalance()));
        float values[] = { itemList.get(position).getInvest(), itemList.get(position).getInterest() };
        holder.chart.setValues(values);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private class YearViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView invest;
        public TextView interest;
        public TextView balance;
        public PieChartView chart;

        public YearViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.year_title);
            invest = (TextView) itemView.findViewById(R.id.year_invest);
            interest = (TextView) itemView.findViewById(R.id.year_interest);
            balance = (TextView) itemView.findViewById(R.id.year_balance);
            chart = (PieChartView) itemView.findViewById(R.id.year_chart);
        }
    }
}
