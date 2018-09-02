package com.example.lenovo.liweixin1603b20180820.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/8/20.
 */

public interface RequestCallback {
void error(Call call, IOException e);
void success(Call call, Response response);

    void success(String json);
}
