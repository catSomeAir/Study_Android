package com.example.project02_iot.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_iot.R;

import java.util.ArrayList;

public class Pager2Adapter extends RecyclerView.Adapter<Pager2Adapter.ViewHolder>{
    ArrayList<Integer> list;
    LayoutInflater inflater;
    Context context;

    public Pager2Adapter(ArrayList<Integer> list, LayoutInflater inflater, Context context) {
        this.list = list;
        this.inflater = inflater;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_noti_pager, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView banner1;
        public ViewHolder(@NonNull View v) {
            super(v);
            banner1 = v.findViewById(R.id.banner1);
        }
        public void bind(@NonNull ViewHolder h, int i){
            h.banner1.setImageResource(list.get(i));
        }
    }
}
