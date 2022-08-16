package com.example.project02_iot.etc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.project02_iot.R;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {
    TabLayout tabs;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabs = findViewById(R.id.tabs);
        //setSupportActionBar 현재 상태 NoActionbar -> 우리가 만든 툴바를 이용해서 액션바 대체함
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabs.addTab(tabs.newTab().setText("통화기록").setIcon(R.drawable.ic_baseline_info_24));
        tabs.addTab(tabs.newTab().setText("스팸기록").setIcon(R.drawable.ic_baseline_info_24));
        tabs.addTab(tabs.newTab().setText("연락처").setIcon(R.drawable.ic_baseline_info_24));
        tabs.addTab(tabs.newTab().setText("차단목록").setIcon(R.drawable.ic_baseline_info_24));

//        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CallFragment()).commit();


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();    // 0부터임


                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new CallFragment(pos+1)).commit();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //선택된지 알아야

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //메뉴가 바꼈을 경우!
//        if(item.getItemId() == R.id.menu1){
//
//       }

        int pos = 0;

        if(item.getItemId() == R.id.menu1) {
            pos = 1;
        } else if(item.getItemId() == R.id.menu2){
                pos = 2;
        } else if(item.getItemId() == R.id.menu3){
            pos = 3;
        } else if(item.getItemId() == R.id.menu4){
            pos = 4;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CallFragment(pos+1)).commit();


        return super.onOptionsItemSelected(item);
    }
}