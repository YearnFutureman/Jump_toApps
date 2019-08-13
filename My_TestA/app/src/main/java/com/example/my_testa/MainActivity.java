package com.example.my_testa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public Button b1,b2,wb1,wx1;
    public EditText ed1;
    public TextView tv;
    String realUrl,Reurl;
    String Inurl="snssdk1128://aweme/detail/";
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        wb1 = (Button)findViewById(R.id.wb1);
        ed1 = (EditText)findViewById(R.id.EditText);
        tv =(TextView)findViewById(R.id.Textview1);
        wx1 = (Button)findViewById(R.id.wx1);
        setcontext();

    }

    private void setcontext() {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Inurl="snssdk1128://aweme/detail/";
                String ur4 = "6718134195404688654/?region=CN&mid=6682317848485890828&u_code=204163ej8d0&titleType=title&utm_source=copy_link&utm_campaign=client_share&utm_medium=android&app=aweme&timestamp=1565339783";
                try {
                    Uri uri = Uri.parse(Inurl+ur4);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(intent);
                    Log.e("TAG","跳转成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG","跳转失败");
                }
            }
        });

        /**
         * 抖音获取链接跳转
         */
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    String geturl = ed1.getText().toString();
                    @Override
                    public void run() {

                        URL url;
                        try {
                            //重定向链接
                            url = new URL(geturl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.getResponseCode();
                            realUrl = conn.getURL().toString();
                            conn.disconnect();
                            Log.e("TAG", "真实URL:" + realUrl);
                            //tv.setText("重定向真实链接为:"+realUrl);
                            /**
                             * 分割链接做跳转视图
                             */
                            String[] Reurl=realUrl.split("video/");
                            Log.e("TAG", "分割url:" + Reurl[1]);
                            //tv.setText("分割url:"+Reurl[1]);
                            //获取链接跳转抖音
                            Uri uri = Uri.parse(Inurl+Reurl[1]);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                            startActivity(intent);
                            Log.e("TAG","跳转成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("TAG","跳转失败");
                        }
                    }
                }).start();
            }
        });
        /**
         * 拉取微博
         */
        wb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Inurl="sinaweibo://detail?mblogid="; //sinaweibo://qrcode/拉取微博扫一扫 //拉取微博某篇文章sinaweibo://detail?mblogid=//微博拉取个人主页 sinaweibo://userinfo?uid=5385259443
                String ur4 = "4212233598635280";
                try {
                    Uri uri = Uri.parse(Inurl+ur4);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(intent);
                    Log.e("TAG","跳转成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG","跳转失败");
                }
            }
        });
        /**
         * 拉取微信
         * 微信跳转技术可实现手机浏览器跳转到微信WAP跳转打开微信链接。
         *
         * 可以跳转到指定的网址链接引导用户加粉、公众号关注、微商、文章浏览、投票等。
         *
         * 可以直接跳转微信内置浏览器识别二维码。
         *
         * 可以实现互联网流量导入微信指定页面。
         */
        wx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Inurl1="";
                String ur1="";
                try {
                   Uri uri = Uri.parse("weixin://");//weixin://dl/business/?ticket=xxx  微信内打开网页
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(intent);
                    Log.e("TAG","跳转成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG","跳转失败");
                }
            }
        });

    }
}
