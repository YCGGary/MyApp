package com.example.ycg.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.ycg.myapp.adapter.OpenRightAdapter;
import com.example.ycg.myapp.bean.OpenRight;
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
public class OpenRightFragment extends Fragment {
    ListView listView;
    OpenRight openRight;

    public OpenRightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_right, container, false);
        listView = (ListView) view.findViewById(R.id.open_right_listView);
        GiftListService service = HttpUtils.getGiftListService();
        service.getWebfutureTest().enqueue(new Callback<OpenRight>() {
            @Override
            public void onResponse(Call<OpenRight> call, Response<OpenRight> response) {
                openRight = response.body();
                initData();
            }

            @Override
            public void onFailure(Call<OpenRight> call, Throwable t) {

            }
        });

        return view;
    }

    private void initData() {
        List<OpenRight.InfoBean> infoBean = openRight.getInfo();
        listView.setAdapter(new OpenRightAdapter(getContext(), infoBean));
    }

}
