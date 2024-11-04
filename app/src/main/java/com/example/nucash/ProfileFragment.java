package com.example.nucash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        CardView logoutCard = view.findViewById(R.id.logoutCard);
        logoutCard.setOnClickListener(v -> {

            Toast.makeText(getActivity(), "Successfully logged out", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);

            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        return view;
    }
}
