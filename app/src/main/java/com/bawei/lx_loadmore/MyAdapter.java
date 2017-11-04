package com.bawei.lx_loadmore;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Zhang on 2017/10/25.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Mybean.StoriesBean> list;
    private Context context;


    public MyAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void SetData(List<Mybean.StoriesBean> list, int page) {
        if (page == 5) {
            this.list.clear();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));

        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Mybean.StoriesBean sb = list.get(position);
        ((MyViewHolder) holder).tv.setText(sb.getTitle());
        if (sb.getImages() != null) {
            ImageLoader.getInstance().displayImage(sb.getImages().get(0), ((MyViewHolder) holder).img);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
            img = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }

}
