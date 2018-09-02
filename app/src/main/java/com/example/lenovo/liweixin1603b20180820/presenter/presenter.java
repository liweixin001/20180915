package com.example.lenovo.liweixin1603b20180820.presenter;

import com.example.lenovo.liweixin1603b20180820.bean.UserBean;
import com.example.lenovo.liweixin1603b20180820.model.IModel;
import com.example.lenovo.liweixin1603b20180820.view.IView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/8/20.
 */

public class presenter {
    private IView view;
    private UserBean userBean;
    private IModel model;

    public presenter(IView view) {
        this.view = view;
        this.model = model;
    }
    public void showRecy(){
        model.recy(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                if (200 == response.code()) {
                    String result = response.body().string();
                    userBean = gson.fromJson(result, UserBean.class);
                    view.showRecy(userBean);
                }
            }
        });
    }
public void onDestory(){
        view=null;
}
}
