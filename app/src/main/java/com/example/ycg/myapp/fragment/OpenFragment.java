package com.example.ycg.myapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ycg.myapp.adapter.ViewPagerAdapter;
import com.xray.daydaybasketball.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpenFragment extends Fragment {
    View view;
    ViewPager vp;

    private TabLayout mTab;

    public OpenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_open, container, false);
        mTab = (TabLayout) view.findViewById(R.id.open_tab);
        vp = (ViewPager) view.findViewById(R.id.vp_open);

        mTab.setTabMode(TabLayout.MODE_FIXED);
        List<String> titles = new ArrayList<>();
        titles.add("开服");
        titles.add("开测");
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new OpenLeftFragment());
        fragments.add(new OpenRightFragment());

        vp.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments, titles));
        mTab.setupWithViewPager(vp);
        return view;
    }
}
