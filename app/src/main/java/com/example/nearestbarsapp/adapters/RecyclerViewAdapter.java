package com.example.nearestbarsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearestbarsapp.models.Bar;
import com.example.nearestbarsapp.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Bar> barList;

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
        holder.tv_bar_distance.setText(barList.get(position).getBarDistance());
    }

    @Override
    public int getItemCount() {
        return barList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_bar, tv_bar_distance;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_bar = itemView.findViewById(R.id.txt_bar_name);
            tv_bar_distance = itemView.findViewById(R.id.txt_bar_distance);
        }
    }
}
