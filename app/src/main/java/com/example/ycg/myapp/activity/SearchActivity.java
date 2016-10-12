package com.example.ycg.myapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.ycg.myapp.adapter.SearchAdapter;
import com.example.ycg.myapp.bean.Search;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.xray.daydaybasketball.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_search)
    Toolbar toolBar_search;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.search_listView)
    ListView listView;
    @BindView(R.id.btn_search)
    Button btn_search;
    private List<Search.ListBean> list;
    private SearchAdapter searchAdapter;
    GiftListService service;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initViews();
        service = HttpUtils.getGiftListService();
        service.postSearch(key).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                list = response.body().getList();
                initListBean();
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast.makeText(SearchActivity.this, "为空", Toast.LENGTH_SHORT).show();
                }else{
                    service = HttpUtils.getGiftListService();
                    service.postSearch(editText.getText().toString()).enqueue(new Callback<Search>() {
                        @Override
                        public void onResponse(Call<Search> call, Response<Search> response) {
                            list = response.body().getList();
                            initListBean();
                        }

                        @Override
                        public void onFailure(Call<Search> call, Throwable t) {
                        }
                    });
                }

                }
        });
    }

    private void initListBean() {
        searchAdapter = new SearchAdapter(this, list);
        listView.setAdapter(searchAdapter);
    }

    private void initViews() {
        setSupportActionBar(toolBar_search);
        toolBar_search.setNavigationIcon(R.mipmap.bar_back);
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
