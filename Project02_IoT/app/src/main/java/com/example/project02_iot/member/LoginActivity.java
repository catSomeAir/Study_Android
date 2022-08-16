package com.example.project02_iot.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project02_iot.MainActivity;
import com.example.project02_iot.R;
import com.example.project02_iot.common.CommonMethod;
import com.example.project02_iot.common.CommonVal;
import com.example.project02_iot.conn.CommonAskTask;
import com.example.project02_iot.conn.CommonConn;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //1. 스프링이용한 로그인처리 : 완료
    //2. SharedPreferences : 공유자원이용 ( 캐시 이용해서 자동로그인기능) : 완료
    //3. 소셜로그인 : 예정


    EditText edt_id, edt_pw;
    Button btn_login, btn_join, btn_social;
    CheckBox chkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.login_edt_id);
        edt_pw = findViewById(R.id.login_edt_pw);

        btn_login = findViewById(R.id.login_btn_login);
        btn_join = findViewById(R.id.login_btn_login);
        btn_social = findViewById(R.id.login_btn_social);

        chkbox = findViewById(R.id.login_chkbox);


        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String userid = preferences.getString("id", "등록안됨");   //공유자원에 데이터를 저장한 경우 읽는 방법
        String userpw = preferences.getString("pw", "등록안됨");   //공유자원에 데이터를 저장한 경우 읽는 방법
        Log.d("공유자원", "onCreate: " + userid +""+ userpw);

        //btn_login 클릭시 id, pw값 로그에 출력
        btn_login.setOnClickListener(this);

        //자동입력 되도록 하고 login 처리까지 해야함
        //default가 아니고 chkbox가 체크시에는 저장된 정보를 editext에 넣고 로그인처리해라
        if(!userid.equals("등록안됨")&&!userpw.equals("등록안됨")){
            chkbox.setChecked(true);
            edt_id.setText(userid);
            edt_pw.setText(userpw);
            login();
        }


        //만들어져있어서 get으로 받아온다.
        //내장 DB사용 : 자동로그인 체크 등의 간단한것들 -> app info 에 Storage & cash 에서 사용자가 지워버릴 수 있다..
        //getPreferences(어떤 용도?)


        edt_pw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    try {
                        btn_login.performClick();
                    } catch (Exception e) {

                    }
                    return true;
                }
                return false;
            }
        });

    }//onCreate

    //로그인정보 저장 :
    public void saveLoginInfo() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();   // Editer 객체를 리턴하는 메소드
        editor.putString("id", CommonVal.loginInfo.getUserid());
        editor.putString("pw", CommonVal.loginInfo.getUserpw());
        editor.apply();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn_login) {
            if (CommonMethod.isCheckEditText(edt_id) && CommonMethod.isCheckEditText(edt_pw)) {
                login();
            }
            else {
                Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력하주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void login() {
        CommonConn conn = new CommonConn(LoginActivity.this, "login.mb");
        conn.onPreExecute();        //다이얼로그
        conn.addParams("userid", edt_id.getText().toString());
        conn.addParams("userpw", edt_pw.getText().toString());
        conn.executeConn(new CommonConn.ConnCallback() {    //여기에서 다이얼로그 dismiss
            @Override
            public void onResult(boolean isResult, String data) {   //isResult : true/false는 서버문제임
//                Log.d("결과 성공실패", "onResult: " + isResult);
//                Log.d("결과 값", "onResult: " + data + "\n");
                if (isResult) { //서버통신 성공일 경우
                    CommonVal.loginInfo = new Gson().fromJson(data, MemberVO.class); //null인경우 판단해야함

                    if(CommonVal.loginInfo==null){
                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();

                    }else{
                        //자동로그인은 유저가 선택하기 때문에 자동로그인이 체크가 되었는지 판단하고
                        // 체크가 되었을 경우만 저장
                        if(chkbox.isChecked()){
                            saveLoginInfo();
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                }

            }
        });
    }


}