package com.example.ycg.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ycg.myapp.activity.OpenInfoActivity;
import com.example.ycg.myapp.bean.Hot;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.ArrayList;
import java.util.List;

public class HotGridAdapter extends BaseAdapter {
    Context context;
    List<Hot.InfoBean.Push2Bean> push2Been = new ArrayList<>();

    public HotGridAdapter(List<Hot.InfoBean.Push2Bean> push2Been, Context context) {
        this.push2Been = push2Been;
        this.context = context;
    }

    @Override
    public int getCount() {
        return push2Been.size();
    }

    @Override
    public Object getItem(int position) {
        return push2Been.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.hot_grid_item,null,false);
            viewHolder=new ViewHolder(convertView);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(HttpUtils.BASE_URL+push2Been.get(position).getLogo()).into(viewHolder.imageView);
        viewHolder.textView.setText(push2Been.get(position).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OpenInfoActivity.class);
                intent.putExtra("id",push2Been.get(position).getAppid());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View v){
            imageView= (ImageView) v.findViewById(R.id.imageView);
            textView= (TextView) v.findViewById(R.id.textView4);
            v.setTag(this);
        }
    }
}

