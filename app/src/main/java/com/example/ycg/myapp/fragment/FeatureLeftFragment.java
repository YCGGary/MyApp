package com.example.ycg.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.ycg.myapp.adapter.FeatureLeftAdapter;
import com.example.ycg.myapp.bean.FeatureWeek;
import com.example.ycg.myapp.bean.NewGame;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.xray.daydaybasketball.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureLeftFragment extends Fragment {
    ListView listView;
    FeatureWeek featureWeek;

    public FeatureLeftFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feature_left, container, false);
        listView = (ListView) view.findViewById(R.id.lv_newgame);
        GiftListService service = HttpUtils.getGiftListService();
        service.getFeatureWeek(0).enqueue(new Callback<FeatureWeek>() {
            @Override
            public void onResponse(Call<FeatureWeek> call, Response<FeatureWeek> response) {
                featureWeek = response.body();
                initData();
            }
            @Override
            public void onFailure(Call<FeatureWeek> call, Throwable t) {

            }
        });
        return view;
    }
    private void initData() {
        listView.setAdapter(new FeatureLeftAdapter(getContext(), featureWeek));
    }
}
