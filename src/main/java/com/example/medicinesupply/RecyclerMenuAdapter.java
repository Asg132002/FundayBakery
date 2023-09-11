package com.example.medicinesupply;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerMenuAdapter extends RecyclerView.Adapter<RecyclerMenuAdapter.ViewHolder>
{
    Context context;
    List<MenuItemsModel> menuItemsModelList;
    //RecyclerViewDataPass recyclerViewDataPass;

    public RecyclerMenuAdapter(Context context, List<MenuItemsModel> menuItemsModelList)
    {
        this.context = context;
        this.menuItemsModelList = menuItemsModelList;
        //this.recyclerViewDataPass=recyclerViewDataPass;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        // LayoutInflater -> converts layout to view
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position)
    {

        // here we will find the position and start setting the output on our views

        String nameofcake = menuItemsModelList.get(position).getCakeName();
        String descofcake = menuItemsModelList.get(position).getCakeDetails();
        int images = menuItemsModelList.get(position).getCakeImg();

        holder.cakeNameAd.setText(nameofcake);
        holder.cakeDescAd.setText(descofcake);
        holder.cakeImgAd.setImageResource(images);

        //recyclerViewDataPass.pass(nameofcake);
        //Intent intent = new Intent("cakename");
        //intent.putExtra("cakename",nameofcake);
        //LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount()
    {
        return menuItemsModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        // here we will find the views on which we will inflate our data

        TextView cakeNameAd, cakeDescAd;
        ImageView cakeImgAd;

        public ViewHolder(View itemView) {
            super(itemView);

            cakeNameAd = itemView.findViewById(R.id.cakeNameLI);
            cakeDescAd = itemView.findViewById(R.id.cakeDetailsLI);
            cakeImgAd = itemView.findViewById(R.id.cakeImgLI);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, IndividualItemActivity.class);
            context.startActivity(intent);
        }
    }
}