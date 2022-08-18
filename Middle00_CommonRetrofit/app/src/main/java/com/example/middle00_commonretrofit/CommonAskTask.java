package com.example.middle00_commonretrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.tv.TvContract;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.transform.Result;

public class CommonAskTask extends AsyncTask<String, String, String> {

    public AsynkTaskCallBack callBack;
    Context context;        //현재 화면에서 필요한 정보에 대한 권한 : 프로그레스 다이얼로그 사용시 필요
    ProgressDialog dialog;
    String url = "";
    HashMap<String, Object> params;


    public CommonAskTask(Context context, String url) {
        this.context = context;
        this.url = url;
        this.dialog = new ProgressDialog(context);  //객체생성시에 progressdialog 인스턴스화
        this.params = new HashMap<>();
    }

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public void excuteAsk(AsynkTaskCallBack callBack) {
        this.callBack = callBack;
        this.execute();
    }

    //작업 시작전 프로그레스 다이얼로그를 보여줌.
    @Override
    protected void onPreExecute() {
        if (dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("데이터 처리");
        dialog.setMessage("데이터 가져오는중..");
        dialog.show();
    }

    //재사용이 가능하려면 바뀌어야하는 부분들있음
    //어디를 바꿀까


    @Override
    protected String doInBackground(String... strings) {
        String rtnData = null;
        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);


        try {
            rtnData = apiInterface.getData(url, params).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return rtnData;
    }

    @Override       //위의 doInBackground 실행되고나서 리턴값들고 여기가 실행됨.
    protected void onPostExecute(String data) {
        if (dialog == null) return;
        dialog.dismiss();
        //    Log.d("콜백데이터", "onPostExecute: " + s);
        //실행부
        if (data == null || data.length() == 0) {
            callBack.onResult(data, false);   //->객체생성한 onresult에 해당값 넘어감
        } else {
            callBack.onResult(data, true);   //->객체생성한 onresult에 해당값 넘어감
        }
    }


    //다시 콜백을 주기 위한 메소드 ( 인터페이스 ) :정의
    public interface AsynkTaskCallBack {
        void onResult(String result, boolean isResult);

    }

}
