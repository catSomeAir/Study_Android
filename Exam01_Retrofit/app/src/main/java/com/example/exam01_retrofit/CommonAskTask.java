package com.example.exam01_retrofit;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

public class CommonAskTask extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {
        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", "JSY");
        try {
            String rtnData = apiInterface.getData("list.cu", map).execute().body();
            Log.d("음", "doInBackground: "+rtnData);
            return rtnData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
