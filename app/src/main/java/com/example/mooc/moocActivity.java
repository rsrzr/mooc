package com.example.mooc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class moocActivity extends Activity {

    Button mBtnBindPhone;
    String APPKEY="d4783f6fbf62";
    String APPSECRETE="2572bcc766e8a7d0a10c86ba1b9d0f0c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mooc);
        //初始化
        SMSSDK.initSDK(this, APPKEY, APPSECRETE);
        //配置信息
        mBtnBindPhone=(Button) this.findViewById(R.id.btn_bind_phone);

        //设置点击事件
        mBtnBindPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册手机号
                RegisterPage registerPage = new RegisterPage();
                //注册回调事件
                registerPage.setRegisterCallback(new EventHandler() {
                    //事件完成后调用
                    @Override
                    public void afterEvent ( int event, int result, Object data){
                    //判断结果是否已经完成
                        if (result == SMSSDK.RESULT_COMPLETE) {
                    //获取数据data
                            //@SuppressWarnings("unchecked")
                            HashMap<String, Object> maps = (HashMap<String, Object>) data;
                            //获取国家信息
                            //获取手机号信息
                            String country = (String) maps.get("country");
                            String phone = (String) maps.get("phone");
                            submitUserInfo(country,phone);
                        }
                    }
                });
                }
            });
    }
    /**
     * 提交用户信息
     * @param country
     * @param phone
     */
    public void submitUserInfo(String country,String phone){
        Random r=new Random();

        String uid=Math.abs(r.nextInt())+"";
        String nickName="IMooc";
        SMSSDK.submitUserInfo(uid,nickName,null,country,phone);
    }
                @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mooc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
