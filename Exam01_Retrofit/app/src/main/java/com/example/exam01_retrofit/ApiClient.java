package com.example.exam01_retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static final String BASE_URL="http://192.168.0.33/mid/";
    private static Retrofit retrofit;

    public static Retrofit getApiclient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build())
                    .build();

        }

        return retrofit;
    }
}
