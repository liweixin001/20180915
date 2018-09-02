package com.example.gouwuche.presenter;

import com.example.gouwuche.bean.CartBean;
import com.example.gouwuche.model.CartModel;
import com.example.gouwuche.view.IcartView;

import java.util.HashMap;

/**
 * Created by lenovo on 2018/8/21.
 */

public class CartPresenter {
    private IcartView icartView;

    private CartModel cartModel;
    public CartPresenter(IcartView icartView) {
        cartModel=new CartModel();
        attachView(icartView);



    }
    public void attachView(IcartView icartView){
        this.icartView=icartView;
    }
    public void getCart(HashMap<String,String>parmas,String url){
        cartModel.getCarts(parmas, url, new CartModel.CartCallback() {
            @Override
            public void success(CartBean cartBean) {
                if (icartView!=null){
                    icartView.success(cartBean);
                }
            }

    @Override
    public void fail(String msg) {
        if (icartView!=null){
            icartView.failure(msg);
        }
    }
});
    }
    public void detachView(){
        this.icartView=null;
    }

}
