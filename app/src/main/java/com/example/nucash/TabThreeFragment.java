package com.example.nucash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabThreeFragment extends Fragment {

    private ExpandableListView expandableListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_two, container, false);
        expandableListView = view.findViewById(R.id.expandableListView);

        prepareListData();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);

        // Expand all groups by default
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }

        return view;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding header data
        listDataHeader.add("Partners");
        listDataHeader.add("International Banks");
        listDataHeader.add("Online Remittance Claim");

        // Adding child data
        List<String> partners = new ArrayList<>();
        partners.add("PayPal");
        partners.add("Payoneer");

        List<String> internationalBanks = new ArrayList<>();
        internationalBanks.add("EU");
        internationalBanks.add("UK");
        internationalBanks.add("USA");

        List<String> onlineRemittanceClaim = new ArrayList<>();
        onlineRemittanceClaim.add("Ayannah");
        onlineRemittanceClaim.add("op2");
        onlineRemittanceClaim.add("op3");

        listDataChild.put(listDataHeader.get(0), partners);
        listDataChild.put(listDataHeader.get(1), internationalBanks);
        listDataChild.put(listDataHeader.get(2), onlineRemittanceClaim);
    }
}
