package com.qzsang.retrofittest.net;

import com.qzsang.retrofittest.net.converter.StringConverterFactory;
import com.qzsang.retrofittest.util.ConfigUtil;
import com.qzsang.retrofittest.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class RetrofitClient {
    public static final String OFFICE_BASE_URL = "http://apis.baidu.com/";//正式服务地址
    public static final String DEBUG_BASE_URL = "http://apis.baidu.com/";//测试服务器地址
    public static final int TIMEOUT_READ = 30;//超时时间设置
    public static final int TIMEOUT_CONNECTION = 30;
    public static boolean isDebug = true;//net层的  flag  用于环境切换
    public static RetrofitClient instance = null;
    private Retrofit retrofit;

    public RetrofitClient() {
        instance = this;
    }

    public static RetrofitClient getInstance() {
        if (instance == null)
            new RetrofitClient();
        return instance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null || isDebug != ConfigUtil.isDebug) {
            isDebug = ConfigUtil.isDebug;//与app 环境同步
            creatRetrofit();
        }
        return retrofit;
    }

    private void creatRetrofit() {
        String baseUrl = isDebug ? DEBUG_BASE_URL : OFFICE_BASE_URL;

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)      //失败重连
                .readTimeout(RetrofitClient.TIMEOUT_READ, TimeUnit.SECONDS) //time out
                .connectTimeout(RetrofitClient.TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        if (isDebug) {
            /**
             * 在GsonConverterFactory解析器的基础上  增加了一个功能，就是当返回结果是String类型时，不解析，直接返回string数据
             */
            builder.addConverterFactory(StringConverterFactory.create());//设置string解析器（内置Gson解析器） ,因为这个解析器是用于调试，所以只在debug模式设置
        } else {
            builder.addConverterFactory(GsonConverterFactory.create());//设置Gson解析器
        }

        retrofit = builder
                .baseUrl(baseUrl)
                .client(client)//设置客户端
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static <T> T create(Class<?> clazz) {
        return (T) getInstance().getRetrofit().create(clazz);
    }


    //指定线程
    public static <T>T initObservable(Observable<?> observable) {
       // if (observable == null)
            return null;

//        return observable
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
    }

}
