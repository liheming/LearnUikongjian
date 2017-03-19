package com.example.haily.learnuikongjian.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haily.learnuikongjian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haily on 2016/11/22.
 */

public class MyAdapter extends BaseAdapter {

    List<PhoneInfo> list = new ArrayList<PhoneInfo>();
    Context context;


    public MyAdapter(List<PhoneInfo> list, Context context) {
        this.context = context;
        this.list = list;

    }

//
//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }

//    @Override
//    public int getItemViewType(int position) {
//        if (list.get(position) instanceof Book)
//        return super.getItemViewType(position);
//    }

    public void removeById(int position ) {
        if (list != null) {
            list.remove(position);

        }
        notifyDataSetChanged();



    }
    public void removeByData(PhoneInfo view ) {
        if (list != null) {
            list.remove(view);

        }
        notifyDataSetChanged();

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
        ViewHolder viewholder = null;
        if (convertView == null) {
            viewholder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cell, null);


            ViewHolder.txt_aName = (TextView) convertView.findViewById(R.id.text_name);
            ViewHolder.test = (TextView) convertView.findViewById(R.id.test);
            ViewHolder.txt_number = (TextView) convertView.findViewById(R.id.text_number);
            convertView.setTag(viewholder);
        }
        else{
            convertView.getTag();
        }


        ViewHolder.test.setText(list.get(position).getName());
        ViewHolder.txt_aName.setText(list.get(position).getName());
        ViewHolder.txt_number.setText(list.get(position).getNumber());


        return convertView;
    }

    static class ViewHolder {


        static TextView txt_aName;
        static TextView test;
        static TextView txt_number;

    }
}
