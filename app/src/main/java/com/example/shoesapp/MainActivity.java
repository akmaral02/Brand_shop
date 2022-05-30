package com.example.shoesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoesapp.adapters.BrandListAdapter;
import com.example.shoesapp.model.BrandModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BrandListAdapter.BrandListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Каталог Брендов");

        List<BrandModel> brandModelList =  getBrandData();

        initRecyclerView(brandModelList);
    }

    private void initRecyclerView(List<BrandModel> brandModelList) {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BrandListAdapter adapter = new BrandListAdapter(brandModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<BrandModel> getBrandData() {
        InputStream is = getResources().openRawResource(R.raw.brands);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        BrandModel[] brandModels =  gson.fromJson(jsonStr, BrandModel[].class);
        List<BrandModel> restList = Arrays.asList(brandModels);

        return  restList;

    }

    @Override
    public void onItemClick(BrandModel brandModel) {
        Intent intent = new Intent(MainActivity.this, ShoesActivity.class);
        intent.putExtra("BrandModel", brandModel);
        startActivity(intent);

    }
}