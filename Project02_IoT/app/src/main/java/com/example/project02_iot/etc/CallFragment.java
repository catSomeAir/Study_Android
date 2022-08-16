package com.example.project02_iot.etc;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.project02_iot.R;


public class CallFragment extends Fragment {

    int frag;

    public CallFragment(int frag) {
        this.frag = frag;
    }

    FrameLayout frag_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_call, container, false);
        frag_back = v.findViewById(R.id.frag_back);

        if(frag == 1){
        frag_back.setBackgroundColor(Color.parseColor("#990000"));

        }else if(frag ==2){
            frag_back.setBackgroundColor(Color.parseColor("#009900"));

        }else if(frag ==3){
            frag_back.setBackgroundColor(Color.parseColor("#000099"));
        }else if(frag ==4){
            frag_back.setBackgroundColor(Color.parseColor("#029410"));

        }
        return v;
    }
}