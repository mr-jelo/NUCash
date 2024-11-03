package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CashInActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in);


        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Set up the ViewPager and TabLayout
        viewPager.setAdapter(new FragmentAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Over-The-Counter");
                            break;
                        case 1:
                            tab.setText("Local Banks");
                            break;
                        case 2:
                            tab.setText("Global Partners");
                            break;
                    }
                }).attach();

        // Handle back button click
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(CashInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private static class FragmentAdapter extends FragmentStateAdapter {

        FragmentAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new TabOneFragment();
                case 1:
                    return new TabTwoFragment();
                case 2:
                    return new TabThreeFragment();
                default:
                    return new TabOneFragment(); // Default fragment
            }
        }

        @Override
        public int getItemCount() {
            return 3; // Update to 3 for the number of tabs
        }
    }
}
