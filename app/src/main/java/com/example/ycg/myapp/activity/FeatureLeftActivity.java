package com.example.ycg.myapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
}
