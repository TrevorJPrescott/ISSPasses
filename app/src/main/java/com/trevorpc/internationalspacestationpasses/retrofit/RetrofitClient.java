package com.trevorpc.internationalspacestationpasses.retrofit;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    Retrofit ourInstance;

    @Inject
    RetrofitClient() {
         ourInstance = new Retrofit.Builder()
                .baseUrl("http://api.open-notify.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getOurInstance() {
        return ourInstance;
    }
}

