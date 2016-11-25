package com.example.haily.learnuikongjian;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haily.learnuikongjian.phone.GetNumber;
import com.example.haily.learnuikongjian.phone.MyAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
 private CheckBox checkBox_autologin;
 private Button btn_get, btn_post,btn_showNumber,btnSQLiteLogin;
 private EditText username, password;
 private TextView isLogin;
 private ListView lv;
 private boolean isAutoLogin=false;
 private MyAdapter myAdapter;
  SharedPreferences data;
    SharedPreferences.Editor edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        data = getSharedPreferences("data", MODE_PRIVATE);
        edit = data.edit();

        btn_get = (Button) findViewById(R.id.btn_get);
        btnSQLiteLogin = (Button) findViewById(R.id.btn_sqlitelogin);
        btn_showNumber = (Button) findViewById(R.id.btn_showNumber);
        btn_post = (Button) findViewById(R.id.btn_post);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        isLogin = (TextView) findViewById(R.id.isLogin);
        checkBox_autologin = (CheckBox) findViewById(R.id.checkboX_autologin);
        checkBox_autologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isAutoLogin = true;
                    Toast.makeText(getApplicationContext(),"checkBox被选中啦",Toast.LENGTH_SHORT).show();
                } else {
                    edit.clear();
                    edit.commit();
                    System.out.println("清除数据>>>>"+data.getBoolean("isAutoLogin",false));
                    Toast.makeText(getApplicationContext(),"checkBox被取消选中啦>>清除数据",Toast.LENGTH_SHORT).show();
                    isAutoLogin = false;
                }


            }
        });
        btn_get.setOnClickListener(this);
        btnSQLiteLogin.setOnClickListener(this);
        btn_showNumber.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        autoLogin();
        initEdittext();
    }

    private void initEdittext() {


        if (data.getBoolean("isAutoLogin", false)) {
            Toast.makeText(getApplicationContext(), "本次是自动填充输入框的", Toast.LENGTH_SHORT).show();
            checkBox_autologin.setChecked(true);
            username.setText(data.getString("name", ""));
            password.setText(data.getString("pass", ""));
            System.out.println("本次已经记住登录状态");
            System.out.println(data.getString("name", ""));
            System.out.println(data.getString("pass", ""));
        } else {
            System.out.println("本次不会记住登录状态");
        }
    }

    private void autoLogin() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_get:
                doGet();

                break;
            case R.id.btn_post:
                doPost();
                break;


            case R.id.btn_sqlitelogin:
                doSQLiteLogin();
                break;


            case R.id.btn_showNumber:
                System.out.println("开始工作");
                Toast.makeText(getApplicationContext(), "开始工作", Toast.LENGTH_SHORT).show();
                getNumber();
                break;
        }
    }

    private void doSQLiteLogin() {
//        MainActivity main = new MainActivity();
        System.out.println("我被点击了");
        boolean userIsTrue=MainActivity.queryData(username.getText().toString(),password.getText().toString());
        System.out.println(userIsTrue);
        if (userIsTrue) {
            Toast.makeText(getApplicationContext(),"sqlite登陆成功",Toast.LENGTH_SHORT).show();
            System.out.println("sqlite登陆成功");
            isLogin.setText("sqlite登陆成功");

        } else {

            Toast.makeText(getApplicationContext(),"sqlite登陆失败",Toast.LENGTH_SHORT).show();
            isLogin.setText("sqlite登陆失败");
            System.out.println("sqlite登陆失败");
        }

    }

    private void getNumber() {
        GetNumber.GetNumber(this);//得到电话本数据并封装在List集合中
        myAdapter = new MyAdapter(GetNumber.list, this);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(myAdapter);

    }

    private void doPost() {

        Toast.makeText(getApplicationContext(), "Post方式读取数据登录",Toast.LENGTH_SHORT).show();
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                System.out.println("POST输入的输入的用户名是:"+params[1]+"密码是:"+params[2]);
                try {

                    URL url = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                   BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"utf-8"));
                    bw.write("username="+params[1]+"&password="+params[2]);
                    bw.flush();
                    BufferedReader br =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder builder = new StringBuilder();

                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        builder.append(line);
                    }
                    System.out.println("浏览器返回的数据是:"+builder.toString());
                        return builder.toString();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                isLogin.setText("Post方式"+s);
                System.out.println("Post方式"+s);
                if (s.equals("登陆成功")) {
                    System.out.println("服务器返回的数据登录成功匹配完成");
                    if (isAutoLogin) {
                        System.out.println("自动登录已经勾选");
                        System.out.println("开始写入和数据");
                        SharedPreferences.Editor edit = data.edit();
                        edit.putBoolean("isAutoLogin", true);
                        edit.putString("name", username.getText().toString());
                        edit.putString("pass", password.getText().toString());
                        edit.commit();
                        Toast.makeText(getApplicationContext(), "Get方式正在自动保存SharedPreferences数据", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("自动登录没有勾选勾选,清除数据");

                    }

                } else {
                    System.out.println("服务器返回的数据登录成功匹配失败");
                }

            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }
        }.execute("http://hemingli.cn/post.php",username.getText().toString(),password.getText().toString());
    }
    private void doGet() {


        Toast.makeText(getApplicationContext(), "get方式读取数据登录",Toast.LENGTH_SHORT).show();
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                System.out.println("get方式的url是:"+params[0]);
                try {

                    URL url = new URL(params[0]);
                 URLConnection connection = url.openConnection();
                   BufferedReader br =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder builder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                        System.out.println("浏览器返回的数据是:"+builder.toString());
                    return builder.toString();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                isLogin.setText("Get方式"+s);
                System.out.println("Get方式"+s);
                if (s.equals("登录成功")) {
                    System.out.println("服务器返回的数据登录成功匹配完成");
                    if (isAutoLogin) {
                        System.out.println("自动登录已经勾选");
                        System.out.println("开始写入和数据");
                        SharedPreferences.Editor edit = data.edit();
                        edit.putBoolean("isAutoLogin", true);
                        edit.putString("name", username.getText().toString());
                        edit.putString("pass", password.getText().toString());
                        edit.commit();
                        Toast.makeText(getApplicationContext(), "Get方式正在自动保存SharedPreferences数据", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("自动登录没有勾选勾选,清除数据");

                    }
                } else {
                    System.out.println("服务器返回的数据登录成功匹配失败");
                }
            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }
        }.execute("http://hemingli.cn/get.php?username="+username.getText().toString()+"&password="+password.getText().toString());
    }

}
