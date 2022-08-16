package com.example.project02_iot.employee;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project02_iot.R;
import com.example.project02_iot.conn.CommonConn;
import com.example.project02_iot.customer.CustomerAdapter;
import com.example.project02_iot.customer.CustomerFragment;
import com.example.project02_iot.customer.CustomerVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EmployeeFragment extends Fragment {
    RecyclerView rcv_hr;
    SwipeRefreshLayout swipe_emp;
    SearchView search_emp;
    String keyword = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_employee, container, false);
        rcv_hr = v.findViewById(R.id.rcv_hr);
        search_emp = v.findViewById(R.id.search_emp);
        swipe_emp = v.findViewById(R.id.swipe_emp);

        rcv_select();

        //swipe 새로고침 초기화하기
        swipe_emp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refresh는 스와이프가 되서 동작하는 상태가 되면 처리할 이벤트
                rcv_select();
                //빙글빙글 도는거 없애기
                swipe_emp.setRefreshing(false);
            }
        });

        search_emp.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //돋버기 버튼 눌렀을 경우
            @Override
            public boolean onQueryTextSubmit(String query) {

                rcv_search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                rcv_search(newText);
                return false;
            }
        });

        return v;
    }

    private void rcv_search(String keyword) {
        CommonConn conn = new CommonConn(getContext(), "search.hr");
        conn.addParams("keyword", keyword);
        conn.executeConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {

                    ArrayList<EmployeeVO> list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<EmployeeVO>>() {
                            }.getType());


                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    EmployeeAdapter adapter = new EmployeeAdapter(getContext(), getLayoutInflater(), list, EmployeeFragment.this);
                    rcv_hr.setLayoutManager(manager);
                    rcv_hr.setAdapter(adapter);
                    swipe_emp.setRefreshing(false);
                }

            }
        });
    }

    private void rcv_select() {
        CommonConn conn = new CommonConn(getContext(), "list.hr");
        conn.executeConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {

                    ArrayList<EmployeeVO> list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<EmployeeVO>>() {
                            }.getType());


                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    EmployeeAdapter adapter = new EmployeeAdapter(getContext(), getLayoutInflater(), list, EmployeeFragment.this);
                    rcv_hr.setLayoutManager(manager);
                    rcv_hr.setAdapter(adapter);
                    swipe_emp.setRefreshing(false);
                }

            }
        });
    }
}