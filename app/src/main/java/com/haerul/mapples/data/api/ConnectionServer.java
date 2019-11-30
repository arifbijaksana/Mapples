package com.haerul.mapples.data.api;

import com.google.gson.JsonObject;
import com.haerul.mapples.util.Constants;

import javax.inject.Inject;

import retrofit2.Call;


public class ConnectionServer {

    @Inject ApiInterface apiInterface;
    
    public ConnectionServer(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Call<JsonObject> loginCall(JsonObject body) {
        return apiInterface.login(body);
    }

    public Call<JsonObject> getLinkDb(JsonObject head, JsonObject body) {
        return apiInterface.initDB(
                head.get(Constants.SECURITY_KEY).getAsString(),
                body
        );
    }
}
