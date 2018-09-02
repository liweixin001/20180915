package com.example.gouwuche.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.gouwuche.Api;
import com.example.gouwuche.bean.CartBean;
import com.example.gouwuche.utils.OkHttpUtils;
import com.example.gouwuche.utils.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/8/21.
 */

public class CartModel {
    //请求购物车数据
    public void getCarts(HashMap<String, String> parmas, String url, final CartCallback cartCallback) {
        OkHttpUtils.getInstance().postData(url, parmas, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {
                if (cartCallback!=null){
                    cartCallback.fail("有错");
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String jsonresult=response.body().string();
                    if (!TextUtils.isEmpty(jsonresult)) {
                                jiexi(jsonresult,cartCallback);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void jiexi(String jsonresult, CartCallback cartCallback) {
        CartBean cartBean=new Gson().fromJson(jsonresult,CartBean.class);
            if (cartCallback!=null&&cartBean!=null){
                cartCallback.success(cartBean);

            }
    }
    public interface CartCallback{
        void success(CartBean cartBean);
        void fail(String msg);
    }
}
