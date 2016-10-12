package com.example.ycg.myapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ycg.myapp.bean.GiftInfo;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftInfoActivity extends AppCompatActivity {
    @BindView(R.id.image_giftdetails)
    ImageView imageView;
    @BindView(R.id.text_gift_details_date)
    TextView text_date;
    @BindView(R.id.text_gift_details_number)
    TextView text_number;
    @BindView(R.id.toolbar_gift)
    Toolbar toolbar;
    @BindView(R.id.gift_toolBar_text_title)
    TextView text_toolBar_title;
    @BindView(R.id.text_gift_details_information)
    TextView text_information;
    @BindView(R.id.text_gift_details_style)
    TextView text_style;
    @BindView(R.id.btn_buy)
    Button btn_buy;
    @BindView(R.id.image_btn)
    ImageButton image_button;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_info);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.bar_back);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("");
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        num = intent.getIntExtra("num", 0);
        initData(Integer.valueOf(id));
    }
    private void initData(final int id) {
        GiftListService service = HttpUtils.getGiftListService();
        service.getGiftInfo(id).enqueue(new Callback<GiftInfo>() {
            @Override
            public void onResponse(Call<GiftInfo> call, Response<GiftInfo> response) {
                GiftInfo info = response.body();
                if (info == null) {
                    Toast.makeText(GiftInfoActivity.this, "网路故障", Toast.LENGTH_SHORT).show();
                    return;
                }
                Picasso.with(GiftInfoActivity.this).load(HttpUtils.BASE_URL + info.getInfo().getIconurl()).into(imageView);
                text_date.setText("有效:" + info.getInfo().getOvertime());
                text_number.setText("" + num);
                text_toolBar_title.setText(info.getInfo().getGiftname());
                text_information.setText(info.getInfo().getExplains());
                text_style.setText(info.getInfo().getTypename());
                if (num == 0) {
                    btn_buy.setText("--淘号--");
                    btn_buy.setBackgroundColor(Color.GRAY);
                    btn_buy.setClickable(false);
                }
            }
            @Override
            public void onFailure(Call<GiftInfo> call, Throwable t) {

            }
        });
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

    public void jump(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
