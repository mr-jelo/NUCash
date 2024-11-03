package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ExpressSendActivity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText messageEditText;
    private EditText recipientEditText;
    private TextView charCountTextView;
    private Button sendNextButton;
    private TextView errorTextView; // Add this for error message
    private TextView accountNotFoundTextView; // New TextView for account not found message
    private double currentBalance = 1300.45; // Set your current balance here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_send);

        amountEditText = findViewById(R.id.amountEditText);
        messageEditText = findViewById(R.id.messageEditText);
        recipientEditText = findViewById(R.id.recipientEditText);
        charCountTextView = findViewById(R.id.charCountTextView);
        sendNextButton = findViewById(R.id.send_next);
        errorTextView = findViewById(R.id.errorTextView); // Initialize error TextView
        accountNotFoundTextView = findViewById(R.id.accountNotFoundTextView); // Initialize account not found TextView

        // Initially disable the Next button
        sendNextButton.setEnabled(false);

        // Set up character limit for message EditText
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charCount = s.length();
                charCountTextView.setText(charCount + "/100");
                validateInputs();
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 100) {
                    s.delete(100, s.length());
                }
            }
        });

        // Handle send_next button click
        sendNextButton.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(ExpressSendActivity.this, SendDetailsActivity.class);
                intent.putExtra("amount", amountEditText.getText().toString());
                intent.putExtra("message", messageEditText.getText().toString());

                // Find the student ID based on the recipient name or ID input
                String recipientInput = recipientEditText.getText().toString().trim();
                String studentId = null;
                String studentName = null;

                for (Student student : StudentData.getStudentList()) {
                    if (student.name.equalsIgnoreCase(recipientInput)) {
                        studentId = student.id; // Get ID if name matches
                        studentName = student.name; // Get name
                        break; // Exit the loop once found
                    } else if (student.id.equals(recipientInput)) {
                        studentId = student.id; // Get ID
                        studentName = student.name; // Get name for display
                        break; // Exit the loop once found
                    }
                }

                if (studentId != null) {
                    intent.putExtra("studentId", studentId); // Send the student's ID
                    intent.putExtra("recipient", studentName); // Send the student's name
                    startActivity(intent);
                } else {
                    accountNotFoundTextView.setVisibility(View.VISIBLE);
                    accountNotFoundTextView.setText("Can't find NUCash Account");
                }
            } else {
                Toast.makeText(ExpressSendActivity.this, "Required Input", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle back button click
        findViewById(R.id.backButton).setOnClickListener(v -> {
            startActivity(new Intent(ExpressSendActivity.this, SendActivity.class));
            finish();
        });

        // Add TextWatcher for recipient and amount fields
        recipientEditText.addTextChangedListener(new InputTextWatcher());
        amountEditText.addTextChangedListener(new InputTextWatcher());
    }

    private boolean validateInputs() {
        boolean isRecipientFilled = !recipientEditText.getText().toString().trim().isEmpty();
        boolean isAmountFilled = !amountEditText.getText().toString().trim().isEmpty();

        // Reset the error message initially
        errorTextView.setVisibility(View.GONE);
        accountNotFoundTextView.setVisibility(View.GONE); // Hide account not found message
        sendNextButton.setEnabled(false); // Disable button by default

        if (isAmountFilled) {
            try {
                double amount = Double.parseDouble(amountEditText.getText().toString());
                if (amount <= currentBalance) {
                    sendNextButton.setEnabled(true); // Enable button if recipient is valid
                } else {
                    errorTextView.setVisibility(View.VISIBLE);
                    errorTextView.setText("Insufficient Balance");
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText("Invalid amount");
            }
        }

        return isRecipientFilled && isAmountFilled && (amountFilled() && (Double.parseDouble(amountEditText.getText().toString()) <= currentBalance));
    }

    private boolean amountFilled() {
        return !amountEditText.getText().toString().trim().isEmpty();
    }

    private class InputTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateInputs();
        }
        @Override
        public void afterTextChanged(Editable s) {}
    }
}
