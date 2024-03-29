package com.example.project02_iot.conn;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonConn {



    private String url; //생성 시 URL 만 입력받게끔 만들예정
    private HashMap<String, Object> params;
    private ConnCallback callback;
    ProgressDialog dialog;
    Context context;

    public CommonConn(Context context, String url) {
        this.context = context;
        this.url = url;
        this.dialog = new ProgressDialog(context);
        params = new HashMap<>();       //생성시에 자동적으로 HashMap 데이터 입력할 주소값 생성되도록 하기

    }

    public void addParams(String key, Object value){
        params.put(key, value);
    }


    public void onPreExecute() {
        if(dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("데이터 처리");
        dialog.setMessage("데이터를 가져오는 중...");
        dialog.show(); //<= 실제 보이게 처리 ※
    }
    protected void onPostExecute() {
        if(dialog == null) return;
        dialog.dismiss();
    }

    //실행처리 메소드 가장중요함!!!!!!!!!!!!!!!!!
    public void executeConn(ConnCallback callback){
        this.callback = callback;

        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);

        //Call 은 retrofit2를 import 한다!!!
        Call<String> call = apiInterface.getData(url,params);
        onPreExecute();
        //실행부분
        call.enqueue(new Callback<String>() {

            // onResponse : 서버와 통신성공( +반환데이터 ) -> true 와 date 리턴

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResult(true, response.body());
                onPostExecute();
            }

            // onFailure : 서버와 통신실패( +반환오류메시지 ) -> false 와 Throwable
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onResult(false, t.getMessage());
                onPostExecute();
                //로그찍고, 토스트 창으로 서버이상 이라고 메시지나오게 해보기
                Toast.makeText(context, "서버이상", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface ConnCallback{
        public void onResult(boolean isResult, String data);
    }
}
