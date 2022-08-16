package com.example.project02_iot.employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_iot.R;
import com.example.project02_iot.customer.CustomerFragment;
import com.example.project02_iot.customer.CustomerVO;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EmployeeVO> list;
    Context context;
    EmployeeFragment employeeFragment;

    public EmployeeAdapter(Context context, LayoutInflater inflater, ArrayList<EmployeeVO> list, EmployeeFragment employeeFragment) {
        this.context = context;
        this.inflater = inflater;
        this.list = list;
        this.employeeFragment = employeeFragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_hr_rcv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ln_emp;
        ImageView imgv_hr;
        TextView tv_department_name, tv_name, tv_phone, emp_id;
        public ViewHolder(@NonNull View v) {
            super(v);
            imgv_hr = v.findViewById(R.id.imgv_hr);
            tv_department_name = v.findViewById(R.id.tv_department_name);
            tv_name = v.findViewById(R.id.tv_name);
            tv_phone = v.findViewById(R.id.tv_phone);
            emp_id = v.findViewById(R.id.emp_id);
            ln_emp = v.findViewById(R.id.ln_emp);
        }

        public void bind(ViewHolder h, int i) {
            h.imgv_hr.setImageResource(R.drawable.male);
            h.tv_name.setText(list.get(i).getFirst_name() + "" + list.get(i).getLast_name());
            h.tv_department_name.setText(list.get(i).getDepartment_name());
            h.tv_phone.setText(list.get(i).getPhone_number());
            h.emp_id.setText("I D : " + list.get(i).getEmployee_id());


            h.ln_emp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmployeeDialog dialog = new EmployeeDialog(context);
                    dialog.show();

                }
            });
        }
    }
}
