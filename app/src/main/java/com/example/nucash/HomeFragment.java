package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private CardView walletCard, cashInCard, sendCard, billsCard, historyCard;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize card views
        walletCard = view.findViewById(R.id.wallet_card);
        cashInCard = view.findViewById(R.id.cash_in_card);
        sendCard = view.findViewById(R.id.send_card);
        billsCard = view.findViewById(R.id.bills_card);
        historyCard = view.findViewById(R.id.history_card);

        // Set click listeners
        walletCard.setOnClickListener(v -> showWalletBalance());
        cashInCard.setOnClickListener(v -> navigateToCashIn());
        sendCard.setOnClickListener(v -> navigateToSend());
        billsCard.setOnClickListener(v -> navigateToBills());
        historyCard.setOnClickListener(v -> navigateToHistory());
    }

    private void showWalletBalance() {
        String walletBalance = "₱ 1300.45";
        Toast.makeText(getContext(), "Your current balance is: " + walletBalance, Toast.LENGTH_SHORT).show();
    }

    private void navigateToCashIn() {
        startActivity(new Intent(getActivity(), CashInActivity.class));
    }

    private void navigateToSend() { // Add this method
        startActivity(new Intent(getActivity(), SendActivity.class));
    }

    private void navigateToBills() { // Add this method
        startActivity(new Intent(getActivity(), BillsActivity.class));
    }

    private void navigateToHistory() { // Add this method
        startActivity(new Intent(getActivity(), HistoryActivity.class));
    }
}
