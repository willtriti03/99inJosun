package com.example.jungjune.sunrintons.Activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jungjune.sunrintons.R;

/**
 * Created by Jaehyun on 2016-07-21.
 */
public class ArrayFragment extends Fragment {
    int position;

    static ArrayFragment newInstance(int num) {

        ArrayFragment f = new ArrayFragment();
        Bundle args = new Bundle();
        args.putInt("position", num);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments() != null ? getArguments().getInt("position") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_lastest_4, container, false);

        TextView name1 = (TextView) v.findViewById(R.id.lastest_4_name1);
        TextView name2 = (TextView) v.findViewById(R.id.lastest_4_name2);
        TextView info1 = (TextView) v.findViewById(R.id.lastest_4_info1);
        TextView info2 = (TextView) v.findViewById(R.id.lastest_4_info2);
        switch (position) {
            case 0:

                break;

            case 1:
                break;

            case 2:
                break;
        }

        return v;
    }
}
