package com.trevorpc.internationalspacestationpasses.retrofit;

import com.trevorpc.internationalspacestationpasses.model.FullResponse;
import com.trevorpc.internationalspacestationpasses.model.Response;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IMyAPI {

    @GET("iss-pass.json")
    Single<FullResponse> getResponse(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("n") int number);

}


