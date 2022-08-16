package com.example.project02_iot.customer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_iot.R;
import com.example.project02_iot.conn.CommonConn;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<CustomerVO> list;
    Context context;
    CustomerFragment customerFragment;

    public CustomerAdapter(Context context, LayoutInflater inflater, ArrayList<CustomerVO> list, CustomerFragment customerFragment) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.customerFragment = customerFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_cus_rcv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        Log.d("화긴", "getItemCount: " + list.size());
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv_cus;
        TextView tv_no, tv_name, tv_phone;
        Button btn_detail, btn_update, btn_delete;

        public ViewHolder(@NonNull View v) {
            super(v);
            imgv_cus = v.findViewById(R.id.imgv_cus);
            tv_no = v.findViewById(R.id.tv_no);
            tv_name = v.findViewById(R.id.tv_name);
            tv_phone = v.findViewById(R.id.tv_phone);
            btn_detail = v.findViewById(R.id.btn_detail);
            btn_update = v.findViewById(R.id.btn_update);
            btn_delete = v.findViewById(R.id.btn_delete);

        }

        public void bind(@NonNull ViewHolder h, int i) {
//            h.imgv_cus.setImageResource(list.get(i));
            if (list.get(i).getGender().equals("남")) {
                h.imgv_cus.setImageResource(R.drawable.male);
            } else {
                h.imgv_cus.setImageResource(R.drawable.female);

            }

            h.tv_no.setText(list.get(i).getId() + "");
            h.tv_name.setText(list.get(i).getName());
            h.tv_phone.setText(list.get(i).getPhone());
            h.btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, CustomerDetailActivity.class);
                    intent.putExtra("id", list.get(i).getId());
                    intent.putExtra("dto", list.get(i));
                    intent.putExtra("정보수정","false");
                    context.startActivity(intent);
                }
            });

            h.btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    customer_update(list.get(i).getId(), list.get(i));

                }
            });

            h.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // confirm(Web) <--> AlertDialog(Android) : 유저가 어떠한 액션을 했을때 최종적으로 확인

                    checkDelete(list.get(i).getId());


                }

            });

        }//bind

        public void customer_update(int id, CustomerVO vo){
            Intent intent = new Intent();
            intent.setClass(context, CustomerDetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("dto", vo);
            intent.putExtra("정보수정","정보수정");
            context.startActivity(intent);
        }
        public void checkDelete(int id){

            //다이얼로그 builder로 만들어두고--------------------

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("고객 정보 삭제")
                    .setMessage("정말로 삭제하시겠습니까")
                    .setIcon(R.drawable.ic_baseline_info_24);
            //사용자가 네라는 메시지를 클릭한 경우
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CommonConn conn = new CommonConn(context, "delete.cu");
                    conn.addParams("id", id);
                    conn.executeConn(new CommonConn.ConnCallback() {
                        @Override
                        public void onResult(boolean isResult, String data) {

                            customerFragment.rcv_select();
                        }
                    });
                }
            });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            //만든 builder 를 생성(create())-------------------
            AlertDialog dialog = builder.create(); //생성
            dialog.show();      //보여줌

        }
    }
}
