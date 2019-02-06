package com.trevorpc.internationalspacestationpasses.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.trevorpc.internationalspacestationpasses.R;

public class ResponseViewHolder extends RecyclerView.ViewHolder {
    TextView tvTime, tvDuration;
    public ResponseViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        tvDuration = (TextView) itemView.findViewById(R.id.tvDuration);
    }
}
