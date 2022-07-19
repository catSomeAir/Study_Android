package com.example.and06_scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_txt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tv_txt = findViewById(R.id.tv_txt);
//        tv_txt.setText("글씨KYM");
//
//        // append <- 기존 글씨에 현재 붙일 String을 더한다.
//        for (int i = 0 ; i < 100 ; i++){
//            tv_txt.append("안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요");
//        }


        //setText로 나의 이니셜로 텍스트뷰 글씨 바꿔보기
        // ctrl + p ( 메소드 파라메터 부에서 누름 )=> 어떤 파라메터 넘겨야하는지 나옴.
    }
}