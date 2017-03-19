package com.example.haily.learnuikongjian;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.haily.learnuikongjian.phone.GetNumber;
import com.example.haily.learnuikongjian.phone.PhoneInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ThreeActivity extends AppCompatActivity {
    ImageView imageView;
    int num=0;
    GridView gridView;
    GridAdapt gridAdapt;
    int pic[] = {R.drawable.one,R.drawable.two,R.drawable.three};
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:

                    System.out.println("handel massage成功i"+num);
                    if (num==3) {
                        num = 0;

                    }
                    imageView.setImageResource(pic[num]);
                        num++;




                    break;


            }



            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        imageView = (ImageView) findViewById(R.id.image);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(100);
            }
        },0,2000);


        gridView = (GridView) findViewById(R.id.grid);
        GetNumber.GetNumber(getApplicationContext());
        gridAdapt = new GridAdapt(GetNumber.list,getApplicationContext());
        gridView.setAdapter(gridAdapt);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"我被单击啦",Toast.LENGTH_SHORT).show();
                gridAdapt.removeByData(GetNumber.list.get(position));
            }
        });

    }
}
