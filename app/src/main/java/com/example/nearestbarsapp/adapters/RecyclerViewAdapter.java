package com.example.nearestbarsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.models.BarItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<BarItem> barList = new ArrayList<>();
    private ItemClickListener clickListener;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setBars(List<BarItem> barList) {
        this.barList = barList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bar_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final BarItem bar = barList.get(position);
        holder.barName.setText(barList.get(position).getName());
        holder.barDistance.setText(Math.round(barList.get(position).getDistance()) + holder.meters);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(bar);
            }
        });

    }

    @Override
    public int getItemCount() {
        return barList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView barName, barDistance;
        CardView cardView;
        String meters;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barName = itemView.findViewById(R.id.txt_bar_name);
            barDistance = itemView.findViewById(R.id.txt_bar_distance);
            cardView = itemView.findViewById(R.id.cv_item_bar);
            meters = itemView.getContext().getResources().getString(R.string.meters);


        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(BarItem bar);
    }
}