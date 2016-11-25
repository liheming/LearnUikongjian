package com.example.haily.learnuikongjian.guide_viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.haily.learnuikongjian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haily on 2016/11/22.
 */

public class Guide extends AppCompatActivity {
    ViewPager viewPager;
    List<View> views;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);


        viewPager = (ViewPager) findViewById(R.id.vpager);
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one, null));
        views.add(inflater.inflate(R.layout.two, null));
        views.add(inflater.inflate(R.layout.three, null));
        views.get(2).findViewById(R.id.btn_gohome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Guide.this,HomeActivity.class));
            }
        });
        myPagerAdapter = new MyPagerAdapter(this, views);
        viewPager.setAdapter(myPagerAdapter);
        
      
    }
}
