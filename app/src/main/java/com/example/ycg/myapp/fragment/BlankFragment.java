package com.example.ycg.myapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ycg.myapp.activity.GiftInfoActivity;
import com.example.ycg.myapp.adapter.AdsAdapter;
import com.example.ycg.myapp.adapter.MyListAdapter;
import com.example.ycg.myapp.bean.GiftBean;
import com.example.ycg.myapp.http.GiftListService;
import com.example.ycg.myapp.http.HttpUtils;
import com.example.ycg.myapp.view.MyViewPager;
import com.squareup.picasso.Picasso;
import com.xray.daydaybasketball.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    ListView listView;
    @BindView(R.id.vp)
    MyViewPager viewPager;
    @BindView(R.id.linear_layout)
    LinearLayout layout;
    GiftBean bean;
    List<View> viewList;
    List<GiftBean.AdBean> adBean = new ArrayList<>();
    List<GiftBean.ListBean> listBean = new ArrayList<>();
    private int curIndex = 0;
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private MyListAdapter myListAdapter;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        listView = (ListView) view.findViewById(R.id.gift_listView);
        ButterKnife.bind(this, view);
        final GiftListService service = HttpUtils.getGiftListService();
        service.getGiftList(1).enqueue(new Callback<GiftBean>() {
            @Override
            public void onResponse(Call<GiftBean> call, Response<GiftBean> response) {
                bean = response.body();
                initListBean(bean);
                initAds();
            }

            @Override
            public void onFailure(Call<GiftBean> call, Throwable t) {
            }
        });
        return view;
    }

    private void initAds() {
        adBean = bean.getAd();
        viewList = new ArrayList<>();
        for (int i = 0; i < adBean.size(); i++) {
            ImageView img = new ImageView(getActivity());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getContext()).load(HttpUtils.BASE_URL + adBean.get(i).getIconurl()).into(img);
            viewList.add(img);
            ImageView img_point = new ImageView(getActivity());
            img_point.setPadding(10, 0, 10, 0);
            if (i == 0) {
                img_point.setImageResource(R.drawable.point_selected);
            } else {
                img_point.setImageResource(R.drawable.point_default);
            }
            layout.addView(img_point);
        }
        viewPager.setAdapter(new AdsAdapter(viewList));
        startAutoScroll();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                int count = layout.getChildCount();
                for (int i = 0; i < count; i++) {
                    ImageView img_point = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        img_point.setImageResource(R.drawable.point_selected);
                    } else {
                        img_point.setImageResource(R.drawable.point_default);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void startAutoScroll() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //参数1：要执行的任务  参数2：第一个任务执行的延迟时间，参数3：两个之间的延迟时间，参数4：时间单位
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 0, 3, TimeUnit.SECONDS);
    }

    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int size = adBean.size();
                    curIndex++;
                    viewPager.setCurrentItem((curIndex + 1) % size);
                }
            });
        }
    }

    private void initListBean(GiftBean bean) {
        listBean = bean.getList();
        myListAdapter = new MyListAdapter(getContext(), listBean);
        listView.setAdapter(myListAdapter);
    }
}
