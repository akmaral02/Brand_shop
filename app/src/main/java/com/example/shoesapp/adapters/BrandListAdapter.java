package com.example.shoesapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoesapp.R;
import com.example.shoesapp.model.BrandModel;

import java.util.List;

public class BrandListAdapter extends RecyclerView.Adapter<BrandListAdapter.MyViewHolder> {

    private List<BrandModel> brandModelList;
    private BrandListClickListener clickListener;

    public BrandListAdapter(List<BrandModel> brandModelList, BrandListClickListener clickListener) {
        this.brandModelList = brandModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<BrandModel> brandModelList) {
        this.brandModelList = brandModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BrandListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.brandName.setText(brandModelList.get(position).getName());
        holder.slogan.setText( brandModelList.get(position).getSlogan());
        holder.hours.setText( brandModelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(brandModelList.get(position));
            }
        });
        Glide.with(holder.brandImage)
                .load(brandModelList.get(position).getImage())
                .into(holder.brandImage);

    }

    @Override
    public int getItemCount() {
        return brandModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView brandName;
        TextView  slogan;
        TextView  hours;
        ImageView brandImage;

        public MyViewHolder(View view) {
            super(view);
            brandName = view.findViewById(R.id.brandName);
            slogan = view.findViewById(R.id.slogan);
            hours = view.findViewById(R.id.hours);
            brandImage = view.findViewById(R.id.brandImage);

        }
    }

    public interface BrandListClickListener {
        public void onItemClick(BrandModel brandModel);
    }
}