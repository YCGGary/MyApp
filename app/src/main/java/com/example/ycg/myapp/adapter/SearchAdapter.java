package com.example.ycg.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ycg.myapp.activity.GiftInfoActivity;
import com.example.ycg.myapp.bean.Search;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchAdapter extends BaseAdapter {
    Context context;
    List<Search.ListBean> list;

    public SearchAdapter(Context context, List<Search.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gift_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(HttpUtils.BASE_URL + list.get(position).getIconurl())
                .placeholder(R.mipmap.def_loading).centerCrop().resize(100, 100).into(viewHolder.img_game);
        viewHolder.tv_name.setText(list.get(position).getGname());
        viewHolder.tv_type.setText(list.get(position).getGiftname());
        viewHolder.tv_num.setText("剩余：" + list.get(position).getNumber());
        viewHolder.tv_time.setText(list.get(position).getAddtime());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GiftInfoActivity.class);
                intent.putExtra("id",list.get(position).getId());
               context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.img_game)
        ImageView img_game;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_type)
        TextView tv_type;
        @BindView(R.id.tv_num)
        TextView tv_num;
        @BindView(R.id.tv_time)
        TextView tv_time;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
