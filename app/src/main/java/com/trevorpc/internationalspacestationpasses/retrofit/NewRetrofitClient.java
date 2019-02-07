package com.trevorpc.internationalspacestationpasses.retrofit;

import dagger.Component;
import retrofit2.Retrofit;

@Component
public interface NewRetrofitClient {
    RetrofitClient getRetrofitClient();
}
