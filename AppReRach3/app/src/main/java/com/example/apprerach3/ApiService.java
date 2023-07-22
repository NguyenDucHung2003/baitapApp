package com.example.apprerach3;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("register")
    Call<Void> register(@Body User user);
    @GET("login")
    Call<List<User>> getUsers();
    @PUT("fogotpassword")
    Call<Void> fogotpassword(@Body User user);
}
