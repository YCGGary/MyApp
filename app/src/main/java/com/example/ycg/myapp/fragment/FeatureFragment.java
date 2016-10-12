package com.example.ycg.myapp.fragment;


import android.os.Bundle;
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
public class FeatureFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager vp;

    public FeatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feature, container, false);
        vp = (ViewPager) view.findViewById(R.id.vp_feature);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_feature);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        List<String> titles = new ArrayList<>();
        titles.add("暴打星期三");
        titles.add("新游周刊");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FeatureLeftFragment());
        fragments.add(new FeatureRightFragment());
        vp.setAdapter(new ViewPagerAdapter(getFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(vp);
        return view;
    }
}
