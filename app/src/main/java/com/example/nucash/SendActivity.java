package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SendActivity extends AppCompatActivity {

    private CardView expressSendCard, sendToManyCard, requestMoneyCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        // Initialize card views
        expressSendCard = findViewById(R.id.express_send);
        requestMoneyCard = findViewById(R.id.request_money);

        // Set onClick listeners
        expressSendCard.setOnClickListener(v -> {
            // Navigate to ExpressSendActivity
            startActivity(new Intent(SendActivity.this, ExpressSendActivity.class));
        });

        requestMoneyCard.setOnClickListener(v -> {
            // Navigate to RequestMoneyActivity
            startActivity(new Intent(SendActivity.this, RequestMoneyActivity.class));
        });

        // Back button click listener
        findViewById(R.id.backButton).setOnClickListener(v -> {
            // Navigate back to MainActivity
            startActivity(new Intent(SendActivity.this, MainActivity.class));
            finish();
        });
    }
}
