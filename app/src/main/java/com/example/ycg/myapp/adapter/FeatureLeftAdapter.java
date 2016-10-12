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

import com.example.ycg.myapp.activity.FeatureLeftActivity;
import com.example.ycg.myapp.bean.FeatureWeek;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;


public class FeatureLeftAdapter extends BaseAdapter {
    Context context;
    FeatureWeek featureWeek;

    public FeatureLeftAdapter(Context context, FeatureWeek featureWeek) {
        this.context = context;
        this.featureWeek = featureWeek;
    }

    @Override
    public int getCount() {
        return featureWeek.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return featureWeek.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.feature_list_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final String img_url = HttpUtils.BASE_URL + featureWeek.getList().get(position).getIconurl();
        viewHolder.tv_dsc.setText(featureWeek.getList().get(position).getName());
        viewHolder.tv_time.setText(featureWeek.getList().get(position).getAddtime());
        Picasso.with(context).load(img_url).into(viewHolder.imageView);
        final int sid = featureWeek.getList().get(position).getSid();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, FeatureLeftActivity.class);
                intent.putExtra("img_url", img_url);
                intent.putExtra("sid",sid);
                intent.putExtra("name", featureWeek.getList().get(position).getName());
                intent.putExtra("time", featureWeek.getList().get(position).getAddtime());
                intent.putExtra("content", featureWeek.getList().get(position).getDescs());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView tv_dsc;
        TextView tv_time;
        View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            tv_dsc = (TextView) itemView.findViewById(R.id.tv_list_item_dcs);
            tv_time = (TextView) itemView.findViewById(R.id.tv_list_item_time);
            imageView = (ImageView) itemView.findViewById(R.id.img_feature_list_item);
            itemView.setTag(this);
        }
    }
}
