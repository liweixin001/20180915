package com.example.gouwuche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.gouwuche.adapter.CartAdapter;
import com.example.gouwuche.bean.CartBean;
import com.example.gouwuche.presenter.CartPresenter;
import com.example.gouwuche.view.IcartView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity implements IcartView {
    private CartPresenter cartPresenter;
    private XRecyclerView xRecyclerView;
    private CartAdapter cartAdapter;
    private List<CartBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initData();
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        xRecyclerView = findViewById(R.id.caetRv);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", "71");
        cartPresenter = new CartPresenter(this);
        cartPresenter.getCart(params, Api.GET_CART);

    }

    @Override
    public void success(CartBean cartBean) {
        CartBean.DataBean dataBean = cartBean.getData().get(1);
        CartBean.DataBean.ListBean listBean = dataBean.getList().get(1);
        String price = listBean.getPrice();
        Log.d("sdsd",price);
//        if (cartBean != null && cartBean.getData() != null) {
//            cartAdapter = new CartAdapter(this,list);
//
//            xRecyclerView.setAdapter(cartAdapter);
//        }
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
