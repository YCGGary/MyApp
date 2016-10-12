package com.example.ycg.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ycg.myapp.activity.OpenInfoActivity;
import com.example.ycg.myapp.bean.OpenLeft;
import com.example.ycg.myapp.bean.OpenLeftGroup;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenListAdapter implements ExpandableListAdapter {
//    Context context;
//    List<OpenLeftGroup> list = new ArrayList<>();
//    String id;
//
//    public OpenListAdapter(Context context,List<OpenLeftGroup> list ) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.open_left_item, null);
//            viewHolder = new ViewHolder(convertView);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        Picasso.with(context).load(HttpUtils.BASE_URL + list.get(position).getIconurl())
//                .placeholder(R.mipmap.def_loading).centerCrop().resize(100, 100).into(viewHolder.img_game);
//        viewHolder.tv_name.setText(list.get(position).getGname());
//        viewHolder.tv_type.setText(list.get(position).getAddtime());
//        viewHolder.tv_time.setText(list.get(position).getArea());
//        viewHolder.tv_num.setText("运营商：" + list.get(position).getOperators());
//        viewHolder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                id = list.get(position).getGid();
//                Intent intent = new Intent(context, OpenInfoActivity.class);
//                intent.putExtra("id", id);
//                context.startActivity(intent);
//            }
//        });
//        return convertView;
//    }

    List<OpenLeftGroup> list=new ArrayList<>();
    Context context;

    public OpenListAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<OpenLeftGroup> list) {
        this.list = list;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public OpenLeftGroup getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.group_item,null,false);
            viewHolderGroup=new ViewHolderGroup(convertView);
        }else{
            viewHolderGroup= (ViewHolderGroup) convertView.getTag();
        }
        viewHolderGroup.text_group.setText(list.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild child;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.open_left_item,null,false);
            child=new ViewHolderChild(convertView);
        }else{
            child= (ViewHolderChild) convertView.getTag();
        }
        Picasso.with(context).load(HttpUtils.BASE_URL+list.get(groupPosition).getList().get(childPosition).getIconurl()).into(child.image_one);
        child.text_company.setText(list.get(groupPosition).getList().get(childPosition).getOperators());
        child.text_date.setText(list.get(groupPosition).getList().get(childPosition).getLinkurl());
        child.text_number.setText(list.get(groupPosition).getList().get(childPosition).getArea());
        child.text_name.setText(list.get(groupPosition).getList().get(childPosition).getGname());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OpenInfoActivity.class);
                intent.putExtra("id",list.get(groupPosition).getList().get(childPosition).getGid());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
    class ViewHolderChild{
        @BindView(R.id.image_child_item)
        ImageView image_one;
        @BindView(R.id.text_child_item_company)
        TextView text_company;
        @BindView(R.id.text_child_item_date)
        TextView text_date;
        @BindView(R.id.text_child_item_name)
        TextView text_name;
        @BindView(R.id.text_child_item_number)
        TextView text_number;
        public ViewHolderChild(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);

        }
    }
    class ViewHolderGroup{
        TextView text_group;
        public ViewHolderGroup(View view){
            text_group= (TextView) view.findViewById(R.id.text_group_item);
            view.setTag(this);
        }
    }


//    class ViewHolder {
//        @BindView(R.id.img_game)
//        ImageView img_game;
//        @BindView(R.id.tv_name)
//        TextView tv_name;
//        @BindView(R.id.tv_time)
//        TextView tv_type;
//        @BindView(R.id.tv_operators)
//        TextView tv_num;
//        @BindView(R.id.tv_area)
//        TextView tv_time;
//        @BindView(R.id.button)
//        Button button;
//
//        public ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//            view.setTag(this);
//        }
//    }
}
