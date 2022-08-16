package com.example.project02_iot.conn;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;

//백그라운드에서 비동기작업 하는 방법 : 결과가 나올때까지 서브 스레드가 대기상태여서 메인도 같이 기다리게됨
//데이터 건수가 많을 때, 꼭 데이터를 받아서 사용해야 할 경우만 아래와 같이 사용할 예정

//5.상속 : AsyncTask , @override
public class CommonAskTask extends AsyncTask<String, String, String> {

    //4. 필요한 필드생성
    public AsynkTaskCallBack callBack;
    Context context;
    String url = "";
    HashMap<String, Object> params;

    //11. 생성자
    public CommonAskTask( Context context, String url) {
        this.context = context;
        this.url = url;
        this.params = new HashMap<>();
    }

    //9. 메소드 재정의
    @Override
    protected String doInBackground(String... strings) {
        //동기처리가 돼버림..
        String rtnData = null;
        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        try {
            rtnData = apiInterface.getData(url, params).execute().body();       //동기처리 : 결과가 올때 까지 메인스레드 대기 하게됨
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rtnData;
    }

    //10. 콜백과 파라메터 메소드 추가
    public void addParams(String key , Object value){
        this.params.put(key, value);
    }
    public void excuteAsk(AsynkTaskCallBack callBack){
        this.callBack = callBack;
        this.execute();
    }

    //12.
    @Override
    protected void onPostExecute(String data) {

        if (data == null || data.length() == 0 || data.equals("null")) {
            callBack.onResult(data, false);   //->객체생성한 onresult에 해당값 넘어감
        } else {
            callBack.onResult(data, true);   //->객체생성한 onresult에 해당값 넘어감
        }
    }


    //3. 이너클래스 인터페이스

    public interface AsynkTaskCallBack {
        void onResult(String result, boolean isResult);
    }
}
