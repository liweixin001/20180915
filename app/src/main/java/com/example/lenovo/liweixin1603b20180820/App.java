package com.example.lenovo.liweixin1603b20180820;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by lenovo on 2018/8/20.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initUm();
    }

    private void initUm() {
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0


        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        UMConfigure.setLogEnabled(true);

    }
}
