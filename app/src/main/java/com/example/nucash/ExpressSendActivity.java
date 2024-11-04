package com.example.nucash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private TextView errorTextView;
    private TextView accountNotFoundTextView;
    private double currentBalance;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "NUCashPrefs";
    private static final String WALLET_BALANCE_KEY = "wallet_balance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_send);

        amountEditText = findViewById(R.id.amountEditText);
        messageEditText = findViewById(R.id.messageEditText);
        recipientEditText = findViewById(R.id.recipientEditText);
        charCountTextView = findViewById(R.id.charCountTextView);
        sendNextButton = findViewById(R.id.send_next);
        errorTextView = findViewById(R.id.errorTextView);
        accountNotFoundTextView = findViewById(R.id.accountNotFoundTextView);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String balanceString = sharedPreferences.getString(WALLET_BALANCE_KEY, "₱ 0.00");
        currentBalance = parseBalance(balanceString);
        TextView currentBalanceTextView = findViewById(R.id.currentBalanceTextView);

        currentBalanceTextView.setText("₱" + String.format("%.2f", currentBalance));

        sendNextButton.setEnabled(false);

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                charCountTextView.setText(s.length() + "/100");
                validateInputs();
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 100) {
                    s.delete(100, s.length());
                }
            }
        });

        sendNextButton.setOnClickListener(v -> {
            if (validateInputs()) {
                String recipientInput = recipientEditText.getText().toString().trim();
                String studentId = null;
                String studentName = null;

                for (Student student : StudentData.getStudentList()) {
                    if (student.name.equalsIgnoreCase(recipientInput) || student.id.equals(recipientInput)) {
                        studentId = student.id;
                        studentName = student.name;
                        break;
                    }
                }

                if (studentId != null) {
                    Intent intent = new Intent(ExpressSendActivity.this, SendDetailsActivity.class);
                    intent.putExtra("amount", amountEditText.getText().toString());
                    intent.putExtra("message", messageEditText.getText().toString());
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("recipient", studentName);
                    startActivity(intent);
                } else {
                    accountNotFoundTextView.setVisibility(View.VISIBLE);
                    accountNotFoundTextView.setText("Can't find NUCash Account");
                }
            } else {
                Toast.makeText(ExpressSendActivity.this, "Required Input", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.backButton).setOnClickListener(v -> {
            startActivity(new Intent(ExpressSendActivity.this, SendActivity.class));
            finish();
        });

        recipientEditText.addTextChangedListener(new InputTextWatcher());
        amountEditText.addTextChangedListener(new InputTextWatcher());
    }

    private double parseBalance(String balanceString) {
        String numericString = balanceString.replace("₱", "").replace(",", "").trim();
        try {
            return Double.parseDouble(numericString);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private boolean validateInputs() {
        boolean isRecipientFilled = !recipientEditText.getText().toString().trim().isEmpty();
        boolean isAmountFilled = !amountEditText.getText().toString().trim().isEmpty();

        errorTextView.setVisibility(View.GONE);
        accountNotFoundTextView.setVisibility(View.GONE);
        sendNextButton.setEnabled(false);

        if (isAmountFilled) {
            try {
                double amount = Double.parseDouble(amountEditText.getText().toString());
                if (amount <= currentBalance) {
                    sendNextButton.setEnabled(true);
                } else {
                    errorTextView.setVisibility(View.VISIBLE);
                    errorTextView.setText("Insufficient Balance");
                }
            } catch (NumberFormatException e) {
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
