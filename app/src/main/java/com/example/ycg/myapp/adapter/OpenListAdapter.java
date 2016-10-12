package com.example.ycg.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ycg.myapp.activity.OpenInfoActivity;
import com.example.ycg.myapp.bean.OpenLeft;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenListAdapter extends BaseAdapter {
    Context context;
    List<OpenLeft.InfoBean> infoBean;
    String id;

    public OpenListAdapter(Context context, List<OpenLeft.InfoBean> infoBean) {
        this.context = context;
        this.infoBean = infoBean;
    }

    @Override
    public int getCount() {
        return infoBean.size();
    }

    @Override
    public Object getItem(int position) {
        return infoBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.open_left_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(HttpUtils.BASE_URL + infoBean.get(position).getIconurl())
                .placeholder(R.mipmap.def_loading).centerCrop().resize(100, 100).into(viewHolder.img_game);
        viewHolder.tv_name.setText(infoBean.get(position).getGname());
        viewHolder.tv_type.setText(infoBean.get(position).getAddtime());
        viewHolder.tv_time.setText(infoBean.get(position).getArea());
        viewHolder.tv_num.setText("运营商：" + infoBean.get(position).getOperators());
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = infoBean.get(position).getGid();
                Intent intent = new Intent(context, OpenInfoActivity.class);
                intent.putExtra("id", id);
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
        @BindView(R.id.tv_time)
        TextView tv_type;
        @BindView(R.id.tv_operators)
        TextView tv_num;
        @BindView(R.id.tv_area)
        TextView tv_time;
        @BindView(R.id.button)
        Button button;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
