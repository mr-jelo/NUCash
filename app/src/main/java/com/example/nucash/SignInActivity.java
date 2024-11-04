package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private EditText emailEditText;
    private CardView nextCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        emailEditText = findViewById(R.id.emailEditText);
        nextCard = findViewById(R.id.next_card);

        // Disable the Next button initially
        nextCard.setEnabled(false);

        // Add a TextWatcher to listen for text changes
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Enable or disable Next card based on input
                nextCard.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        nextCard.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignInPassActivity.class);
            startActivity(intent);
        });
    }
}
