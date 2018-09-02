package com.example.gouwuche.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gouwuche.CartActivity;
import com.example.gouwuche.R;
import com.example.gouwuche.bean.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by lenovo on 2018/8/21.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
   private Context mcontext;
   private List<CartBean.DataBean>cartlist;
   private CartBean cartBean;

    public CartAdapter(Context context, List<CartBean.DataBean> list) {
        mcontext= context;
        cartlist = list;
    }



    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(mcontext).inflate(R.layout.cart_item_layout,parent,false);
        CartViewHolder cartViewHolder=new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        List<CartBean.DataBean.ListBean> list = cartlist.get(position).getList();
        holder.nameTV.setText(list.get(position).getTitle());
//        holder.proudXRV.setLayoutManager(new LinearLayoutManager(mcontext));
//        holder.proudXRV.setAdapter(new ProudCartAdapter(mcontext,bean.getList()));
    }

    @Override
    public int getItemCount() {
        return cartlist.size()==0?0:cartlist.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        TextView nameTV;
        private XRecyclerView proudXRV;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.cart_item_cb);
            nameTV=itemView.findViewById(R.id.cart_item_name);
            proudXRV=itemView.findViewById(R.id.proudXRV);

        }
    }
}
