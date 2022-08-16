package com.example.project02_iot.notice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project02_iot.R;
import com.example.project02_iot.conn.CommonConn;
import com.example.project02_iot.employee.EmployeeAdapter;
import com.example.project02_iot.employee.EmployeeFragment;
import com.example.project02_iot.employee.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;


public class NoticeFragment extends Fragment {
    TextView tv_test;
    ImageView imgv_notice1;
    //1. 애니메이션 객체를 준비함.
    Animation flowAnim, turnAnim;
    ZoomOutPageTransformer zoomOutPageTransformer;
    //2. load 속성을 이용해서 만들어둔 애니메이션 로딩.

    //3. 위젯에 연결


    ViewPager2 notice_vp_pager2;
    RecyclerView rcv_no;
    ArrayList<Integer> img_list = new ArrayList<>();
    ArrayList<NoticeVO> list = new ArrayList<>();
    WormDotsIndicator indicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notice, container, false);
        //애니메이션----------------------------
//        tv_test = v.findViewById(R.id.tv_test);
//        flowAnim = AnimationUtils.loadAnimation(getContext(),R.anim.flow);
//        imgv_notice1 = v.findViewById(R.id.imgv_notice1);
//        turnAnim = AnimationUtils.loadAnimation(getContext(), R.anim.turn);
//        imgv_notice1.startAnimation(turnAnim);
//        tv_test.startAnimation(flowAnim);
//애니메이션----------------------------
        img_list.add(new Integer(R.drawable.banner1));
        img_list.add(new Integer(R.drawable.banner2));
        img_list.add(new Integer(R.drawable.banner3));
        img_list.add(new Integer(R.drawable.banner4));
        img_list.add(new Integer(R.drawable.banner5));

        indicator = v.findViewById(R.id.indicator);

        notice_vp_pager2 = v.findViewById(R.id.notice_vp_pager2);
        Pager2Adapter adapter = new Pager2Adapter(img_list, inflater, getContext());

        notice_vp_pager2.setPageTransformer(new ZoomOutPageTransformer());
        notice_vp_pager2.setAdapter(adapter);
        indicator.setViewPager2(notice_vp_pager2);

        autoSlide();


        rcv_no= v.findViewById(R.id.rcv_no);


        CommonConn conn = new CommonConn(getContext(),"list.no");
        conn.executeConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult){
                    ArrayList<NoticeVO> list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<NoticeVO>>() {
                            }.getType());


                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    NoticeAdapter adapter = new NoticeAdapter(list, getLayoutInflater() ,getContext() , NoticeFragment.this);
                    rcv_no.setLayoutManager(manager);
                    rcv_no.setAdapter(adapter);

                }
            }
        });



        return v;
    }

    public void autoSlide() {
        // new Handler<- SlpashActivity 페이지전환
        // runOnUiThread : 비동기로 화면전환 쓰레드사용  --->> Activity 단에서만 사용가능

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < img_list.size(); i++) {
                    final int value = i;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //비동기로 디자인 작업

                            notice_vp_pager2.setCurrentItem(value);  //현재 아이템
                        }
                    });

                    try {
                        if(i==img_list.size()-1){
                            i=-1;
                        }
                        Thread.sleep(2000);

                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer, ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}