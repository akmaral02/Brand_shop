package com.example.shoesapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoesapp.model.Shoe;
import com.bumptech.glide.Glide;
import java.util.List;

public class ShoesListAdapter extends RecyclerView.Adapter<ShoesListAdapter.MyViewHolder> {

    private List<Shoe> shoeList;
    private ShoesListClickListener clickListener;

    public ShoesListAdapter(List<Shoe> shoeList, ShoesListClickListener clickListener) {
        this.shoeList = shoeList;
        this.clickListener = clickListener;
    }

    public void updateData(List<Shoe> shoeList) {
        this.shoeList = shoeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoesListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoes_item, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.shoesName.setText(shoeList.get(position).getName());
        holder.shoesPrice.setText("Цена: $"+ shoeList.get(position).getPrice());
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoe shoe = shoeList.get(position);
                shoe.setTotalInCart(1);
                clickListener.onAddToCartClick(shoe);
                holder.addMoreLayout.setVisibility(View.VISIBLE);
                holder.addToCartButton.setVisibility(View.GONE);
                holder.tvCount.setText(shoe.getTotalInCart()+"");
            }
        });
        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoe shoe = shoeList.get(position);
                int total = shoe.getTotalInCart();
                total--;
                if(total > 0 ) {
                    shoe.setTotalInCart(total);
                    clickListener.onUpdateCartClick(shoe);
                    holder.tvCount.setText(total +"");
                } else {
                    holder.addMoreLayout.setVisibility(View.GONE);
                    holder.addToCartButton.setVisibility(View.VISIBLE);
                    shoe.setTotalInCart(total);
                    clickListener.onRemoveFromCartClick(shoe);
                }
            }
        });

        holder.imageAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoe shoe = shoeList.get(position);
                int total = shoe.getTotalInCart();
                total++;
                if(total <= 10 ) {
                    shoe.setTotalInCart(total);
                    clickListener.onUpdateCartClick(shoe);
                    holder.tvCount.setText(total +"");
                }
            }
        });

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
        TextView  addToCartButton;
        ImageView shoesImage;
        ImageView imageMinus;
        ImageView imageAddOne;
        TextView  tvCount;
        LinearLayout addMoreLayout;

        public MyViewHolder(View view) {
            super(view);
            shoesName = view.findViewById(R.id.shoesName);
            shoesPrice = view.findViewById(R.id.shoesPrice);
            addToCartButton = view.findViewById(R.id.addToCartButton);
            shoesImage = view.findViewById(R.id.shoesImage);
            imageMinus = view.findViewById(R.id.imageMinus);
            imageAddOne = view.findViewById(R.id.imageAddOne);
            tvCount = view.findViewById(R.id.tvCount);

            addMoreLayout  = view.findViewById(R.id.addMoreLayout);
        }
    }

    public interface ShoesListClickListener {
        public void onAddToCartClick(Shoe shoe);
        public void onUpdateCartClick(Shoe shoe);
        public void onRemoveFromCartClick(Shoe shoe);
    }
}
