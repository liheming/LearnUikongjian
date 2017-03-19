package com.example.haily.learnuikongjian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.haily.learnuikongjian.phone.PhoneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haily on 2016/11/30.
 */

public class GridAdapt extends BaseAdapter {
    List<PhoneInfo> list = new ArrayList<>();
    Context context;


    GridAdapt(List<PhoneInfo> list,Context context) {
        this.list = list;
        this.context = context;

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
            convertView = inflater.inflate(R.layout.cell_gridview, null);


            viewholder.txt_aName = (TextView) convertView.findViewById(R.id.text_name);

            viewholder.txt_number = (TextView) convertView.findViewById(R.id.text_number);
            convertView.setTag(viewholder);
        }
        else{
            convertView.getTag();
        }



        viewholder.txt_aName.setText(list.get(position).getName());
        viewholder.txt_number.setText(list.get(position).getNumber());


        return convertView;
    }

    public void removeByData(PhoneInfo phoneInfo) {
        if (list != null) {
            list.remove(phoneInfo);

        }
        notifyDataSetChanged();


    }

    static class ViewHolder {


        static TextView txt_aName;

        static TextView txt_number;

    }
}
