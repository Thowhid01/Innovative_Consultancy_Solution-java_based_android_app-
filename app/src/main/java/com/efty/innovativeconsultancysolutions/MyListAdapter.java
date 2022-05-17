package com.efty.innovativeconsultancysolutions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private ArrayList<Consultant> listData;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView nameTv,phoneTv;
        public ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=(ImageView) itemView.findViewById(R.id.list_itemImageViewId);
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
        Picasso.get().load(listData.get(position).getImage()).into(holder.imageView);
        holder.nameTv.setText(listData.get(position).getName());
        holder.phoneTv.setText(listData.get(position).getWorkbackground()+"  "+listData.get(position).getPhone());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(view.getContext(),OneConsultant.class);
                intent.putExtra("about",listData.get(holder.getAdapterPosition()).getAbout());
                intent.putExtra("bloodgroup",listData.get(holder.getAdapterPosition()).getBloodgroup());
                intent.putExtra("date",listData.get(holder.getAdapterPosition()).getDate());
                intent.putExtra("gender",listData.get(holder.getAdapterPosition()).getGender());
                intent.putExtra("image",listData.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("name",listData.get(holder.getAdapterPosition()).getName());
                intent.putExtra("phone",listData.get(holder.getAdapterPosition()).getPhone());
                intent.putExtra("workbackground",listData.get(holder.getAdapterPosition()).getWorkbackground());
                intent.putExtra("email",listData.get(holder.getAdapterPosition()).getEmail());
                view.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


}
