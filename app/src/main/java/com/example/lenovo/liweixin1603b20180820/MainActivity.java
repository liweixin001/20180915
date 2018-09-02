package com.example.lenovo.liweixin1603b20180820;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.liweixin1603b20180820.adapter.OnItemClickListener;
import com.example.lenovo.liweixin1603b20180820.adapter.XRecyclerViewAdapter;
import com.example.lenovo.liweixin1603b20180820.bean.UserBean;
import com.example.lenovo.liweixin1603b20180820.utils.OkHttpUtils;
import com.example.lenovo.liweixin1603b20180820.utils.RequestCallback;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{
    private UserBean userBean;
    private Handler handler=new Handler();
    private ImageView img,img1;
    private XRecyclerView xRecy;
    private List<UserBean.ResultBean.DataBean> list;
    private UMShareAPI umShareAPI;



private OnItemClickListener onItemClickListener;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        umShareAPI=UMShareAPI.get(this);
        initView();
        initData();

//        new presenter(this);

    }

    private void initView() {
        img = findViewById(R.id.user_img);
        xRecy = findViewById(R.id.XRecy);
        img1 = findViewById(R.id.item_img);
        tv = findViewById(R.id.user_name);

//        pres.showRecy();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder=new Request.Builder();
        final Request request= builder.get().url(Api.BASE_URL).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        jiexi(result);
                    }
                });
            }
        });

    }

    private void jiexi(String result) {

        System.out.println("ccccccccccccc"+result);
        userBean=new Gson().fromJson(result,UserBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                fillData();
            }
        });

    }

    private void fillData() {
        XRecyclerViewAdapter adapter=new XRecyclerViewAdapter(this,userBean.getResult().getData(),onItemClickListener );

        System.out.println("aaaaaaaaaaaaaaa"+adapter);
        xRecy.setLayoutManager(new LinearLayoutManager(this));
        xRecy.setAdapter(adapter);
adapter.setOnItemClickListener((OnItemClickListener) this);


    }

    public void touxiang(View view) {
        ObjectAnimator scaleX=ObjectAnimator.ofFloat(img,"scaleX",0,1);
        scaleX.setDuration(2000);
        scaleX.start();
        ObjectAnimator scaleY=ObjectAnimator.ofFloat(img,"scaleY",0,1);
        scaleY.setDuration(2000);
        scaleY.start();
    }



    @Override
    public void onItemClick(int postion) {
//        //TODO 条目点击
//        ObjectAnimator alpha=ObjectAnimator.ofFloat(img1,"alpha",0f,1f,0.5f,1f);
//        alpha.setDuration(4000);
//        alpha.setRepeatCount(1);
//        alpha.start();
    }

    public void qqLogin(View view) {
        UMAuthListener authListener = new UMAuthListener() {
            /**
             * @desc 授权开始的回调
             * @param platform 平台名称
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {

             }

            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "成功了"+data, Toast.LENGTH_LONG).show();

//                Glide.with(this).load(data.get()).into(img);
//                tv.setText(data.put("","").);

            System.out.println("dddddddddddddddd"+data);
            }

            /**
             * @desc 授权失败的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
            }

            /**
             * @desc 授权取消的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             */
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
            }
        };
        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);

        System.out.println("dddddddddddddddd"+authListener);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
