package com.nayan.sampleproject.retrofit;

import com.nayan.sampleproject.model.Example;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiRequest {

    @GET("search?")
    Call<Example> getResponse(
            @Query("q") String q );
}
