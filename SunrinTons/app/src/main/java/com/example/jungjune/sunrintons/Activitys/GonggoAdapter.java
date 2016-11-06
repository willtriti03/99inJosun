package com.example.jungjune.sunrintons.Activitys;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jungjune.sunrintons.R;

import java.util.ArrayList;

/**
 * Created by Jaehyun on 2016-07-23.
 */
public class GonggoAdapter extends BaseAdapter {

    public static ArrayList<GongooSchema> items;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_gonggo, parent, false);

            TextView name = (TextView) convertView.findViewById(R.id.name_gonggo);
            name.setText(items.get(position).getName());
            Log.d("asdf", items.get(position).getName() + "");
        }

        return convertView;
    }
}
