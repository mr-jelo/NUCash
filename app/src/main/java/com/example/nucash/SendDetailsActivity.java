package com.example.nucash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SendDetailsActivity extends AppCompatActivity {
    private TextView nucashTextView, recipientTextView, studentIdTextView, amountTextView, messageTextView;
    private Button sendButton;
    private CheckBox confirmCheckbox;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "NUCashPrefs";
    private static final String WALLET_BALANCE_KEY = "wallet_balance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_details);

        nucashTextView = findViewById(R.id.nucashTextView);
        recipientTextView = findViewById(R.id.recipientTextView);
        studentIdTextView = findViewById(R.id.studentIdTextView);
        amountTextView = findViewById(R.id.amountTextView);
        messageTextView = findViewById(R.id.messageTextView);
        sendButton = findViewById(R.id.send_next);
        confirmCheckbox = findViewById(R.id.confirmCheckbox);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Retrieve and display the wallet balance
        String walletBalance = sharedPreferences.getString(WALLET_BALANCE_KEY, "₱ 4000.50");
        nucashTextView.setText(walletBalance);

        // Get and display recipient data from intent
        Intent intent = getIntent();
        String recipient = intent.getStringExtra("recipient");
        String studentId = intent.getStringExtra("studentId");
        String amount = intent.getStringExtra("amount");
        String message = intent.getStringExtra("message");

        recipientTextView.setText(recipient);
        studentIdTextView.setText(studentId);
        amountTextView.setText("₱ " + amount);
        messageTextView.setText(message); // Display the message here

        sendButton.setEnabled(false);

        findViewById(R.id.backButton).setOnClickListener(v -> {
            startActivity(new Intent(SendDetailsActivity.this, ExpressSendActivity.class));
            finish();
        });

        // Enable send button based on checkbox status
        confirmCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> sendButton.setEnabled(isChecked));

        sendButton.setOnClickListener(v -> processTransaction(walletBalance, amount));
    }

    private void processTransaction(String walletBalance, String amount) {
        try {
            double currentBalance = Double.parseDouble(walletBalance.replace("₱", "").replace(",", "").trim());
            double amountToSend = Double.parseDouble(amount.replace("₱", "").replace(",", "").trim());

            if (currentBalance >= amountToSend) {
                // Deduct amount and update wallet balance
                double newBalance = currentBalance - amountToSend;
                sharedPreferences.edit().putString(WALLET_BALANCE_KEY, "₱ " + String.format("%.2f", newBalance)).apply();

                Toast.makeText(this, "Transaction complete", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SendDetailsActivity.this, MainActivity.class);
                intent.putExtra("fragment", "home"); // Specify which fragment to load
                startActivity(intent);
                finish(); // Finish current activity
            } else {
                Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
        }
    }

}
