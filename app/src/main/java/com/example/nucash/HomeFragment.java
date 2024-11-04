package com.example.nucash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private CardView walletCard, cashInCard, sendCard, billsCard, historyCard;
    private TextView walletBalanceTextView;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "NUCashPrefs";
    private static final String WALLET_BALANCE_KEY = "wallet_balance";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        walletCard = view.findViewById(R.id.wallet_card);
        cashInCard = view.findViewById(R.id.cash_in_card);
        sendCard = view.findViewById(R.id.send_card);
        billsCard = view.findViewById(R.id.bills_card);
        historyCard = view.findViewById(R.id.history_card);
        walletBalanceTextView = view.findViewById(R.id.nucashTextView);

        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Set initial wallet balance
        if (sharedPreferences.getString(WALLET_BALANCE_KEY, null) == null) {
            sharedPreferences.edit().putString(WALLET_BALANCE_KEY, "₱ 4000.50").apply(); // Set initial balance
        }

        // Retrieve and display the current balance
        refreshWalletBalance();

        walletCard.setOnClickListener(v -> showWalletBalance());
        cashInCard.setOnClickListener(v -> navigateToCashIn());
        sendCard.setOnClickListener(v -> navigateToSend());
        billsCard.setOnClickListener(v -> navigateToBills());
        historyCard.setOnClickListener(v -> navigateToHistory());
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh wallet balance when the fragment is resumed
        refreshWalletBalance();
    }

    private void refreshWalletBalance() {
        String walletBalance = sharedPreferences.getString(WALLET_BALANCE_KEY, "₱ 4000.50");
        walletBalanceTextView.setText(walletBalance);
    }

    private void showWalletBalance() {
        String walletBalance = sharedPreferences.getString(WALLET_BALANCE_KEY, "₱ 4000.50");
        Toast.makeText(getContext(), "Your current balance is: " + walletBalance, Toast.LENGTH_SHORT).show();
    }

    private void navigateToCashIn() {
        startActivity(new Intent(getActivity(), CashInActivity.class));
    }

    private void navigateToSend() {
        startActivity(new Intent(getActivity(), SendActivity.class));
    }

    private void navigateToBills() {
        startActivity(new Intent(getActivity(), BillsActivity.class));
    }

    private void navigateToHistory() {
        startActivity(new Intent(getActivity(), HistoryActivity.class));
    }
}
