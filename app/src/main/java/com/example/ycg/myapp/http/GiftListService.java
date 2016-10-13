package com.example.ycg.myapp.http;


import com.example.ycg.myapp.bean.AppInfo;
import com.example.ycg.myapp.bean.FeatureWeek;
import com.example.ycg.myapp.bean.FeatureWeekInfo;
import com.example.ycg.myapp.bean.GiftBean;
import com.example.ycg.myapp.bean.GiftInfo;
import com.example.ycg.myapp.bean.Hot;
import com.example.ycg.myapp.bean.NewGame;
import com.example.ycg.myapp.bean.NewGameInfo;
import com.example.ycg.myapp.bean.OpenLeft;
import com.example.ycg.myapp.bean.OpenRight;
import com.example.ycg.myapp.bean.Search;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/26.
 */
public interface GiftListService {
    @GET("/majax.action?method=getGiftList")
    Call<GiftBean> getGiftList(@Query("pageno") int pageno);

    @GET("/majax.action?method=getGiftInfo")
    Call<GiftInfo> getGiftInfo(@Query("id") int id);

    @GET("/majax.action?method=getJtkaifu")
    Call<OpenLeft> getJtkaifu();

    @GET("/majax.action?method=getWebfutureTest")
    Call<OpenRight> getWebfutureTest();

    @GET("/majax.action?method=getAppInfo")
    Call<AppInfo> getAppInfo(@Query("id") int id);

    @GET("/majax.action?method=getWeekll")
    Call<NewGame> getNewGame(@Query("pageNo") int pageNo);

    @GET("/majax.action?method=bdxqs")
    Call<FeatureWeek> getFeatureWeek(@Query("pageNo") int pageNo);

    @GET("/majax.action?method=getWeekllChid")
    Call<NewGameInfo> getNewGameInfo(@Query("id") int id);

    @GET("/majax.action?method=bdxqschild")
    Call<FeatureWeekInfo> getFeatureWeekInfo(@Query("id") int id);

    @FormUrlEncoded
    @POST("/majax.action?method=searchGift")
    Call<Search> postSearch(@Field("key") String key);

    @GET("/majax.action?method=hotpushForAndroid")
    Call<Hot> getHotInfo();
}
 