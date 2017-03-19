package com.example.haily.learnuikongjian.phone;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.haily.learnuikongjian.MainActivity;
import com.example.haily.learnuikongjian.R;

/**
 * Created by haily on 2016/11/29.
 */

public class ListFragment extends Fragment {
    private MyAdapter myAdapter;
    ListView listView;
    LinearLayout linearLayout;

    private PopupWindow popWindow;

    private void initPopWindow(View v) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_popip, null, false);
        Button btn_xixi = (Button) view.findViewById(R.id.btn_xixi);
        Button btn_hehe = (Button) view.findViewById(R.id.btn_hehe);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

//        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 50, 0);

        //设置popupWindow里的按钮的事件
        btn_xixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "你点击了嘻嘻~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "你点击了呵呵~", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_list_number,container,false);

//        View headView = inflater.inflate(R.layout.view_header, null, false);
//        View  header= inflater.inflate(R.layout.view_header,null,false);
         listView = (ListView) linearLayout.findViewById(R.id.lv);

        GetNumber.GetNumber(getContext());//得到电话本数据并封装在List集合中
        myAdapter = new MyAdapter(GetNumber.list, getContext());

//        listView.addHeaderView(headView);
        listView.setAdapter(myAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                myAdapter.removeByData(view);
                initPopWindow(view);

                System.out.println("我被长按啦");
//                GetNumber.list.remove(position);


                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("我被单击啦");
//                myAdapter.removeById(position);
                myAdapter.removeByData(GetNumber.list.get(position));
//                myAdapter.removeById(GetNumber.list.get(position));

//                Toast.makeText(getContext(),"helloposition="+position+"id="+id, Toast.LENGTH_SHORT).show();
//                Fragment fragment = new DetailFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("position",position);
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.second,fragment).commit();
            }
        });

        return linearLayout;
    }

}
