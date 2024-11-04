package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TuitionActivity extends AppCompatActivity {

    private LinearLayout detailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition);

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuitionActivity.this, BillsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        detailsContainer = findViewById(R.id.detailsContainer);

        setupOptionClickListeners();
    }

    private void setupOptionClickListeners() {
        findViewById(R.id.option1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelection(1);
            }
        });

        findViewById(R.id.option2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelection(2);
            }
        });

        findViewById(R.id.option3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelection(3);
            }
        });
    }

    private void updateSelection(int option) {
        // Reset all options to unselected
        resetOptions();

        // Set the selected option and show corresponding fragment
        switch (option) {
            case 1:
                findViewById(R.id.radioOption1).setBackgroundResource(R.drawable.circle_shape_selected);
                showFragment(new EFMPFragment());
                break;
            case 2:
                findViewById(R.id.radioOption2).setBackgroundResource(R.drawable.circle_shape_selected);
                showFragment(new EIPFragment());
                break;
            case 3:
                findViewById(R.id.radioOption3).setBackgroundResource(R.drawable.circle_shape_selected);
                showFragment(new BPFragment());
                break;
        }
    }

    private void resetOptions() {
        findViewById(R.id.radioOption1).setBackgroundResource(R.drawable.circle_shape);
        findViewById(R.id.radioOption2).setBackgroundResource(R.drawable.circle_shape);
        findViewById(R.id.radioOption3).setBackgroundResource(R.drawable.circle_shape);
        detailsContainer.setVisibility(View.GONE); // Hide details initially
    }

    private void showFragment(Fragment fragment) {
        detailsContainer.setVisibility(View.VISIBLE);

        // Using FragmentManager to display the fragment in the details container
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.detailsContainer, fragment);
        fragmentTransaction.addToBackStack(null); // Allow user to navigate back
        fragmentTransaction.commit();
    }
}
