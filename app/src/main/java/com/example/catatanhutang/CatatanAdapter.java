package com.example.catatanhutang;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.MyViewHolder> {
    private Context context;
    private List<CatatanModel> data = new ArrayList<>();

    public CatatanAdapter(Context context, List<CatatanModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_catatan, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tvJudul.setText(data.get(position).getJudul());
        holder.tvJumlah.setText("Rp. "+data.get(position).getJumlahhutang());
        holder.tvTanggal.setText(data.get(position).getTanggal());


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent pindah = new Intent(context, DetailCatatnActivity.class);
                pindah.putExtra(DetailCatatnActivity.KEY_ID, data.get(position).getId());
                context.startActivity(pindah);



            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvJumlah, tvTanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
        }
    }
}
