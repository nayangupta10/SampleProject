package com.nayan.sampleproject.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nayan.sampleproject.model.Example;
import com.nayan.sampleproject.retrofit.ApiRequest;
import com.nayan.sampleproject.retrofit.RetrofitRequest;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocRepository {
    private ApiRequest apiRequest;
    HashMap<String,String> hashMap;

    public DocRepository(){
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<Example> getResponse(){
        final MutableLiveData<Example> data=new MutableLiveData<>();
        apiRequest.getResponse("title:DNA")
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        Log.d("Tag","response"+response);
                                if(response!=null){
                                    data.setValue(response.body());

                                    Log.d("tag","num found"+response.body().getResponse().getNumFound());
                                    Log.d("tag","max score"+response.body().getResponse().getMaxScore());
                                    Log.d("tag","max score"+response.body().getResponse().getStart());
                                }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Log.d("Tag","response"+t.toString());
                        data.setValue(null);

                    }
                });
        return data;
    }
}
