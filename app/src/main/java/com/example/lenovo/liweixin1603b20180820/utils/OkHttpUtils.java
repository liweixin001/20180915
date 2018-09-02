package com.example.lenovo.liweixin1603b20180820.utils;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/8/20.
 */

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler=new Handler();

    public OkHttpUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public void postData(HashMap<String,String >prams,String url, final RequestCallback requestCallback){
        FormBody.Builder builder = new FormBody.Builder();
        if (prams!=null&&prams.size()>0){
            for (Map.Entry<String, String> stringStringEntry : prams.entrySet()) {
                builder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }
        }
        final Request request = new Request.Builder()
                .post(builder.build())
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback!=null){
                    requestCallback.error(call, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallback!=null){
                    requestCallback.success(call, response);
                }
            }
        });
    }


//    public void getData(String url, final OkCallBack okCallBack){
//        Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//
//        Call call = new OkHttpClient().newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(final Call call, final IOException e) {
//                if (okCallBack!=null){
//                    okCallBack.onFailure(e);
//                }
//            }
//
//            @Override
//            public void onResponse(final Call call, final Response response) throws IOException {
//                if (response!=null&&response.isSuccessful()){
//                    final String json=response.body().string();
//                    okCallBack.onResponse(json);
//                }
//            }
//        });
//    }
//    public interface OkCallBack {
//        void onFailure(Exception e);
//
//        void onResponse(String json);
//    }

}
