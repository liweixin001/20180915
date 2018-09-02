package com.example.cartdamo.view;

import com.example.cartdamo.bean.CartBean;

/**
 * Created by lenovo on 2018/8/22.
 */

public interface IcartView {
void success(CartBean cartBean);
void  failure(String msg);
}

