package com.example.ycg.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.ycg.myapp.adapter.FeatureRightAdapter;
import com.example.ycg.myapp.bean.NewGame;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.example.ycg.myapp.refreshview.MyRefreshListView;
import com.example.ycg.myapp.refreshview.OnRefreshListener;
import com.xray.daydaybasketball.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureRightFragment extends Fragment {
    MyRefreshListView listView;
    NewGame newGame;

    public FeatureRightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feature_right, container, false);
        listView = (MyRefreshListView) view.findViewById(R.id.lv_week);
        GiftListService service = HttpUtils.getGiftListService();
        service.getNewGame(1).enqueue(new Callback<NewGame>() {
            @Override
            public void onResponse(Call<NewGame> call, Response<NewGame> response) {
                newGame = response.body();
                initData();
            }

            @Override
            public void onFailure(Call<NewGame> call, Throwable t) {
            }
        });
        return view;
    }
    private void initData() {
        listView.setAdapter(new FeatureRightAdapter(getContext(), newGame));
        listView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.hideHeadView();
                    }
                },2000);
            }

            @Override
            public void onLoadingMore() {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.hideFootView();
                    }
                },2000);
            }
        });
    }

}
