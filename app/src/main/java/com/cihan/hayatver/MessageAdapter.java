package com.cihan.hayatver;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Context context;
    List<MessageModel> list;
    Activity activity;
    String userPhone;
    Boolean state;
    int view_send =1 , view_received =2;

    public MessageAdapter(Context context, List<MessageModel> list, Activity activity,
                          String userPhone) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.userPhone = userPhone;
        state=false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==view_send){
            view=LayoutInflater.from(context).inflate(R.layout.send_layout, parent,false);
            return new ViewHolder(view);
        }  else
        {
            view=LayoutInflater.from(context).inflate(R.layout.received_layout, parent,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (state==true) {
                textView = itemView.findViewById(R.id.send_textView);
            }else
            {
                textView = itemView.findViewById(R.id.received_textView);
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getFrom().equals(userPhone))
        {
            state=true;
            return view_send;
        }   else{
            state =false;
            return  view_received;
        }

    }

}
