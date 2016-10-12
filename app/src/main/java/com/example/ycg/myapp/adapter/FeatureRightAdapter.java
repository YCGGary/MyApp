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


import com.example.ycg.myapp.activity.FeatureRightActivity;
import com.example.ycg.myapp.bean.NewGame;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;


public class FeatureRightAdapter extends BaseAdapter {
    Context context;
    NewGame newGame;

    public FeatureRightAdapter(Context context, NewGame newGame) {
        this.context = context;
        this.newGame = newGame;
    }

    @Override
    public int getCount() {
        return newGame.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return newGame.getList().get(position);
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
        final String img_url = HttpUtils.BASE_URL + newGame.getList().get(position).getIconurl();
        final String img = HttpUtils.BASE_URL + newGame.getList().get(position).getAuthorimg();
        viewHolder.tv_dsc.setText(newGame.getList().get(position).getName());
        viewHolder.tv_time.setText(newGame.getList().get(position).getAddtime());
        Picasso.with(context).load(img_url).into(viewHolder.imageView);
        final int id = newGame.getList().get(position).getId();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, FeatureRightActivity.class);
                intent.putExtra("img",img);
                intent.putExtra("img_url", img_url);
                intent.putExtra("id",id);
                intent.putExtra("des", newGame.getList().get(position).getName());
                intent.putExtra("name", newGame.getList().get(position).getName());
                intent.putExtra("content", newGame.getList().get(position).getDescs());
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
