package com.example.ycg.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;


import com.example.ycg.myapp.adapter.MyListAdapter;
import com.example.ycg.myapp.adapter.OpenListAdapter;
import com.example.ycg.myapp.bean.OpenLeft;
import com.example.ycg.myapp.bean.OpenLeftGroup;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.xray.daydaybasketball.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenLeftFragment extends Fragment {

    ExpandableListView listView;
    OpenLeft openLeft;
    List<OpenLeftGroup> list_group;

    public OpenLeftFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_left, container, false);
        listView = (ExpandableListView) view.findViewById(R.id.open_listView);
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
        final List<OpenLeft.InfoBean> infoBean = openLeft.getInfo();
        final int size = infoBean.size();
        list_group = new ArrayList<>();
        String str = "haha";
        int index = -1;
        for (int i = 0; i < size; i++) {
            OpenLeft.InfoBean info = infoBean.get(i);
            if (!str.equals(info.getAddtime())) {
                index++;
                str = info.getAddtime();
                OpenLeftGroup group = new OpenLeftGroup();
                group.setGroupName(info.getAddtime());
                List<OpenLeft.InfoBean> list_infobean = new ArrayList<>();
                group.setList(list_infobean);
                list_group.add(group);
            }
            list_group.get(index).getList().add(info);
        }
        OpenListAdapter adapter = new OpenListAdapter(getContext());
        adapter.setList(list_group);
        listView.setAdapter(adapter);
        int groupCount = listView.getCount();
        for (int i = 0; i < groupCount; i++) {
            listView.expandGroup(i);
        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        listView.setGroupIndicator(null);

    }
}
