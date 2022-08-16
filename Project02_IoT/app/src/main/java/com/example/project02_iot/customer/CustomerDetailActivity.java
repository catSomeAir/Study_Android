package com.example.project02_iot.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.project02_iot.R;
import com.example.project02_iot.conn.CommonConn;
import com.example.project02_iot.member.MemberVO;
import com.google.gson.Gson;

public class CustomerDetailActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton rdo_man,rdo_woman;
    EditText edt_email,edt_phone;
    Button btn_update,btn_close;
    ImageView imgv_profile;
    TextView tv_name;
    String select_btn="";
    RadioGroup rdo_grp;
    CustomerVO vo = null;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdetail);

        tv_name = findViewById(R.id.tv_name);
        rdo_man = findViewById(R.id.rdo_man);
        rdo_woman = findViewById(R.id.rdo_woman);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_phone);
        btn_update = findViewById(R.id.btn_update);
        btn_close = findViewById(R.id.btn_close);
        imgv_profile = findViewById(R.id.imgv_profile);

//        rdo_grp = findViewById(R.id.rdo_grp);

//        rdo_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(checkedId == R.id.rdo_man){
//                    vo.setGender("남");
//                }else if(checkedId == R.id.rdo_woman) {
//                    vo.setGender("여");
//                }
//            }
//        });

        Intent intent = getIntent();
        //첫번째방법 : 아이디만
        id = intent.getIntExtra("id", 0);
        select_btn = intent.getStringExtra("정보수정");

        CustomerVO tempvo = (CustomerVO) intent.getSerializableExtra("dto");


        //두번째방법 : VO전체
        Log.d("tempvo", "onCreate: " + tempvo.getId());

        CommonConn conn = new CommonConn(this, "detail.cu");
        conn.addParams("id",id);
        conn.executeConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                Log.d("데이터 : ", "onResult: " + data);
                vo = new Gson().fromJson(data, CustomerVO.class);
                setWidget(vo);

            }
        });

        if(intent.getStringExtra("정보수정").equals("삭제")){
            CommonConn deleteconn = new CommonConn(this, "delete.cu");
            deleteconn.addParams("id",id);
            deleteconn.executeConn(new CommonConn.ConnCallback() {
                @Override
                public void onResult(boolean isResult, String data) {
                    finish();

                }
            });
        }


        btn_update.setOnClickListener(this);

        //라디오 버튼 클릭
        rdo_man.setOnClickListener(this);
        rdo_woman.setOnClickListener(this);

        //닫기
        btn_close.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.rdo_man){
            rdo_man.setChecked(true);
            rdo_woman.setChecked(false);
        }else if(v.getId() == R.id.rdo_woman){
            rdo_woman.setChecked(true);
            rdo_man.setChecked(false);
        }
        if(v.getId() == R.id.btn_close){
            onBackPressed();
        }

        if(v.getId() == R.id.btn_update){
            CommonConn updateconn = new CommonConn(this, "update.cu");

            Log.d("음", "onClick: ㅎㅇ");
            CustomerVO vo = new CustomerVO(id, edt_email.getText().toString(),edt_phone.getText().toString(), rdo_man.isChecked()?"남":"여");
            Gson gson = new Gson();
            String data = gson.toJson(vo);
            updateconn.addParams("data", data);
            updateconn.executeConn(new CommonConn.ConnCallback() {
                @Override
                public void onResult(boolean isResult, String data) {
                    finish();
                }
            });
        }
    }


    public void setWidget(CustomerVO vo){
        if(!select_btn.equals("정보수정")){
          btn_update.setVisibility(View.GONE);
        }
        tv_name.setText(vo.getName()+"님 의 고객정보");

        if(vo.getGender().equals("남")){
            rdo_man.setChecked(true);

            imgv_profile.setImageResource(R.drawable.male);

        }else{
            rdo_woman.setChecked(true);
            imgv_profile.setImageResource(R.drawable.female);
        }

        edt_email.setText(vo.getEmail());
        edt_phone.setText(vo.getPhone());

       if(!select_btn.equals("정보수정")){
        rdo_man.setEnabled(false);
        rdo_woman.setEnabled(false);
        edt_email.setEnabled(false);
        edt_phone.setEnabled(false);

       }
    }
}