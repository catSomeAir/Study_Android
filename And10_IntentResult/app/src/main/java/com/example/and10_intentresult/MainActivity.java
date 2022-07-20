package com.example.and10_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn ;
    // 안드로이드의 4대 컴포넌트 : (현) 액티비티
    // 4가지의 컴포넌트들간의 통신을 담당 : Intent(전달)

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                intent.putExtra("test","넘길값");
                intent.putExtra("num",100);

                LoginDTO dto = new LoginDTO( 11 , "admin");
                ArrayList<LoginDTO> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new LoginDTO(i , "pw" + i));
                }
                // LoginDTO타입을 요소로가지는 ArrayList를 만들기
                // size == 10 이어야한다.

                intent.putExtra("list" , list);
                intent.putExtra("dto" , dto); // 직렬화 Serializable ???????

                //11 , pw : admin

                startActivity(intent);
                //현재위치=>이동할위치 두개를 파라메터로 지정을 해둠.
                // 직접 LoginActivity라는 액티비티를 하나만들고
                // 메인액티비티의 버튼을 클릭했을때 ↑ 새로 띄워지게 처리해보기.

                //Intent intent = new Intent(MainActivity.this, SubActivity.class);
                //startActivity(intent);
            }
        });//View ( => Button )

    }
}