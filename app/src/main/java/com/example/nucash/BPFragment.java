package com.example.nucash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BPFragment extends Fragment {

    private static final double MIN_AMOUNT = 500.0; // Minimum amount to pay
    private EditText amountEditText;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b_p, container, false);

        amountEditText = view.findViewById(R.id.amountEditText);
        submitButton = view.findViewById(R.id.submit_btn);

        // Initially disable the submit button
        submitButton.setEnabled(false);

        // Add TextWatcher to EditText for input validation
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        submitButton.setOnClickListener(v -> handleSubmit());

        return view;
    }

    private void validateInput() {
        String input = amountEditText.getText().toString();
        // Enable the submit button if the input is valid
        submitButton.setEnabled(!input.isEmpty() && isValidAmount(input) && Double.parseDouble(input) > MIN_AMOUNT);
    }

    private boolean isValidAmount(String input) {
        try {
            double amount = Double.parseDouble(input);
            return amount >= 0; // Ensure the amount is non-negative
        } catch (NumberFormatException e) {
            return false; // Invalid input
        }
    }

    private void handleSubmit() {
        String input = amountEditText.getText().toString();
        double amount;
        try {
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Invalid amount entered", Toast.LENGTH_SHORT).show();
            return;
        }

        updateWalletBalance(amount);
    }

    private void updateWalletBalance(double amount) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Wallet", Context.MODE_PRIVATE);
        double walletBalance = sharedPreferences.getFloat("balance", 0);

        if (amount <= walletBalance) {
            walletBalance -= amount;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("balance", (float) walletBalance);
            editor.apply();
            Toast.makeText(getActivity(), "Transaction Complete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
        }
    }

}
