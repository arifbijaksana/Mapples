package com.haerul.mapples.data.api;

import com.google.gson.JsonObject;
import com.haerul.mapples.util.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/login")
    Call<JsonObject> login(@Body JsonObject jsonObject);

    @POST("api/init_db")
    Call<JsonObject> initDB(
            @Header(Constants.SECURITY_KEY) String auth_token,
            @Body JsonObject jsonObject);
}
