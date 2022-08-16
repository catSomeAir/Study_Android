package com.example.project02_iot.conn;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {

    //7. Api인터페이스
    @FormUrlEncoded
    @POST
    Call<String> getData(@Url String url, @FieldMap HashMap<String,Object> parameters);

}
