package com.example.nucash;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        ImageButton qrButton = findViewById(R.id.qrButton);


        boolean redirectToHome = getIntent().getBooleanExtra("redirectToHome", false);

        if (redirectToHome) {
            loadFragment(new HomeFragment());
        } else {
            loadFragment(new HomeFragment());
        }

        // Handle bottom navigation item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.navigation_inbox) {
                    selectedFragment = new InboxFragment();
                } else if (item.getItemId() == R.id.navigation_qr_dummy) {
                    // Prevent selection of this item in the BottomNavigationView
                    return false;
                } else if (item.getItemId() == R.id.navigation_help) {
                    selectedFragment = new HelpFragment();
                } else if (item.getItemId() == R.id.navigation_profile) {
                    selectedFragment = new ProfileFragment();
                } else {
                    return false;
                }

                return loadFragment(selectedFragment);
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    // Method to load the selected fragment
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment) // Replace 'fragment_container' with your actual container ID
                    .commit();
            return true;
        }
        return false;
    }
}
