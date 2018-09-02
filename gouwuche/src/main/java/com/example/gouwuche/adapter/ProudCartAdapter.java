package com.example.gouwuche.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gouwuche.R;
import com.example.gouwuche.bean.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by lenovo on 2018/8/21.
 */

public class ProudCartAdapter extends RecyclerView.Adapter<ProudCartAdapter.CartViewHolder>{
   private Context context;
    private CartBean cartBean;

   private List<CartBean.DataBean.ListBean>listBeanList;

    public ProudCartAdapter(Context context, List<CartBean.DataBean.ListBean> listBeanList) {
        this.context = context;
        this.listBeanList = listBeanList;
    }



    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.cart_item_child,parent,false);
        CartViewHolder cartViewHolder=new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartBean.DataBean.ListBean bean = listBeanList.get(position);
        holder.child_tv.setText(bean.getTitle());
        holder.child_price.setText("优惠价：￥" + bean.getBargainPrice());

        String[] img = bean.getImages().split("\\|");
        if (img != null && img.length > 0) {
            Glide.with(context).load(img[0]).into(holder.child_image);
        }
    }

    @Override
    public int getItemCount() {
        return listBeanList.size()==0?0:listBeanList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CheckBox child_cb;
        private TextView child_tv,child_price;
        private ImageView child_image;

        public CartViewHolder(View itemView) {
            super(itemView);
            child_price=itemView.findViewById(R.id.child_price);
            child_cb=itemView.findViewById(R.id.child_cb);
            child_tv=itemView.findViewById(R.id.child_tv);
            child_image=itemView.findViewById(R.id.child_image);


        }
    }
}
