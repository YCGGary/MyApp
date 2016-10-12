package com.example.ycg.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;


import com.example.ycg.myapp.adapter.HotGridAdapter;
import com.example.ycg.myapp.adapter.HotListAdapter;
import com.example.ycg.myapp.bean.Hot;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.xray.daydaybasketball.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {
    ListView listView;
    GridView gridView;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        listView = (ListView) view.findViewById(R.id.listVIew_hot);
        gridView = (GridView) view.findViewById(R.id.grid_hot);
        GiftListService service = HttpUtils.getGiftListService();
        service.getHotInfo().enqueue(new Callback<Hot>() {
            @Override
            public void onResponse(Call<Hot> call, Response<Hot> response) {

                List<Hot.InfoBean.Push1Bean> push1Been = response.body().getInfo().getPush1();
                List<Hot.InfoBean.Push2Bean> push2Been = response.body().getInfo().getPush2();
                HotListAdapter hotListAdapter = new HotListAdapter(getContext(),push1Been);
                listView.setAdapter(hotListAdapter);
                HotGridAdapter hotGridAdapter = new HotGridAdapter(push2Been,getContext());
                gridView.setAdapter(hotGridAdapter);
            }
            @Override
            public void onFailure(Call<Hot> call, Throwable t) {

            }
        });

        return view;
    }

}
