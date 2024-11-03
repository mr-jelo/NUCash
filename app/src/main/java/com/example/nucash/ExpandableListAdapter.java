package com.example.nucash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader; // Header titles
    private HashMap<String, List<String>> listDataChild; // Header, Child data

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                   HashMap<String, List<String>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_header, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.groupHeaderTextView);
        lblListHeader.setText(headerTitle);

        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_child, null);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView txtListChild = convertView.findViewById(R.id.childTextView);

        // Set the image for the child item based on the text
        // You can use a placeholder for now or set your drawable based on conditions
        switch (childText) {
            case "BPI":
                imageView.setImageResource(R.drawable.ic_bills); // Replace with actual drawable
                break;
            case "UnionBank":
                imageView.setImageResource(R.drawable.ic_bills); // Replace with actual drawable
                break;
            case "AUB":
                imageView.setImageResource(R.drawable.ic_bills); // Replace with actual drawable
                break;
            case "Bank of China":
                imageView.setImageResource(R.drawable.ic_bills); // Replace with actual drawable
                break;
            case "Bank of Commerce":
                imageView.setImageResource(R.drawable.ic_bills); // Replace with actual drawable
                break;
            case "BDO":
                imageView.setImageResource(R.drawable.ic_bills); // Replace with actual drawable
                break;
            default:
                imageView.setImageResource(R.drawable.ic_bills); // Default drawable
                break;
        }

        txtListChild.setText(childText);
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
