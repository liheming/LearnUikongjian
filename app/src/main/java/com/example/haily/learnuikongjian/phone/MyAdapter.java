package com.example.haily.learnuikongjian.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.haily.learnuikongjian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haily on 2016/11/22.
 */

public class MyAdapter extends BaseAdapter {
    LinearLayout linearLayout;
    List<PhoneInfo> list = new ArrayList<PhoneInfo>();
    Context context;


   public MyAdapter(List<PhoneInfo> list , Context  context) {
        this.context = context;
        this.list = list;


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) inflater.inflate(R.layout.cell, null);
        TextView name = (TextView) linearLayout.findViewById(R.id.text_name);
        TextView number = (TextView) linearLayout.findViewById(R.id.text_number);
        name.setText(list.get(position).getName());
        number.setText(list.get(position).getNumber());
        return linearLayout;
    }
}
