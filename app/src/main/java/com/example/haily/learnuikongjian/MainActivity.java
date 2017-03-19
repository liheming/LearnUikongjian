package com.example.haily.learnuikongjian;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.haily.learnuikongjian.note.NoteMainAty;
import com.example.haily.learnuikongjian.phone.GetNumber;
import com.example.haily.learnuikongjian.phone.MyAdapter;
import com.example.haily.learnuikongjian.phone.PhoneInfo;

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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener, ExpandableListView.OnChildClickListener {
     View ga;
    private View view_custom;
    private Button btn_show;
    private AlertDialog alert = null;
    public static MySQLitedatabase mydb;
    Spinner spinner, spinner_two;
    public static SQLiteDatabase db;
    public Context context;
    private AutoCompleteTextView url_auto_textV;
    private EditText  edit_name;
    private ToggleButton togConVoi;
    private Button showDate, changeProgress, btn_open_activity, createNOTIFY, btnReadSrc, btnOpenFrag, btn_openNote, btnbtn, btn_readJson, btn_writeJson;
    private Switch aSwitch;
    private RadioButton male;
    private CheckBox cb1, cb2, cb3, cb4;
    private RadioGroup sex;
    private View view;
    ExpandableListView expandableListView;
    private TextView textView, showCode;
    ArrayList<String> itemdata = null;
    ArrayList<ArrayList<PhoneInfo>> groupdata = null;


    private final String[] fruits = new String[]{"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
    private ProgressBar downProgress;
    private SeekBar seekBar;
    String[] urlData = {"www.baidu.com","hemingli.cn","www.taobao.com","www.jd.com"};


    int count = 1;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 666:
                    progressDialog.setProgress(value);
                    System.out.println(value);
                    break;
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
    private static final String TAG = "MainActivity";
    private ProgressDialog progressDialog;
    private int value;
    /**
     * language : [{"id":1,"ide":"eclipse","name":"java"},{"id":2,"ide":"xcode","name":"swift"},{"id":3,"ide":"visual Studio","name":"c#"}]
     * cat : it
     */

    private String cat;
    private List<LanguageBean> language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "无用信息");
        Log.d(TAG, "调试信息");
        Log.i(TAG, "普通信息");
        Log.w(TAG, "警告信息");
        Log.e(TAG, "错误信息");
        mydb = new MySQLitedatabase(this);
        addDb();//添加数据到SQLiet数据库
        queryData("liheming", "123456"); //查询数据库的内容
        expandableListView = (ExpandableListView) findViewById(R.id.expand_list_view);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner_two = (Spinner) findViewById(R.id.spinner_two);
        GetNumber.GetNumber(this);

        spinner_two.setAdapter(new MyAdapter(GetNumber.list, this));
        //ExpandableListView(可折叠列表)的基本使用

        itemdata = new ArrayList<>();
        itemdata.add("好友");
        itemdata.add("同学");
//        itemdata.add("朋友");
//        itemdata.add("微信");
        GetNumber.GetNumber(getApplicationContext());
        groupdata = new ArrayList<>();
        groupdata.add(0,GetNumber.list);
        groupdata.add(1,GetNumber.list);
        groupdata.add(2,GetNumber.list);
        groupdata.add(3,GetNumber.list);
        groupdata.add(4,GetNumber.list);
        expandableListView.setOnChildClickListener(this);


        MyBaseExpandableListAdapter expandAdapt = new MyBaseExpandableListAdapter(itemdata,groupdata,getApplicationContext());
        expandableListView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        expandableListView.setAdapter(expandAdapt);
        showDate = (Button) findViewById(R.id.showDate);
        url_auto_textV  = (AutoCompleteTextView) findViewById(R.id.url_auto_textV);

        ArrayAdapter <String> adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,urlData);
        url_auto_textV.setAdapter(adapter);

        btnReadSrc = (Button) findViewById(R.id.readSrc_Button);
        btn_open_activity = (Button) findViewById(R.id.btn_open_activity);
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
        btn_open_activity.setOnClickListener(this);
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
        spinner.setOnItemSelectedListener(this);
        spinner_two.setOnItemSelectedListener(this);
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

    public static boolean queryData(String username, String password) {

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
            if (name.equals(username) && pass.equals(password)) {
//                Toast.makeText(MainActivity.this, "查询数据库用户："+name+"成功!!!", Toast.LENGTH_SHORT);
                System.out.println("查询数据库用户：" + name + "成功!!!");

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


                  progressDialog = new ProgressDialog(this);
                    progressDialog.setMax(100);
                    progressDialog.setTitle("文件读取中");
                    progressDialog.setMessage("文件加载中,请稍后...");
                    //这里设置为不可以通过按取消按钮关闭进度条
                    progressDialog.setCancelable(false);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    //这里设置的是是否显示进度,设为false才是显示的哦！
                    progressDialog.setIndeterminate(false);
                    progressDialog.show();


                    new Thread(new Runnable() {


                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    Thread.sleep(1000);
                                    handler.sendEmptyMessage(666);
                                    value++;
                                    if (value == 100) {
                                     progressDialog.dismiss();
                                        progressDialog.cancel();
                                    }
//                                    System.out.println("当前的值是:" + value);
//                                    progressDialog.setProgress(value);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }).start();




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
                ProgressDialog.show(MainActivity.this, "更新", "正在下载中....请稍后", false, true);
    }

    private long exittime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            System.out.println( "再按一次退出程序");
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT);

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - exittime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT);

           exittime =  System.currentTimeMillis();
        } else {

            System.exit(0);
        }

        super.onBackPressed();

    }

    @Override
    public void onClick(View v) {
        System.out.println(v.getId());
        switch (v.getId()) {

            case R.id.createNOTIFY:
                //自定义dialog
//                AlertDialog.Builder builder2 = new AlertDialog.Builder(getApplicationContext());
//               view_custom = MainActivity.this.getLayoutInflater().inflate(R.layout.view_dialog_custom, null, false);
//                builder2.setView(view_custom);
//                builder2.setCancelable(false);
//              alert = builder2.create();
//                view_custom.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alert.dismiss();
//                    }
//                });
//
//                view_custom.findViewById(R.id.btn_blog).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getApplicationContext(), "访问博客", Toast.LENGTH_SHORT).show();
//                        Uri uri = Uri.parse("http://blog.csdn.net/coder_pig");
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
//                        alert.dismiss();
//                    }
//                });
//
//                view_custom.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getApplicationContext(), "对话框已关闭~", Toast.LENGTH_SHORT).show();
//                        alert.dismiss();
//                    }
//                });
//
//                alert.show();





//                getSystemService(Context.NOTIFICATION_SERVICE).notify(1300,new NotificationCompat.Builder(getApplicationContext())
//                        .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("黎合明")
//                        .setContentText("您有" + count + "个新消息").build());



                count++;
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("黎合明");
                builder.setContentText("您有" + count + "个新消息");
                Notification notification = builder.build();

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1200, notification);


                AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
//                builder1.setTitle("我是标题党").setNegativeButton("取消",null).setNeutralButton("忽略",null).setPositiveButton("确定",null).create().show();



                builder1.setTitle("我是标题党")
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
                });
                builder1.create().show();


                break;


            case R.id.btn_openNote: //打开记事本
                startActivity(new Intent(MainActivity.this, NoteMainAty.class));


                break;


            case R.id.btn_open_activity://打开第三个Activity

                startActivity(new Intent(MainActivity.this, ThreeActivity.class));

                break;


            case R.id.readSrc_Button://读取网页源代码
                System.out.println("开始读取网页数据");
                System.out.println("http://"+url_auto_textV.getText());
                ReadUrl("http://"+url_auto_textV.getText().toString());


                break;


            case R.id.btn_readJson://读取json数据

                readJson();

                break;
            case R.id.btn_writeJson://写入json数:

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



                    publishProgress();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(parent.getItemIdAtPosition(position));
        switch (parent.getId()) {

//            case R.id.expand_list_view:
//                System.out.println("99999999");
//                TextView text = (TextView) view.findViewById(R.id.text_ming);
//                TextView text_say = (TextView) view.findViewById(R.id.text_say);
//                Toast.makeText(getApplicationContext(), "hello你的名字是:" + text.getText().toString()+"手机号码是:"+text_say.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;

            case R.id.spinner:

                Toast.makeText(getApplicationContext(), "hello world" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                break;

            case R.id.spinner_two:
                TextView textName = (TextView) view.findViewById(R.id.text_number);
                Toast.makeText(getApplicationContext(), "hello你点击的手机号码是:" + textName.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(getApplicationContext(), "hello你点击的手机号码是:"+groupdata.get(groupPosition).get(childPosition).getNumber(), Toast.LENGTH_SHORT).show();
        return false;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public List<LanguageBean> getLanguage() {
        return language;
    }

    public void setLanguage(List<LanguageBean> language) {
        this.language = language;
    }

    public static class LanguageBean {
        /**
         * id : 1
         * ide : eclipse
         * name : java
         */

        private int id;
        private String ide;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIde() {
            return ide;
        }

        public void setIde(String ide) {
            this.ide = ide;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
