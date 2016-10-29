package com.qzsang.retrofittest.net.server;

import com.qzsang.retrofittest.net.bean.BooksBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BookService {


  @Headers("apikey:f0648e7a29ce3eba95d3ba4c4eb38a5c")
  @POST("tngou/book/show")
  Call<BooksBean> getBooks(@Query("id") String id);//如果是对象  可以用@Body   ，多个Query 可以使用 @QueryMap
}