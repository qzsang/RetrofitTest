package com.qzsang.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qzsang.retrofittest.net.RetrofitClient;
import com.qzsang.retrofittest.net.bean.BooksBean;
import com.qzsang.retrofittest.net.server.BookService;
import com.qzsang.retrofittest.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ListView lv_list;

    String[] texts = new String[]{
            "简单测试请求方法Post",
            "retrofit的简单封装ing",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_list = (ListView) findViewById(R.id.lv_list);
        lv_list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return texts.length;
            }

            @Override
            public String getItem(int position) {
                return texts[position];
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(MainActivity.this);
                   /* ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            60);
                    convertView.setLayoutParams(lp);*/
                }
                TextView textView = (TextView) convertView;
                textView.setText(getItem(position));
                return convertView;
            }
        });
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        test0();
                        break;
                    case 1:
                        test1();
                        break;

                }
            }
        });
    }


    private void showLog(String test) {
        Log.e("log", test + "");
    }


    private void test1() {
        BookService service = RetrofitClient.create(BookService.class);
        Call<BooksBean> call = service.getBooks("2874");
        call.enqueue(new Callback<BooksBean>() {
            @Override
            public void onResponse(Call<BooksBean> call, Response<BooksBean> response) {
            //    BooksBean booksBean = response.body();
                LogUtil.e("onResponse",  response.body() + "");
            }


            @Override
            public void onFailure(Call<BooksBean> call, Throwable t) {
                LogUtil.e("onFailure", "onFailure");
            }
        });
    }

    private void test0() {

        //添加拦截
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {//添加拦截器
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        okhttp3.Response response = chain.proceed(request);
                        showLog("request url:" + request.url());

                        //一旦调用下面这句话  会导致session  close
                        // showLog("body:" + response.body().string());


                        showLog("isSuccessful:" + response.isSuccessful());
                        return response;
                    }
                })
                .connectTimeout(300, TimeUnit.SECONDS)//设置超时时间  30s
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.baidu.com/")
                .addConverterFactory(GsonConverterFactory.create())//设置解析器
                .client(client)//设置客户端
                .build();

        BookService service = retrofit.create(BookService.class);
        final Call<BooksBean> call = service.getBooks("2874");


        //call.execute();  使用这个会堵塞线程
        call.enqueue(new Callback<BooksBean>() {
            @Override
            public void onResponse(Call<BooksBean> call, Response<BooksBean> response) {
                BooksBean booksBean = response.body();
                showLog(booksBean.getList().get(20).getTitle());
            }


            @Override
            public void onFailure(Call<BooksBean> call, Throwable t) {
                showLog("onFailure");
            }
        });

    }


}
