package com.example.gouwuche.view;

import com.example.gouwuche.bean.CartBean;

/**
 * Created by lenovo on 2018/8/21.
 */

public interface IcartView {
    void  success(CartBean cartBean);
    void failure(String msg);
}
