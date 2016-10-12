package com.example.ycg.myapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ycg.myapp.activity.SearchActivity;
import com.example.ycg.myapp.fragment.BlankFragment;
import com.example.ycg.myapp.fragment.FeatureFragment;
import com.example.ycg.myapp.fragment.HotFragment;
import com.example.ycg.myapp.fragment.OpenFragment;
import com.xray.daydaybasketball.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity {
    SlidingPaneLayout slidingPaneLayout;
    LinearLayout layout;
    @BindView(R.id.home_tab_home_rb)
    RadioButton mGiftRb;
    @BindView(R.id.home_tab_open_rb)
    RadioButton mOpenRb;
    @BindView(R.id.home_tab_hot_rb)
    RadioButton mHotRb;
    @BindView(R.id.home_tab_special_rb)
    RadioButton mSpecialRb;
    @BindString(R.string.gift)
    String mGiftStr;
    @BindString(R.string.open)
    String mOpenStr;
    @BindString(R.string.hot)
    String mHotStr;
    @BindString(R.string.feature)
    String mSpecialStr;
    @BindView(R.id.toolbar_gift)
    Toolbar toolbar;
    @BindView(R.id.gift_toolBar_text_title)
    TextView textView_toolbar_title;
    @BindView(R.id.btn_search)
    Button btn_search;
    Fragment mFragmentGift;
    Fragment mFragmentOpen;
    Fragment mFragmentHot;
    Fragment mFragmentSpecial;
    //当前显示的Fragment
    Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShareSDK.initSDK(this);
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sp);
        layout = (LinearLayout) findViewById(R.id.content);
        ButterKnife.bind(this);
        initFragments();
        addFirstFragment();
        initToolBar();
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                //左面板滑动时，内容面板所剩的空间大小
                layout.setScaleY(1 - slideOffset * 0.3f);
            }

            @Override
            public void onPanelOpened(View panel) {
            }

            @Override
            public void onPanelClosed(View panel) {
            }
        });
        //左面板滑动时，内容面板所有控件被覆盖的颜色
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    slidingPaneLayout.openPane();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.title_bar_menu);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("");
        textView_toolbar_title.setText("礼包精灵");
    }

    private void initFragments() {
        mFragmentGift = BlankFragment.newInstance(mGiftStr);
        if (mFragmentHot == null) {
            mFragmentHot = new HotFragment();
        }
        if (mFragmentOpen == null) {
            mFragmentOpen = new OpenFragment();
        }
        if (mFragmentSpecial == null) {
            mFragmentSpecial = new FeatureFragment();
        }
    }

    private void addFirstFragment() {
        FragmentTransaction trans = this.getSupportFragmentManager()
                .beginTransaction();
        trans.add(R.id.home_content_layout, mFragmentGift).commit();
        mCurrentFragment = mFragmentGift;
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction trans = this.getSupportFragmentManager()
                .beginTransaction();
        if (fragment.isAdded()) {
            trans.hide(mCurrentFragment).show(fragment).commit();
        } else {
            trans.hide(mCurrentFragment).add(R.id.home_content_layout, fragment).commit();
        }
        mCurrentFragment = fragment;
    }

    @OnClick(R.id.home_tab_home_rb)
    void clickGift() {
        textView_toolbar_title.setText("礼包精灵");
        switchFragment(mFragmentGift);
        btn_search.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.home_tab_open_rb)
    void clickOpen() {
        textView_toolbar_title.setText("开服");
        switchFragment(mFragmentOpen);
        btn_search.setVisibility(View.GONE);
    }

    @OnClick(R.id.home_tab_hot_rb)
    void clickHot() {
        textView_toolbar_title.setText("热门游戏");
        switchFragment(mFragmentHot);
        btn_search.setVisibility(View.GONE);
    }

    @OnClick(R.id.home_tab_special_rb)
    void clickSpecial() {
        textView_toolbar_title.setText("独家策划");
        switchFragment(mFragmentSpecial);
        btn_search.setVisibility(View.GONE);
    }

    public void change(View view) {
        startActivity(new Intent(this, SearchActivity.class));
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
