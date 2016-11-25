package com.example.haily.learnuikongjian.phone;

import android.content.Context;

/**
 * Created by haily on 2016/11/22.
 */

public class PhoneInfo {
//    Context context;
    private String name;
    private String number;

    PhoneInfo(String name , String number) {
        this.name = name;
        this.number = number;
    }



    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
