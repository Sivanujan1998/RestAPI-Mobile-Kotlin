package com.example.myapplicationfragment.Posts.api;
import com.example.myapplicationfragment.Posts.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {
    // "http://jsonplaceholder.typicode.com/posts"
    @GET("posts")
       public Call<List<Post>> listdetails();

     String baseurl = "https://jsonplaceholder.typicode.com";

}
