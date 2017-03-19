package com.example.haily.learnuikongjian.phone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.haily.learnuikongjian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haily on 2016/11/29.
 */
public class DetailFragment extends Fragment{
    List<PhoneInfo> list = new ArrayList<PhoneInfo>();
    TextView name, number;
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        GetNumber.GetNumber(getContext());//得到电话本数据并封装在List集合中
        list= GetNumber.list;
        int position = 0;
        if (getArguments() != null) {
             position = getArguments().getInt("position");
            System.out.println(position);

        }
            linearLayout = (LinearLayout) inflater.inflate(R.layout.number_detail, container, false);
        name = (TextView) linearLayout.findViewById(R.id.name);
        number = (TextView) linearLayout.findViewById(R.id.number);
        name.setText("我是姓名是:"+list.get(position).getName());
        number.setText("我是号码是:"+list.get(position).getNumber());

        return linearLayout;
    }
}
