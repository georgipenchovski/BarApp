package com.example.nearestbarsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearestbarsapp.models.Bar;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Bar> barList;

    public RecyclerViewAdapter(Context mContext, List<Bar> bars) {
        this.mContext = mContext;
        this.barList = bars;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.bar_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_bar.setText(barList.get(position).getBarName());
    }

    @Override
    public int getItemCount() {
        return barList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_bar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_bar = itemView.findViewById(R.id.txt_bar_name);
        }
    }
}
