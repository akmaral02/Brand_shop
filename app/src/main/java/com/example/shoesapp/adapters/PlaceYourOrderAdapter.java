package com.example.shoesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoesapp.R;
import com.example.shoesapp.model.Shoe;

import java.util.List;

public class PlaceYourOrderAdapter extends RecyclerView.Adapter<PlaceYourOrderAdapter.MyViewHolder> {

    private List<Shoe> shoeList;

    public PlaceYourOrderAdapter(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    public void updateData(List<Shoe> shoeList) {
        this.shoeList = shoeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceYourOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceYourOrderAdapter.MyViewHolder holder, int position) {
        holder.shoesName.setText(shoeList.get(position).getName());
        holder.shoesPrice.setText("Цена: $"+String.format("%.2f", shoeList.get(position).getPrice()* shoeList.get(position).getTotalInCart()));
        holder.shoesQty.setText("Кол-во: " + shoeList.get(position).getTotalInCart());
        Glide.with(holder.shoesImage)
                .load(shoeList.get(position).getUrl())
                .into(holder.shoesImage);

    }

    @Override
    public int getItemCount() {
        return shoeList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shoesName;
        TextView  shoesPrice;
        TextView  shoesQty;
        ImageView shoesImage;

        public MyViewHolder(View view) {
            super(view);
            shoesName = view.findViewById(R.id.shoesName);
            shoesPrice = view.findViewById(R.id.shoesPrice);
            shoesQty = view.findViewById(R.id.shoesQty);
            shoesImage = view.findViewById(R.id.shoesImage);
        }
    }
}
