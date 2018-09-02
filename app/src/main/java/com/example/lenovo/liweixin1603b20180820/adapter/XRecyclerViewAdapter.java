package com.example.lenovo.liweixin1603b20180820.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.liweixin1603b20180820.MainActivity;
import com.example.lenovo.liweixin1603b20180820.R;
import com.example.lenovo.liweixin1603b20180820.bean.UserBean;

import java.util.List;

/**
 * Created by lenovo on 2018/8/20.
 */

public class XRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    public static final int TYPE_IMAGE_ONE=0;
    public static final int TYPE_IMAGE_TWO=1;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private Context context;
    private List<UserBean.ResultBean.DataBean>list;
    private View view;
    private View view1;



    public XRecyclerViewAdapter(Context context, List<UserBean.ResultBean.DataBean> list, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==TYPE_IMAGE_ONE){
            view = LayoutInflater.from(context).inflate(R.layout.xrecy_item1,null);
            OneImageHolder oneImageHolder =new OneImageHolder(view);
            view.setOnClickListener(this);
            return oneImageHolder;
        }else if (viewType==TYPE_IMAGE_TWO){
            view1 = LayoutInflater.from(context).inflate(R.layout.xrecy_item2,null);
           TwoImageHolder twoImageHolder =new TwoImageHolder(view1);
           view1.setOnClickListener(this);
            return twoImageHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       UserBean.ResultBean.DataBean dataBean=list.get(position);

        System.out.println("aaaaaaaaaaaa"+dataBean);
        if (holder instanceof OneImageHolder){
            Glide.with(context).load(dataBean.getThumbnail_pic_s()).into(((OneImageHolder) holder).img);
            ((OneImageHolder) holder).tv.setText(dataBean.getTitle());
            ((OneImageHolder) holder).itemView.setTag(position);
        }else if (holder instanceof TwoImageHolder){
            Glide.with(context).load(dataBean.getThumbnail_pic_s()).into(((TwoImageHolder) holder).img1);
            Glide.with(context).load(dataBean.getThumbnail_pic_s02()).into(((TwoImageHolder) holder).img2);
            Glide.with(context).load(dataBean.getThumbnail_pic_s03()).into(((TwoImageHolder) holder).img3);
            ((TwoImageHolder) holder).tv1.setText(list.get(position).getTitle());

            ((TwoImageHolder) holder).itemView.setTag(position);
        }

    }

    @Override
    public int getItemCount() {

        return list.size()==0?0:list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%3==0){
            return TYPE_IMAGE_ONE;
        }else if (position%3==1){
            return TYPE_IMAGE_TWO;
        }else{
            return super.getItemViewType(position);
        }

    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick((Integer) view.getTag());

        }

    }

    private class OneImageHolder extends RecyclerView.ViewHolder{

       ImageView img;
       TextView tv;

        public OneImageHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            tv = itemView.findViewById(R.id.item_tv);
        }
    }
    private class TwoImageHolder extends RecyclerView.ViewHolder{
        ImageView img1,img2,img3;
        TextView tv1;
        public TwoImageHolder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.item_img1);

            img2 = itemView.findViewById(R.id.item_img2);

            img3 = itemView.findViewById(R.id.item_img3);
            tv1 = itemView.findViewById(R.id.item_tv1);
        }
    }


}
