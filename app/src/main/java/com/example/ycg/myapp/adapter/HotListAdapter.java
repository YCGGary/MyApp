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

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotListAdapter extends BaseAdapter{
    Context context;
    List<Hot.InfoBean.Push1Bean> push1Been = new ArrayList<>();

    public HotListAdapter(Context context, List<Hot.InfoBean.Push1Bean> push1Been) {
        this.context = context;
        this.push1Been = push1Been;
    }

    @Override
    public int getCount() {
        return push1Been.size();
    }

    @Override
    public Object getItem(int position) {
        return push1Been.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderChild child;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.hot_list_item,null,false);
            child=new ViewHolderChild(convertView);
        }else{
            child= (ViewHolderChild) convertView.getTag();
        }
        Picasso.with(context).load(HttpUtils.BASE_URL+push1Been.get(position).getLogo()).into(child.image);
        child.text_count.setText(push1Been.get(position).getSize());
        child.text_number.setText(""+push1Been.get(position).getClicks());
        child.text_name.setText(push1Been.get(position).getName());
        child.text_style.setText(push1Been.get(position).getTypename());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OpenInfoActivity.class);
                intent.putExtra("id",push1Been.get(position).getAppid());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolderChild{
        @BindView(R.id.text_open_details_name)
        TextView text_name;
        @BindView(R.id.text_open_details_count)
        TextView text_count;
        @BindView(R.id.text_open_details_style)
        TextView text_style;
        @BindView(R.id.image_open_details_one)
        ImageView image;
        @BindView(R.id.text_count)
        TextView text_number;
        public ViewHolderChild(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
