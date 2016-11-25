package com.example.haily.learnuikongjian.guide_viewpager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.haily.learnuikongjian.R;

public class WelcomeActivity extends AppCompatActivity {
    Boolean isfirstIn = false;

    final int TIME = 2000;
    final int HOME = 1000;
    final int GUIDE = 1001;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GUIDE:
                    GoGuide();

                    break;

                case HOME:
                    GoHome();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }




    private void initView() {
        SharedPreferences sp = getSharedPreferences("hello", MODE_PRIVATE);
       isfirstIn = sp.getBoolean("isfirstIn", true);
        System.out.println(isfirstIn);
        if (!isfirstIn) {

            handler.sendEmptyMessageDelayed(HOME, TIME);

        } else {

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isfirstIn", false);

            editor.commit();
            handler.sendEmptyMessageDelayed(GUIDE, TIME);
        }

    }

    void GoHome() {
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
    }

    void GoGuide() {
        startActivity(new Intent(WelcomeActivity.this, Guide.class));
    }
}
