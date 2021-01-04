package com.example.yrsshipper.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yrsshipper.R;

public class OrderViewHolder  extends RecyclerView.ViewHolder  {

    public TextView txtOrderId, txtOrderDate,txtOrderStatus, txtOrderPhone, txtOrderAddress;

    public Button btnShipping;


    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txtOrderId = (TextView) itemView.findViewById(R.id.order_id);
        txtOrderDate = (TextView) itemView.findViewById(R.id.order_date);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_phone);
        txtOrderAddress = (TextView) itemView.findViewById(R.id.order_address);

        btnShipping = (Button) itemView.findViewById(R.id.btnShipping);





    }


}

