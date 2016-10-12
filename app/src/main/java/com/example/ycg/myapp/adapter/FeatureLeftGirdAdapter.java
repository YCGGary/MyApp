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
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeatureLeftGirdAdapter extends BaseAdapter {
    Context context;
    List<FeatureWeekInfo.ListBean> list = new ArrayList<>();

    public FeatureLeftGirdAdapter(Context context, List<FeatureWeekInfo.ListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.left_gird_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String url = list.get(position).getAppicon();
        Picasso.with(context).load(HttpUtils.BASE_URL + url).placeholder(R.mipmap.def_loading).centerCrop().resize(100, 100).into(viewHolder.left_gird_img);
        viewHolder.left_gird_tv_name.setText(list.get(position).getAppname());

        viewHolder.left_gird_btn.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.left_gird_img)
        ImageView left_gird_img;
        @BindView(R.id.left_gird_tv_name)
        TextView left_gird_tv_name;
        @BindView(R.id.left_gird_btn)
        Button left_gird_btn;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
        }
    }
}
