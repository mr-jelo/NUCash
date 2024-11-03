package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RequestDetailsActivity extends AppCompatActivity {
    private TextView recipientTextView;
    private TextView studentIdTextView;
    private TextView amountTextView;
    private TextView messageTextView;

    private EditText amountEditText;
    private Button sendButton;
    private CheckBox confirmCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_details);

        recipientTextView = findViewById(R.id.recipientTextView);
        studentIdTextView = findViewById(R.id.studentIdTextView);
        amountTextView = findViewById(R.id.amountTextView);
        messageTextView = findViewById(R.id.messageTextView);

        sendButton = findViewById(R.id.send_next);
        confirmCheckbox = findViewById(R.id.confirmCheckbox);

        // Disable send button initially
        sendButton.setEnabled(false);

        // Set onClickListener for the back button
        findViewById(R.id.backButton).setOnClickListener(v -> {
            startActivity(new Intent(RequestDetailsActivity.this, RequestMoneyActivity.class));
            finish();
        });

        // Get the intent and display the data
        Intent intent = getIntent();
        String recipient = intent.getStringExtra("recipient");
        String studentId = intent.getStringExtra("studentId");
        String amount = intent.getStringExtra("amount");
        String message = intent.getStringExtra("message");

        recipientTextView.setText(recipient);
        studentIdTextView.setText(studentId);
        amountTextView.setText(amount);
        messageTextView.setText(message);

        // Enable send button based on checkbox status
        confirmCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sendButton.setEnabled(isChecked);
        });

        // Set button text to a static value
        sendButton.setText("Send");

        // Set OnClickListener for the send button
        sendButton.setOnClickListener(v -> {
            // Display toast message
            Toast.makeText(RequestDetailsActivity.this, "Transaction Complete", Toast.LENGTH_SHORT).show();

            // Redirect to HomeFragment
            Intent intentToHome = new Intent(RequestDetailsActivity.this, MainActivity.class);
            intentToHome.putExtra("redirectToHome", true); // Pass data to identify home redirection
            startActivity(intentToHome);
            finish();
        });
    }
}
