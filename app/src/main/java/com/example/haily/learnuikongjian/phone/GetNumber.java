package com.example.haily.learnuikongjian.phone;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haily on 2016/11/22.
 */

public class GetNumber {

    public static ArrayList<PhoneInfo> list = new ArrayList<>();


    public static String GetNumber(Context context) {
       Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,null);
        while (cursor.moveToNext()) {
            String name ;
            String number;
            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            System.out.println("姓名:"+name+"  号码:"+number);
            PhoneInfo phoneInfo = new PhoneInfo(name,number);
            list.add(phoneInfo);

        }


        return null;
    }
}
