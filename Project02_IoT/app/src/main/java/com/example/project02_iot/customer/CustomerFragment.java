package com.example.project02_iot.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project02_iot.MainActivity;
import com.example.project02_iot.R;
import com.example.project02_iot.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class CustomerFragment extends Fragment {
    RecyclerView rcv_cus;
    LayoutInflater inflater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer, container, false);
        this.inflater = getLayoutInflater();
        rcv_cus = v.findViewById(R.id.rcv_cus);
        rcv_select();
        //안쪽에 들어갈 아이템(layout)

        //어뎁터 (adpater)
        //모양확인 후 : VO(DTO) ArrayList 혹은 Collection 자료구조 가진 것 넣기


        return v;
    }

    public void rcv_select() {
        //액티비티 안에서 interface(메소드 안 ) 엑티비티.this
        //액티비티  : this
        //프래그먼트에서 getContext()
        // Json : [ 데이터 ] 는 List 구조, { 데이터 } 는 Object 구조
        /// { [ { } ] }

        CommonConn conn = new CommonConn(getContext(), "list.cu");
        conn.executeConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {
                    Log.d("음1", "onResult: " + data);
                    ArrayList<CustomerVO> list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<CustomerVO>>() {
                            }.getType());
                    Log.d("사이즈", "onResult: " + list.size());


                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    CustomerAdapter adapter = new CustomerAdapter(getContext(), inflater, list, CustomerFragment.this);
                    rcv_cus.setLayoutManager(manager);
                    rcv_cus.setAdapter(adapter);
                }

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("프래그머언트", "onPause: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        rcv_select();
        Log.d("프래그머언트", "onStart: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("프래그머언트", "onStop: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("프래그머언트", "onResume: ");
    }
}