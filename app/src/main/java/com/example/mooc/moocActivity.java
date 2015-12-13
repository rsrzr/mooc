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
        //��ʼ��
        SMSSDK.initSDK(this, APPKEY, APPSECRETE);
        //������Ϣ
        mBtnBindPhone=(Button) this.findViewById(R.id.btn_bind_phone);

        //���õ���¼�
        mBtnBindPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ע���ֻ���
                RegisterPage registerPage = new RegisterPage();
                //ע��ص��¼�
                registerPage.setRegisterCallback(new EventHandler() {
                    //�¼���ɺ����
                    @Override
                    public void afterEvent ( int event, int result, Object data){
                    //�жϽ���Ƿ��Ѿ����
                        if (result == SMSSDK.RESULT_COMPLETE) {
                    //��ȡ����data
                            //@SuppressWarnings("unchecked")
                            HashMap<String, Object> maps = (HashMap<String, Object>) data;
                            //��ȡ������Ϣ
                            //��ȡ�ֻ�����Ϣ
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
     * �ύ�û���Ϣ
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
