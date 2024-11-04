package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.EditText;

public class SignInPassActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private CardView signInCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in_pass);

        passwordEditText = findViewById(R.id.passEditText);
        signInCard = findViewById(R.id.signin_card);

        // Disable the Sign In button initially
        signInCard.setEnabled(false);

        // Add a TextWatcher to listen for text changes
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Enable or disable Sign In card based on input
                signInCard.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        signInCard.setOnClickListener(view -> {
            Intent intent = new Intent(SignInPassActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
