package com.victorai60.retrofit;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Author: victor
 * Date: 2016-02-28 11:26
 * Email: 497042813@qq.com
 */
public interface GithubService {
    @GET("/posts")
    void posts(Callback<List<Post>> callback);

    @GET("/posts/{id}")
    void post(@Path("id") int id, Callback<Post> callback);

    @GET("/posts")
    List<Post> post(@Query("id") int id);
}
