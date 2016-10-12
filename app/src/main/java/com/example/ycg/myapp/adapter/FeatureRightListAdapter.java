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
import com.example.ycg.myapp.bean.FeatureWeekInfo;
import com.example.ycg.myapp.bean.NewGameInfo;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/8.
 */
public class FeatureRightListAdapter extends BaseAdapter {
    Context context;
    List<NewGameInfo.ListBean> list = new ArrayList<>();

    public FeatureRightListAdapter(Context context, List<NewGameInfo.ListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.right_list_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String iconurl = list.get(position).getIconurl();
        Picasso.with(context).load(HttpUtils.BASE_URL + iconurl).placeholder(R.mipmap.def_loading).centerCrop().resize(100, 100).into(viewHolder.right_list_img);
        viewHolder.right_tv_name.setText(list.get(position).getAppname());
        viewHolder.right_tv_type.setText(list.get(position).getTypename());
        viewHolder.right_tv_size.setText(list.get(position).getAppsize());
        viewHolder.right_tv_des.setText(list.get(position).getDescs());
        viewHolder.right_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OpenInfoActivity.class);
                intent.putExtra("id", list.get(position).getAppid());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.right_list_img)
        ImageView right_list_img;
        @BindView(R.id.right_tv_name)
        TextView right_tv_name;
        @BindView(R.id.right_tv_type)
        TextView right_tv_type;
        @BindView(R.id.right_tv_size)
        TextView right_tv_size;
        @BindView(R.id.right_tv_des)
        TextView right_tv_des;
        @BindView(R.id.right_list_btn)
        Button right_list_btn;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
