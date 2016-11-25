package com.example.haily.learnuikongjian;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.haily.learnuikongjian.note.NoteMainAty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    public static MySQLitedatabase mydb;

    public static SQLiteDatabase db;
    public Context context;
    private EditText edut_url, edit_name;
    private ToggleButton togConVoi;
    private Button showDate, changeProgress, createNOTIFY, btnReadSrc, btnOpenFrag,btn_openNote, btnbtn, btn_readJson, btn_writeJson;
    private Switch aSwitch;
    private RadioButton male;
    private CheckBox cb1, cb2, cb3, cb4;
    private RadioGroup sex;
    private View view;
    private TextView textView, showCode;
    private final String[] fruits = new String[]{"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
    private ProgressBar downProgress;
    private SeekBar seekBar;

    int count = 1;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 1:
                    textView.setText("开始干活啦11111111");
                    break;

                case 3:
                    textView.setText("开始干活啦33333333");
                    break;
            }
        }
    };
    private String single;
    private static final String TAG="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"无用信息");
        Log.d(TAG,"调试信息");
        Log.i(TAG,"普通信息");
        Log.w(TAG,"警告信息");
        Log.e(TAG,"错误信息");
        mydb = new MySQLitedatabase(this);
        addDb();//添加数据到SQLiet数据库
        queryData("liheming","123456"); //查询数据库的内容



        showDate = (Button) findViewById(R.id.showDate);
        edut_url = (EditText) findViewById(R.id.url_Edit);
        btnReadSrc = (Button) findViewById(R.id.readSrc_Button);
        btn_openNote = (Button) findViewById(R.id.btn_openNote);
        btnOpenFrag = (Button) findViewById(R.id.btnOpenFragment);
        changeProgress = (Button) findViewById(R.id.changeProgress);
        btn_readJson = (Button) findViewById(R.id.btn_readJson);
        btn_writeJson = (Button) findViewById(R.id.btn_writeJson);
        downProgress = (ProgressBar) findViewById(R.id.downProgress);
        aSwitch = (Switch) findViewById(R.id.switchVoice);
        togConVoi = (ToggleButton) findViewById(R.id.toggleControVoice);
        male = (RadioButton) findViewById(R.id.male);
        textView = (TextView) findViewById(R.id.showDatetext);
        showCode = (TextView) findViewById(R.id.showCode);

        createNOTIFY = (Button) findViewById(R.id.createNOTIFY);
        sex = (RadioGroup) findViewById(R.id.sex);
        cb1 = (CheckBox) findViewById(R.id.camera);
        cb2 = (CheckBox) findViewById(R.id.bicycle);
        cb3 = (CheckBox) findViewById(R.id.music);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

        createNOTIFY.setOnClickListener(this);
        showDate.setOnClickListener(this);
        btn_openNote.setOnClickListener(this);
        btnReadSrc.setOnClickListener(this);
        btn_readJson.setOnClickListener(this);
        btn_writeJson.setOnClickListener(this);
        btnOpenFrag.setOnClickListener(this);
        changeProgress.setOnClickListener(this);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        aSwitch.setOnCheckedChangeListener(this);
        togConVoi.setOnCheckedChangeListener(this);
        sex.setOnCheckedChangeListener(this);
        textView.setText("默认值");
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("当前进度是：" + progress);
                System.out.println("当前进度是：" + progress + fromUser);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        message.what = 4;
//                        handler.sendMessage(message);
//                    }
//                }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                System.out.println("触碰seekbar");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("松开seekbar");
            }
        });


        findViewById(R.id.showDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("最大值是：" + downProgress.getMax() + "当前的进度值是：" + downProgress.getProgress());
                Toast.makeText(getApplicationContext(), "当前的进度值是：" + downProgress.getProgress(), Toast.LENGTH_SHORT);
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.append("" + hourOfDay + "小时/" + minute + "分钟/");
                    }
                }, 17, 20, true).show();


                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textView.append("" + year + "年/" + month + "月/" + dayOfMonth + "日");
                        System.out.print("" + year + "年/" + month + "月/" + dayOfMonth + "日");
                    }
                }, 2015, 10, 5).show();


            }
        });

    }

    public static boolean queryData(String username,String password) {

//        mydb = new MySQLitedatabase();
        System.out.println("打开数据库users");
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String pass = cursor.getString(cursor.getColumnIndex("pass"));
            builder.append(cursor.getString(cursor.getColumnIndex("_id")));
            builder.append(name);
            builder.append(pass);
            if (name.equals(username) && pass.equals(password)){
//                Toast.makeText(MainActivity.this, "查询数据库用户："+name+"成功!!!", Toast.LENGTH_SHORT);
            System.out.println("查询数据库用户："+name+"成功!!!");

            return true;
            }

        }
        System.out.println("查询数据库的内容是:" + builder.toString());

