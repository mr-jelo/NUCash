package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class BillsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        // Back button functionality
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Click listener for Tuition card
        findViewById(R.id.tuition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsActivity.this, TuitionActivity.class);
                startActivity(intent);
            }
        });

        // Click listener for Promissory Note card
        findViewById(R.id.promissory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsActivity.this, PromissoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
