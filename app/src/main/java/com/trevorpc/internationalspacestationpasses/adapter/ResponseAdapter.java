package com.trevorpc.internationalspacestationpasses.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trevorpc.internationalspacestationpasses.R;
import com.trevorpc.internationalspacestationpasses.model.Response;


import java.util.Date;
import java.util.List;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseViewHolder> {

    Context context;
    List<Response> responseList;

    public ResponseAdapter(Context context, List<Response> responses) {
        this.context = context;
        this.responseList = responses;
    }

    @NonNull
    @Override
    public ResponseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.response_layout,viewGroup,false);
        return new ResponseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseViewHolder holder, int position) {

        Date date = new Date(Long.valueOf(responseList.get(position).risetime)*1000);
        Log.d("DATE", "Date: " + date.toString());
        holder.tvDuration.setText(String.valueOf(responseList.get(position).duration)+ " seconds");
        holder.tvTime.setText(date.toString());
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }
}
