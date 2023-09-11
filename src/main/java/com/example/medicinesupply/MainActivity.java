package com.example.medicinesupply;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    List<MenuItemsModel> menuItemsModelList;
    RecyclerView recyclerView;
    RecyclerMenuAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //logout = findViewById(R.id.logout);

        menuItemsModelList = new ArrayList<>();
        menuItemsModelList.add(new MenuItemsModel("Red Velvet Cake",getString(R.string.redvelvet),R.drawable.redvelvet));
        menuItemsModelList.add(new MenuItemsModel("Chocolate Fudge Cake",getString(R.string.chocolatefudge),R.drawable.chocolatefudge));
        menuItemsModelList.add(new MenuItemsModel("Lemon Drizzle Cake",getString(R.string.lemondrizzle),R.drawable.lemondrizzle));
        menuItemsModelList.add(new MenuItemsModel("Carrot Cake",getString(R.string.carrot),R.drawable.carrot));
        menuItemsModelList.add(new MenuItemsModel("Black Forest Cake",getString(R.string.blackforest),R.drawable.blackforest));
        menuItemsModelList.add(new MenuItemsModel("Strawberry ShortCake",getString(R.string.strawberryshortcake),R.drawable.strawberryshortcake));
        menuItemsModelList.add(new MenuItemsModel("Coffee Mocha Cake",getString(R.string.coffeemocha),R.drawable.coffeemocha));
        menuItemsModelList.add(new MenuItemsModel("Matcha Green Cake",getString(R.string.matchagreentea),R.drawable.matchagreentea));
        menuItemsModelList.add(new MenuItemsModel("Coconut Cream Cake",getString(R.string.coconutcream),R.drawable.coconutcream));
        menuItemsModelList.add(new MenuItemsModel("Funfetti Cake",getString(R.string.funfetti),R.drawable.funfetti));


        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        Adapter = new RecyclerMenuAdapter(this, menuItemsModelList);
        recyclerView.setAdapter(Adapter);

        /*RecyclerViewDataPass recyclerViewDataPass = new RecyclerViewDataPass() {
            @Override
            public void pass(String cakename)
            {
                Toast.makeText(MainActivity.this, ""+cakename, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, IndividualItemActivity.class);
                intent.putExtra("cakename",cakename);
            }
        };

        Adapter = new RecyclerMenuAdapter(MainActivity.this,menuItemsModelList,recyclerViewDataPass);
        recyclerView.setAdapter(Adapter);*/

    }
}