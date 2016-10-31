package com.qzsang.retrofittest.net;

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
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class RetrofitClient {
    public static final String OFFICE_BASE_URL = "http://apis.baidu.com/";//正式服务地址
    public static final String DEBUG_BASE_URL = "http://apis.baidu.com/";//测试服务器地址
    public static final int TIMEOUT_READ = 25;//time out set
    public static final int TIMEOUT_CONNECTION = 25;
    public static boolean isDebug = true;//net层的  标记  用于环境切换
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

        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);*/
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)      //失败重连
                .readTimeout(RetrofitClient.TIMEOUT_READ, TimeUnit.SECONDS) //time out
                .connectTimeout(RetrofitClient.TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//设置解析器
                .client(client)//设置客户端
                .build();
    }


    public static <T> T create(Class<?> clazz) {
        return (T) getInstance().getRetrofit().create(clazz);
    }
}
