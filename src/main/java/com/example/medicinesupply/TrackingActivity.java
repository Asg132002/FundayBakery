package com.example.medicinesupply;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class TrackingActivity extends AppCompatActivity {
    RatingBar rt;
    TextView price;
    Button rtbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        price = findViewById(R.id.price);
        rt = (RatingBar) findViewById(R.id.ratingbar);
        rtbtn = findViewById(R.id.ratingbtn);

        Intent intent = getIntent();
        String str = intent.getStringExtra("price");
        price.setText("PRICE : Rs "+ str);

        rtbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(TrackingActivity.this, "Thank you for your feedback.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}