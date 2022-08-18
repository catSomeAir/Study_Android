package com.example.middle00_commonretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //구현부
        //인터페이스가 어떤 결과를 받고나서 처리를 만들어둠
//        CommonAskTask askTask = new CommonAskTask(this, new CommonAskTask.AsynkTaskCallBack() {
//            @Override
//            public void onResult(String result) {
//                Log.d("메인엑티비티", "onResult: "+result);
//            }
//        });
//
//
//        askTask.execute(); //.get() 하면안됨 : 동기처리 하게 해서 오류생김



        //구현부
        //인터페이스가 어떤 결과를 받고나서 처리를 만들어둠
        CommonAskTask askTask = new CommonAskTask(this, "detail.cu");
        askTask.addParams("data","JSY");
        askTask.excuteAsk(new CommonAskTask.AsynkTaskCallBack() {
            @Override
            public void onResult(String result, boolean isResult) {
                if(isResult){
                    Log.d("음", "onResult: " + result);
                }else{
                    Log.d("음", "수신 실패" + isResult );
                }
            }
        });

//        askTask.execute(); //.get();// 비동기 x , 동기 처리를 강제로 함 <- 여러번 사용하면 OS는 해당앱이 이상하다는걸
//        //감지를 하고 NetWork 오류를 발생시키고 앱을 강제 종료처리함

    }

    public class CustomerList{
        CustomerListResult customerListResult;
    }


    private class CustomerListResult {
        ArrayList<CustomerVO> customerLists = new ArrayList<>();
    }

}