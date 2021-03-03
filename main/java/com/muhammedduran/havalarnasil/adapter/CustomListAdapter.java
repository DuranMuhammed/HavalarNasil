package com.muhammedduran.havalarnasil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.muhammedduran.havalarnasil.R;

public class CustomListAdapter extends BaseAdapter {
    Context context;
    String propertiesList[], valuesList[];
    LayoutInflater inflater;
    public CustomListAdapter(Context applicationContext, String[] propertiesList, String[] valuesList) {
        this.context = applicationContext;
        this.propertiesList = propertiesList;
        this.valuesList = valuesList;

    }

    @Override
    public int getCount() {
        return propertiesList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(parent.getContext());
        ViewGroup.LayoutParams paramsList;
        convertView = inflater.inflate(R.layout.current_listview_layout, parent, false);
        paramsList = convertView.getLayoutParams();
        paramsList.height = (Integer)(Resources.getSystem().getDisplayMetrics().heightPixels/14);
        convertView.setLayoutParams(paramsList);
        TextView propertyView = (TextView) convertView.findViewById(R.id.currentListPropertyTextView);
        TextView valueView = (TextView) convertView.findViewById(R.id.currentListValueTextView);
        propertyView.setText(propertiesList[position]);
        valueView.setText(valuesList[position]);
        return convertView;
    }
}
