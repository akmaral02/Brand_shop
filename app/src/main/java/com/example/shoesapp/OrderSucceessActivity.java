package com.example.shoesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoesapp.model.BrandModel;

public class OrderSucceessActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_succeess);


        BrandModel brandModel = getIntent().getParcelableExtra("BrandModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(brandModel.getName());
        actionBar.setSubtitle(brandModel.getSlogan());
        actionBar.setDisplayHomeAsUpEnabled(false);

        img = findViewById(R.id.image_order);


        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}