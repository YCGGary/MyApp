package com.example.ycg.myapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ycg.myapp.adapter.OpenAdapter;
import com.example.ycg.myapp.bean.AppInfo;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_open_details)
    Toolbar toolbar;
    @BindView(R.id.text_open_details_name)
    TextView text_name;
    @BindView(R.id.text_open_details_count)
    TextView text_count;
    @BindView(R.id.text_open_details_information)
    TextView text_information;
    @BindView(R.id.text_open_details_style)
    TextView text_style;
    @BindView(R.id.image_open_details_one)
    ImageView image;
    @BindView(R.id.open_details_viewPager)
    ViewPager viewpager;
    @BindView(R.id.open_toolBar_text_title)
    TextView text_tool;
    @BindView(R.id.btn_open_details_download)
    Button btn_down;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_info);
        Intent intent = getIntent();
        int id = Integer.valueOf(intent.getStringExtra("id"));
        ButterKnife.bind(this);
        initView();
        getExpDetailsData(id);
    }

    private void getExpDetailsData(int id) {
        GiftListService service = HttpUtils.getGiftListService();
        service.getAppInfo(id).enqueue(new Callback<AppInfo>() {
            @Override
            public void onResponse(Call<AppInfo> call, Response<AppInfo> response) {
                final AppInfo appInfo = response.body();
                if (appInfo == null) {
                    Toast.makeText(OpenInfoActivity.this, "网路故障", Toast.LENGTH_SHORT).show();
                    return;
                }
                text_tool.setText(appInfo.getApp().getName());
                text_count.setText("未知");
                text_information.setText(appInfo.getApp().getDescription());
                text_name.setText(appInfo.getApp().getName());
                text_style.setText(appInfo.getApp().getTypename());
                String adress = appInfo.getApp().getDownload_addr();
                if (adress.equals("")){
                    btn_down.setText("暂无下载");
                    btn_down.setBackgroundColor(Color.GRAY);
                    btn_down.setClickable(false);
                }
                Picasso.with(OpenInfoActivity.this).load(HttpUtils.BASE_URL + appInfo.getApp().getLogo()).into(image);
                List<AppInfo.ImgBean> img = appInfo.getImg();
                List<View> list_view = new ArrayList<>();
                for (int i = 0; i < img.size(); i++) {
                    ImageView imageView = new ImageView(OpenInfoActivity.this);
                    Picasso.with(OpenInfoActivity.this).load(HttpUtils.BASE_URL + img.get(i).getAddress()).into(imageView);
                    list_view.add(imageView);

                }
                OpenAdapter adapter = new OpenAdapter();
                adapter.setList(list_view);
                viewpager.setAdapter(adapter);
                btn_down.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(appInfo.getApp().getDownload_addr()));
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<AppInfo> call, Throwable t) {

            }
        });
    }

    private void initView() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.bar_back);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("");
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

    public void share(View view) {
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
