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

public class TabTwoFragment extends Fragment {

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
        listDataHeader.add("Recommended Banks");
        listDataHeader.add("Partner Banks");

        // Adding child data
        List<String> recommendedBanks = new ArrayList<>();
        recommendedBanks.add("BPI");
        recommendedBanks.add("UnionBank");

        List<String> partnerBanks = new ArrayList<>();
        partnerBanks.add("AUB");
        partnerBanks.add("Bank of China");
        partnerBanks.add("Bank of Commerce");
        partnerBanks.add("BDO");

        listDataChild.put(listDataHeader.get(0), recommendedBanks); // Header, Child data
        listDataChild.put(listDataHeader.get(1), partnerBanks);
    }
}
