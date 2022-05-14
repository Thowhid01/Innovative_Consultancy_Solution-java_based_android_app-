package com.efty.innovativeconsultancysolutions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private ArrayList<Consultant> listData;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView dateTv,nameTv,phoneTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dateTv=(TextView) itemView.findViewById(R.id.list_itemTextViewId);
            this.nameTv=(TextView) itemView.findViewById(R.id.nameTextViewId);
            this.phoneTv=(TextView) itemView.findViewById(R.id.phoneTextViewId);
            this.cardView=(CardView) itemView.findViewById(R.id.cardViewId);
        }
    }

    public MyListAdapter(ArrayList<Consultant> listData, Context context) {
        this.listData = listData;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.item_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Consultant listItemData=listData.get(position);
        holder.dateTv.setText(listData.get(position).getDate());
        holder.nameTv.setText(listData.get(position).getName());
        holder.phoneTv.setText(listData.get(position).getPhone());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Hello : "+holder.nameTv.getText(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


}
