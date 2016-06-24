package com.kavita.ppf.yearly_limit;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kavita.ppf.R;

import java.util.List;

/**
 * Created by Sharad on 17-Jun-16.
 */

public class YearlyLimitRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<YearlyLimitItem> itemList;

    public YearlyLimitRecyclerAdapter(List<YearlyLimitItem> itemList) {
        this.itemList = itemList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.yearly_limit_card, parent, false);
        YearlyLimitViewHolder vh = new YearlyLimitViewHolder(view);
        return vh;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        YearlyLimitViewHolder holder = (YearlyLimitViewHolder) viewHolder;
        holder.title.setText(itemList.get(position).getYearString());
        holder.limit.setText(itemList.get(position).getLimitString());
    }

    public int getItemCount() { return itemList.size(); }

    public class YearlyLimitViewHolder extends RecyclerView.ViewHolder
            implements PopupMenu.OnMenuItemClickListener {
        public TextView title;
        public TextView limit;
        private PopupMenu menu;

        public YearlyLimitViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.year_title);
            limit = (TextView) itemView.findViewById(R.id.year_limit);

            ImageButton menuButton = (ImageButton) itemView.findViewById(R.id.contextMenu);
            menu = new PopupMenu(itemView.getContext(), menuButton, Gravity.TOP);
            menu.inflate(R.menu.menu_item_card);
            menu.setOnMenuItemClickListener(this);

            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menu.show();
                }
            });
        }

        public boolean onMenuItemClick(MenuItem item) {
            return true;
        }
    }
}
