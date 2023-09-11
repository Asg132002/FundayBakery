package com.example.medicinesupply;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class IndividualItemActivity extends AppCompatActivity
{
    ImageView imageView;
    ImageButton plus, minus;
    Button addbtn;
    EditText message;
    TextView placeorder;
    TextView count;
    TextView cakeName;
    TextView totalPrice;
    CheckBox whippedcream, ganache, caramel, chocochips, whitechips, sprinklers, macaron, flowers;
    int quantity=0;
    int totalPr = 0;
    int originalPrice;
    int ifCheckBox;
    int[] arr = new int[8];
    String cakenm;
    HashMap<String,Integer> CakePriceMap = new HashMap<>();
    //HashMap<String,Integer> CakeImageMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_item);

        imageView = findViewById(R.id.cakeimage_infopg);
        plus = findViewById(R.id.plus);
        minus  = findViewById(R.id.minus);
        count = findViewById(R.id.count);
        addbtn = findViewById(R.id.addbtn);
        message = findViewById(R.id.msg);
        placeorder = findViewById(R.id.proccedtopay);
        cakeName = findViewById(R.id.cakename_infopg);
        totalPrice = findViewById(R.id.priceinfo);
        whippedcream = findViewById(R.id.checkbox_whippedcream);
        ganache = findViewById(R.id.checkbox_ganache);
        caramel = findViewById(R.id.checkbox_caramel);
        chocochips = findViewById(R.id.checkbox_chocochips);
        whitechips = findViewById(R.id.checkbox_whitechips);
        sprinklers = findViewById(R.id.checkbox_sprinklers);
        macaron = findViewById(R.id.checkbox_macarons);
        flowers = findViewById(R.id.checkbox_flowers);

        /*CakeImageMap.put("Red Velvet Cake",R.drawable.redvelvet);
        CakeImageMap.put("Chococlate Fudge Cake",R.drawable.chocolatefudge);
        CakeImageMap.put("Lemon Drizzle Cake",R.drawable.lemondrizzle);
        CakeImageMap.put("Carrot Cake",R.drawable.carrot);
        CakeImageMap.put("Black Forest Cake",R.drawable.blackforest);
        CakeImageMap.put("Strawberry ShortCake",R.drawable.strawberryshortcake);
        CakeImageMap.put("Coffee Mocha Cake",R.drawable.coffeemocha);
        CakeImageMap.put("Matcha Green Cake",R.drawable.matchagreentea);
        CakeImageMap.put("Coconut Cream Cake",R.drawable.coconutcream);
        CakeImageMap.put("Funfetti Cake",R.drawable.funfetti);

        if(CakeImageMap.get(cakenm)==null)
            Toast.makeText(this, "Null Value generated", Toast.LENGTH_SHORT).show();
        else
            imageView.setImageResource(CakeImageMap.get(cakenm));*/

        CakePriceMap.put("Red Velvet Cake",400);
        CakePriceMap.put("Chococlate Fudge Cake",350);
        CakePriceMap.put("Lemon Drizzle Cake",400);
        CakePriceMap.put("Carrot Cake",450);
        CakePriceMap.put("Black Forest Cake",450);
        CakePriceMap.put("Strawberry ShortCake",180);
        CakePriceMap.put("Coffee Mocha Cake",375);
        CakePriceMap.put("Matcha Green Cake",500);
        CakePriceMap.put("Coconut Cream Cake",470);
        CakePriceMap.put("Funfetti Cake",350);

        String str = getIntent().getStringExtra("cakename");
        cakeName.setText(str);
        imageView.setImageResource(R.drawable.cakedummy);

        plus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                quantity++;
                displayQuantity();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(quantity==0)
                {
                    Toast.makeText(IndividualItemActivity.this, "Can't Decrease Anymore", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    quantity--;
                    displayQuantity();
                }
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0)
                {
                    ifCheckBox = addToPrice(whippedcream, ganache, caramel, chocochips, whitechips, sprinklers, macaron, flowers);
                    totalPr = ifCheckBox * quantity;
                    displayTotalPrice();
                    Boolean insert;
                    try (DBHelper db = new DBHelper(IndividualItemActivity.this))
                    {
                        String cake = String.valueOf(cakeName);
                        if(message==null)
                            message.setText("");
                        String msg = message.getText().toString();
                        insert = db.insertDataOrder("CAKENAME", 300, quantity, arr, totalPr,message.getText().toString());

                        if(insert)
                            Toast.makeText(IndividualItemActivity.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(IndividualItemActivity.this, "Data storage failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(IndividualItemActivity.this, "Increase Quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quantity>0) {
                    Intent intent = new Intent(IndividualItemActivity.this, TrackingActivity.class);
                    intent.putExtra("price", String.valueOf(totalPr));
                    startActivity(intent);
                }
                else
                    Toast.makeText(IndividualItemActivity.this, "Make your selection", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private int addToPrice(CheckBox whippedcream, CheckBox ganache, CheckBox caramel, CheckBox chocochips, CheckBox whitechips, CheckBox sprinklers, CheckBox macaron, CheckBox flowers) {

        originalPrice = 300;
        if (whippedcream.isChecked()) {
            // add the cream cost $2
            arr[0] = 1;
            originalPrice = originalPrice + 10;
        }
        if (ganache.isChecked()) {
            // add the cream cost $2
            arr[1] = 1;
            originalPrice = originalPrice + 15;
        }
        if (caramel.isChecked()) {
            // add the cream cost $2
            arr[2] = 1;
            originalPrice = originalPrice + 18;
        }
        if (chocochips.isChecked()) {
            // add the cream cost $2
            arr[3] = 1;
            originalPrice = originalPrice + 5;
        }
        if (whitechips.isChecked()) {
            // add the cream cost $2
            arr[4] = 1;
            originalPrice = originalPrice + 6;
        }
        if (sprinklers.isChecked()) {
            // add the cream cost $2
            arr[5] = 1;
            originalPrice = originalPrice + 6;
        }
        if (macaron.isChecked()) {
            // add the cream cost $2
            arr[6] = 1;
            originalPrice = originalPrice + 20;
        }
        if (flowers.isChecked()) {
            // add the cream cost $2
            arr[7] = 1;
            originalPrice = originalPrice + 15;
        }

        return originalPrice;
    }

    private void displayQuantity()
    {
        count.setText(String.valueOf(quantity));
    }
    private void displayTotalPrice()
    {
        totalPrice.setText("TOTAL COST = Rs " + totalPr);
    }
}