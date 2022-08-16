package com.example.project02_iot.notice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_iot.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    ArrayList<NoticeVO> list;
    LayoutInflater inflater;
    Context context;
    Fragment fragment;
    public NoticeAdapter(ArrayList<NoticeVO> list, LayoutInflater inflater, Context context, Fragment fragment) {
        this.list = list;
        this.inflater = inflater;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_no_rcv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        Log.d("list", "getItemCount: 사이즈"+list.size());
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notice_tv_title, notice_tv_writer, notice_tv_writedate, notice_tv_content;
        public ViewHolder(@NonNull View v) {
            super(v);
            notice_tv_title = v.findViewById(R.id.notice_tv_title);
            notice_tv_writer = v.findViewById(R.id.notice_tv_writer);
            notice_tv_writedate = v.findViewById(R.id.notice_tv_writedate);
            notice_tv_content = v.findViewById(R.id.notice_tv_content);

        }

        public void bind(@NonNull ViewHolder h, int i){
            h.notice_tv_title.setText(list.get(i).getTitle());
            h.notice_tv_writer.setText(list.get(i).getWriter());
            h.notice_tv_writedate.setText(list.get(i).getWritedate());
            h.notice_tv_content.setText(list.get(i).getContent());
        }
    }
}
