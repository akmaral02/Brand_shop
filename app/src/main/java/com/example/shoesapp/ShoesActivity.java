package com.example.shoesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoesapp.model.BrandModel;
import com.example.shoesapp.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ShoesActivity extends AppCompatActivity implements ShoesListAdapter.ShoesListClickListener {


    private List<Shoe> shoeList = null;
    private List<Shoe> itemsInCartList;
    private int totalItemInCart = 0;
    private TextView buttonCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);



        BrandModel brandModel = getIntent().getParcelableExtra("BrandModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(brandModel.getName());
        actionBar.setSubtitle(brandModel.getSlogan());
        actionBar.setDisplayHomeAsUpEnabled(true);


        shoeList = brandModel.getShoes();
        initRecyclerView();


        buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemsInCartList != null && itemsInCartList.size() <= 0) {
                    Toast.makeText(ShoesActivity.this, "Ваша корзина пустая!", Toast.LENGTH_SHORT).show();
                    return;
                }
                brandModel.setShoes(itemsInCartList);
                Intent i = new Intent(ShoesActivity.this, PlaceYourOrderActivity.class);
                i.putExtra("BrandModel", brandModel);
                startActivityForResult(i, 1000);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ShoesListAdapter shoesListAdapter = new ShoesListAdapter(shoeList, this);
        recyclerView.setAdapter(shoesListAdapter);
    }

    @Override
    public void onAddToCartClick(Shoe shoe) {
        if(itemsInCartList == null) {
            itemsInCartList = new ArrayList<>();
        }
        itemsInCartList.add(shoe);
        totalItemInCart = 0;

        for(Shoe m : itemsInCartList) {
            totalItemInCart = totalItemInCart + m.getTotalInCart();
        }
        buttonCheckout.setText("Ваша Корзина (" +totalItemInCart +") товаров");
    }

    @Override
    public void onUpdateCartClick(Shoe shoe) {
        if(itemsInCartList.contains(shoe)) {
            int index = itemsInCartList.indexOf(shoe);
            itemsInCartList.remove(index);
            itemsInCartList.add(index, shoe);

            totalItemInCart = 0;

            for(Shoe m : itemsInCartList) {
                totalItemInCart = totalItemInCart + m.getTotalInCart();
            }
            buttonCheckout.setText("Ваша Корзина (" +totalItemInCart +") товаров");
        }
    }

    @Override
    public void onRemoveFromCartClick(Shoe shoe) {
        if(itemsInCartList.contains(shoe)) {
            itemsInCartList.remove(shoe);
            totalItemInCart = 0;

            for(Shoe m : itemsInCartList) {
                totalItemInCart = totalItemInCart + m.getTotalInCart();
            }
            buttonCheckout.setText("Ваша Корзина (" +totalItemInCart +") товаров");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            //
            finish();
        }
    }
}