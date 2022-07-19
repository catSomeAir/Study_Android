package com.example.and05_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_imgv1 , btn_imgv2 ;
    ImageView imgv1 , imgv2, imgv3;
    ArrayList<ImageView> list = new ArrayList<>();
    int flag = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);

        list.add(imgv1);
        list.add(imgv2);
        list.add(imgv3);

        btn_imgv1 = findViewById(R.id.btn_img1);
        btn_imgv2 = findViewById(R.id.btn_img2);

        btn_imgv1.setOnClickListener(this);
        btn_imgv2.setOnClickListener(this);
     /*   btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgv3.setVisibility(View.GONE);
            }
        });*/



    }
    //==========================2번 방법==========================
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_img1){
            flag ++ ;

        }else{
            flag -- ;


        }

        setImageGone();
        if(flag>2) flag = 0;
        else if(flag<0) flag = 2;
    }
    public void setImageGone(){
        for (int i = 0 ; i<list.size() ; i ++){
            list.get(i).setVisibility(View.GONE);
        }
        list.get(flag).setVisibility(View.VISIBLE);
    }

//==========================1번 방법==========================


 /*   @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_img1){
            flag ++ ;
            if(flag <=4 ) flag = 1;
            setImageGone();
        }else{
            flag -- ;
            if(flag <=0 ) flag = 3;
            setImageGone();
        }
    }*/

   /* public void setImageGone() {
        imgv1.setVisibility(View.GONE);
        imgv2.setVisibility(View.GONE);
        imgv3.setVisibility(View.GONE);

        if (flag == 1) {
            imgv1.setVisibility(View.VISIBLE);
        } else if (flag == 2) {
            imgv2.setVisibility(View.VISIBLE);
        } else if (flag == 3) {
            imgv3.setVisibility(View.VISIBLE);
        }
    }*/
    //==========================1번 방법==========================
}