package com.example.ycg.myapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
        Picasso.with(this).load(getIntent().getStringExtra("img")).into(right_img);
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
}
