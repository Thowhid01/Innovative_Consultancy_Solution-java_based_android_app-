package com.efty.innovativeconsultancysolutions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyClientAdapter extends RecyclerView.Adapter<MyClientAdapter.ViewHolder>{

    private ArrayList<Client> clientData;
    private Context context;

    public MyClientAdapter(ArrayList<Client> clientData, Context context) {
        this.clientData = clientData;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView clientCardView;
        TextView nameTv,emailTv,dateTv,clientDetailsTv;
        Button yesBtn,noBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.clientCardView=itemView.findViewById(R.id.clientCardId);
            this.nameTv=itemView.findViewById(R.id.nameTvId);
            this.emailTv=itemView.findViewById(R.id.emailTvId);
            this.dateTv=itemView.findViewById(R.id.dateTvId);
            this.clientDetailsTv=itemView.findViewById(R.id.clientDetailsTvId);
            this.yesBtn=itemView.findViewById(R.id.yesBtnId);
            this.noBtn=itemView.findViewById(R.id.noBtnId);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View clientView = layoutInflater.inflate(R.layout.clientlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(clientView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Client client=clientData.get(position);
        holder.nameTv.setText(clientData.get(position).getUserName());
        holder.emailTv.setText(clientData.get(position).getUserEmail());
        holder.dateTv.setText(clientData.get(position).getConsultationDate());
        holder.clientDetailsTv.setText(clientData.get(position).getConsultatDetails());
        holder.clientCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Consultant Email : "+clientData.get(holder.getAdapterPosition()).getConsultantEmail(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Yes excepted", Toast.LENGTH_SHORT).show();
            }
        });

        holder.noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Not excepted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return clientData.size();
    }


}
