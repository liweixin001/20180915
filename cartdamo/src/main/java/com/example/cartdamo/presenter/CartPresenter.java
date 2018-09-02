package com.example.cartdamo.presenter;

import com.example.cartdamo.model.CartModel;
import com.example.cartdamo.bean.CartBean;
import com.example.cartdamo.view.IcartView;

import java.util.HashMap;

/**
 * Created by lenovo on 2018/8/22.
 */

public class CartPresenter {
    private IcartView icartView;
    private CartModel cartModel;

    public CartPresenter(IcartView icartView) {
        this.icartView = icartView;
        cartModel=new CartModel();
    }
public void getCart(HashMap<String,String>params,String url){
        cartModel.getCart(params, url, new CartModel.CartCallBack() {
            @Override
            public void success(CartBean cartBean) {
                icartView.success(cartBean);
            }

            @Override
            public void fail(String msg) {
                icartView.failure(msg);
            }
        });
}

}
