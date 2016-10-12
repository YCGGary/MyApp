package com.example.ycg.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.ycg.myapp.adapter.OpenListAdapter;
import com.example.ycg.myapp.bean.OpenLeft;
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
public class OpenLeftFragment extends Fragment {
    //
    ListView listView;
    OpenLeft openLeft;

    public OpenLeftFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_left, container, false);
        listView = (ListView) view.findViewById(R.id.open_listView);
        GiftListService service = HttpUtils.getGiftListService();
        service.getJtkaifu().enqueue(new Callback<OpenLeft>() {
            @Override
            public void onResponse(Call<OpenLeft> call, Response<OpenLeft> response) {
                openLeft = response.body();
                initData();
            }
            @Override
            public void onFailure(Call<OpenLeft> call, Throwable t) {
            }
        });
        return view;
    }
    private void initData() {
        List<OpenLeft.InfoBean> infoBean = openLeft.getInfo();
        listView.setAdapter(new OpenListAdapter(getContext(), infoBean));
    }
}
