package com.example.ycg.myapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ycg.myapp.adapter.FeatureRightListAdapter;
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
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeatureRightActivity extends AppCompatActivity {

    @BindView(R.id.right_toolBar)
    Toolbar toolbar;
    @BindView(R.id.right_toolBar_textView)
    TextView right_toolBar_textView;
    @BindView(R.id.img_feature_list_item)
    ImageView img_feature_list_item;
    @BindView(R.id.right_tv_content)
    TextView right_tv_content;
    @BindView(R.id.right_listView)
    ListView right_listView;
    @BindView(R.id.right_img)
    CircleImageView right_img;
    private NewGameInfo newGameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_right);
        ButterKnife.bind(this);
        int id = getIntent().getIntExtra("id", 0);
        initViews();
        GiftListService service = HttpUtils.getGiftListService();
        service.getNewGameInfo(id).enqueue(new Callback<NewGameInfo>() {
            @Override
            public void onResponse(Call<NewGameInfo> call, Response<NewGameInfo> response) {
                newGameInfo = response.body();
                initData();
            }

            @Override
            public void onFailure(Call<NewGameInfo> call, Throwable t) {

            }
        });
    }

    private void initData() {
        right_toolBar_textView.setText(getIntent().getStringExtra("name"));
        Picasso.with(this).load(getIntent().getStringExtra("img_url")).into(img_feature_list_item);
        Picasso.with(this).load(getIntent().getStringExtra("img")).centerCrop().resize(80,80).into(right_img);
        right_tv_content.setText(getIntent().getStringExtra("content"));
        List<NewGameInfo.ListBean> list = newGameInfo.getList();
        right_listView.setAdapter(new FeatureRightListAdapter(this, list));
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
