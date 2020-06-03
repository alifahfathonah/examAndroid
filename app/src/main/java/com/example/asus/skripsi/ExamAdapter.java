package com.example.asus.skripsi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.CardViewHolder> {
    private Context context;
    private List<ModelDaftarUjianData> exams;

    ExamAdapter(Context context) {
        this.context = context;
    }

    public List<ModelDaftarUjianData> getExams() {
        return exams;
    }

    void setExams(List<ModelDaftarUjianData> exams) {
        this.exams = exams;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_exam, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int i) {
        Glide.with(holder.itemView.getContext())
                .load(R.drawable.iconujian)
                .apply(new RequestOptions().override(120, 120))
                .into(holder.imgPhoto);
        holder.tvName.setText(exams.get(i).getNamaMatkul());
        holder.tvDate.setText(exams.get(i).getTglUjian());

    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

     class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDate;
        Button btnEnter;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            btnEnter = itemView.findViewById(R.id.btn_enter_exam);
            btnEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FormToken.class).putExtra("enter_ujian", exams.get(getAdapterPosition())));
                }
            });
        }
    }

}