//        db.close();

        return false;
    }


    private void addDb() {
        mydb = new MySQLitedatabase(this);
        System.out.println("创建数据库users");
        db = mydb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", "haily");
        cv.put("pass", "1234");
        db.insert("user", null, cv);
        System.out.println("插入数据");
//        db.close();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {

            case R.id.sex:
                Toast.makeText(getApplicationContext(), "你选择的是:" + buttonView.getText() + isChecked, Toast.LENGTH_SHORT).show();
                break;

            case R.id.camera:

                textView.setText("你选择的是:" + buttonView.getText() + isChecked);
                Toast.makeText(getApplicationContext(), "你选择的是:" + buttonView.getText() + isChecked, Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:

                textView.setText("你选择的是:" + buttonView.getText());
                Toast.makeText(getApplicationContext(), "你选择的是:" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.bicycle:

                textView.setText("你选择的是:" + buttonView.getText());
                Toast.makeText(getApplicationContext(), "你选择的是:" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.toggleControVoice:
                if (isChecked) {


                    textView.setText("状态发生改变了 Toggle开关被打开了");
                    Toast.makeText(getApplicationContext(), "状态发生改变了 Toggle开关被打开了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "状态发生改变了 Toggle开关被关闭啦了", Toast.LENGTH_SHORT).show();
                    textView.setText("状态发生改变了  Toggle开关被关闭啦了");
                }
                break;

            case R.id.switchVoice:
                if (isChecked) {
                    textView.setText("状态发生改变了 SWITCH开关被打开了");
                    Toast.makeText(getApplicationContext(), "状态发生改变了 SWITCH开关被打开了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "状态发生改变了 SWITCH开关被关闭啦了", Toast.LENGTH_SHORT).show();
                    textView.setText("状态发生改变了 SWITCH开关被关闭啦了");
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) findViewById(checkedId);
        textView.setText("选择发生了改变" + radioButton.getText().toString());
        Toast.makeText(getApplicationContext(), "选择发生了改变" + radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.createNOTIFY:
                count++;
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("黎合明");
                builder.setContentText("您有" + count + "个新消息");
                Notification notification = builder.build();

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1200, notification);


                new AlertDialog.Builder(MainActivity.this).setTitle("我是标题党")
                        .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("你选择了" + fruits[which]);
                                single = fruits[which];

                            }
                        }).setView(view).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();


                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();


                break;


            case R.id.btn_openNote: //打开记事本
            startActivity(new Intent(MainActivity.this, NoteMainAty.class));


                break;


            case R.id.readSrc_Button:
                System.out.println("开始读取网页数据");
                System.out.println(edut_url.getText());
                ReadUrl(edut_url.getText().toString());


                break;


            case R.id.btn_readJson:

                readJson();

                break;
            case R.id.btn_writeJson:

                writeJson();

                break;


            case R.id.btnOpenFragment:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;

            case R.id.showDate:


                System.out.println("最大值是：" + downProgress.getMax() + "当前的进度值是：" + downProgress.getProgress());
                Toast.makeText(getApplicationContext(), "当前的进度值是：" + downProgress.getProgress(), Toast.LENGTH_SHORT);
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.append("" + hourOfDay + "小时/" + minute + "分钟/");
                    }
                }, 17, 20, true).show();


                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textView.append("" + year + "年/" + month + "月/" + dayOfMonth + "日");
                        System.out.print("" + year + "年/" + month + "月/" + dayOfMonth + "日");
                    }
                }, 2015, 10, 5).show();


                break;

            case R.id.changeProgress:


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int pro = 0;
                        Message message = new Message();
//                                message.arg1 = 2;
                        message.what = 1;
                        handler.sendMessage(message);
                        while (true) {
                            try {
                                Thread.sleep(2000);

                                pro++;
                                if (pro == 100) {
                                    pro = 0;
                                }
                                System.out.println("hello world" + pro);
                                downProgress.setProgress(pro);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
                break;
        }
    }

    private void writeJson() {


        try {

            JSONObject root = new JSONObject();
            root.put("cat", "it");
            JSONObject lan1 = new JSONObject();
            lan1.put("id", 1);
            lan1.put("ide", "eclipse");
            lan1.put("name", "java");

            JSONObject lan2 = new JSONObject();
            lan2.put("id", 2);
            lan2.put("ide", "XCode");
            lan2.put("name", "swift");

            JSONObject lan3 = new JSONObject();
            lan3.put("id", 3);
            lan3.put("ide", "visual Studio");
            lan3.put("name", "C#");

            JSONArray arr = new JSONArray();
            arr.put(lan1);
            arr.put(lan2);
            arr.put(lan3);
            root.put("language", arr);
            System.out.println(root);


            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard, "demo1.json");
//            file.createNewFile();
            System.out.println("文件路径是:" + file.getPath());

//           FileOutputStream fos = openFileOutput("demo.json",Context.MODE_PRIVATE);

            FileOutputStream fos = new FileOutputStream(file);

            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(root.toString());
            Toast.makeText(getApplicationContext(), "写入数据成功", Toast.LENGTH_SHORT).show();
            bw.flush();
            bw.close();
            osw.close();
            fos.close();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void readJson() {


        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard, "demo1.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

//            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("demo.json")));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            System.out.println(builder.toString());
            JSONObject root = new JSONObject(builder.toString());
            textView.setText(root.toString());
            JSONArray jsonArray = root.getJSONArray("language");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject arr = (JSONObject) jsonArray.get(i);
                System.out.println("id=" + arr.getInt("id") + " ide=" + arr.getString("ide") + " name=" + arr.getString("name"));
            }


//            System.out.println(jsonArray.get(1));
//            System.out.println(jsonArray.get(0));

            System.out.println("cat=" + root.getString("cat"));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void ReadUrl(final String url) {


        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                showCode.setText(s);
                System.out.println(s);
                super.onPostExecute(s);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(MainActivity.this, "开始读取源代码", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            @Override
            protected String doInBackground(String... params) {
                try {


                    URL url1 = new URL(params[0]);
                    System.out.println(params[0]);
                    URLConnection connection = url1.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    br.close();


                    return builder.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(url);


    }

}
