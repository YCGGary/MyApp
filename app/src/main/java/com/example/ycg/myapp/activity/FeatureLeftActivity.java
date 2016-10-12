package com.example.ycg.myapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ycg.myapp.adapter.FeatureLeftGirdAdapter;
import com.example.ycg.myapp.bean.FeatureWeekInfo;
import com.example.ycg.myapp.bean.NewGameInfo;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeatureLeftActivity extends AppCompatActivity {

    @BindView(R.id.left_toolBar)
    Toolbar toolbar;
    @BindView(R.id.left_toolBar_textView)
    TextView left_toolBar_textView;
    @BindView(R.id.img_feature_list_item)
    ImageView img_feature_list_item;
    @BindView(R.id.tv_list_item_dcs)
    TextView tv_list_item_dcs;
    @BindView(R.id.tv_list_item_time)
    TextView tv_list_item_time;
    @BindView(R.id.left_tv_content)
    TextView left_tv_content;
    @BindView(R.id.left_gridView)
    GridView left_gridView;
    private FeatureWeekInfo featureWeekInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_left);
        ButterKnife.bind(this);
        initViews();

        int sid = getIntent().getIntExtra("sid",0);
        GiftListService service = HttpUtils.getGiftListService();
        service.getFeatureWeekInfo(sid).enqueue(new Callback<FeatureWeekInfo>() {
            @Override
            public void onResponse(Call<FeatureWeekInfo> call, Response<FeatureWeekInfo> response) {
                featureWeekInfo = response.body();
                initData();
            }

            @Override
            public void onFailure(Call<FeatureWeekInfo> call, Throwable t) {

            }
        });
    }

    private void initData() {
        left_toolBar_textView.setText(getIntent().getStringExtra("name"));
        Picasso.with(this).load(getIntent().getStringExtra("img_url")).into(img_feature_list_item);
        tv_list_item_dcs.setText(getIntent().getStringExtra("name"));
        tv_list_item_time.setText(getIntent().getStringExtra("time"));
        left_tv_content.setText("导读：" + getIntent().getStringExtra("content"));
        List<FeatureWeekInfo.ListBean> list = featureWeekInfo.getList();
        left_gridView.setAdapter(new FeatureLeftGirdAdapter(this,list));
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.bar_back);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onShare(View view) {
        showShare();
    }
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
}
