package com.example.project02_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project02_iot.common.CommonVal;
import com.example.project02_iot.conn.CommonConn;
import com.example.project02_iot.customer.CustomerFragment;
import com.example.project02_iot.employee.EmployeeFragment;
import com.example.project02_iot.etc.TabActivity;
import com.example.project02_iot.member.LoginActivity;
import com.example.project02_iot.member.MemberVO;
import com.example.project02_iot.notice.NoticeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {
    TextView success;
    BottomNavigationView btm_nav;
    Button btn_tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btm_nav = findViewById(R.id.btm_nav);
        changeFragment(new CustomerFragment());


        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id_chk = item.getItemId();

                if(id_chk == R.id.menu_cus){

                    changeFragment(new CustomerFragment());
                }else if(id_chk == R.id.menu_emp){
                    changeFragment(new EmployeeFragment());

                }else if(id_chk == R.id.menu_noti) {
                    changeFragment(new NoticeFragment());
                }

                return true;    //이거해두면 바텀네비게이션 선택모션 안나옴
            }
        });

    }

    //frgment 재활용 할 수 있는 메소드
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}