package com.example.asus.skripsi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SoalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ModelSoalUjianData> soal;
    private List<ModelInsertData.DataJawaban> jawaban = new ArrayList<>();

    SoalAdapter(Context context) {
        this.context = context;
    }

    public List<ModelSoalUjianData> getSoal() {
        return soal;
    }

    void setSoal(List<ModelSoalUjianData> soal) {
        this.soal = soal;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        // Inflate the layout view you have created for the list rows here
        // use the viewType to inflate specic Layouts for the ViewHolders

        switch(viewType) {
            case ModelSoalUjianData.TYPE_PG:
                // Inflate the first view type
                View view1 = layoutInflater.inflate(R.layout.item_exam_pg, viewGroup, false);
                return new viewHolder1(view1);
            case ModelSoalUjianData.TYPE_ESSAY:
                // inflate the second view type
                View view2 = layoutInflater.inflate(R.layout.item_exam_essay, viewGroup, false);
                return new viewHolder2(view2);
        }
        // always use a fallback ViewHolder
        View view = layoutInflater.inflate(R.layout.item_exam_pg, viewGroup, false);
        return new viewHolder1(view);
    }

    @Override
    public int getItemViewType(int position) {
        return soal.get(position).getType();
    }

    List<ModelInsertData.DataJawaban> getJawaban(){
//        ModelInsertData modelInsertData = new ModelInsertData("","", jawaban);

//        Log.e("MODEL INSERT", modelInsertData.toString());
//        modelInsertData.setDataJawaban(jawaban);
        return jawaban;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        if (soal.get(i).getType() == ModelSoalUjianData.TYPE_PG){
            viewHolder1 viewHolderSatu = (viewHolder1)holder;
            viewHolderSatu.tvNumber.setText(String.valueOf(i+1));
            viewHolderSatu.tvSoal.setText(soal.get(i).getSoal());
            viewHolderSatu.rbJawaban1.setText(soal.get(i).getJawaban().get(0).getJawaban());
            viewHolderSatu.rbJawaban2.setText(soal.get(i).getJawaban().get(1).getJawaban());
            viewHolderSatu.rbJawaban3.setText(soal.get(i).getJawaban().get(2).getJawaban());
            viewHolderSatu.rbJawaban4.setText(soal.get(i).getJawaban().get(3).getJawaban());

//            jawaban.add(new ModelInsertData.DataJawaban(soal.get(i).getIdDetailSoal(),"0","Tidak ada jawaban"));

        } else {
            viewHolder2 viewHolderDua = (viewHolder2)holder;
            viewHolderDua.tvNumber.setText(String.valueOf(i+1));
            viewHolderDua.tvEssay.setText(soal.get(i).getSoal());
//            jawaban.add(new ModelInsertData.DataJawaban(soal.get(i).getIdDetailSoal(),"",""));

        }
    }

    @Override
    public int getItemCount() {
        return soal.size();
    }

    public class viewHolder1 extends RecyclerView.ViewHolder {
        TextView tvNumber;
        TextView tvSoal;
        RadioButton rbJawaban1, rbJawaban2, rbJawaban3, rbJawaban4;
        RadioGroup rgGroup;
         viewHolder1(@NonNull View itemView) {
            super(itemView);

            tvNumber = itemView.findViewById(R.id.tv_number);
            tvSoal = itemView.findViewById(R.id.tv_soal);
            rgGroup = itemView.findViewById(R.id.rg_jawaban);
            rbJawaban1 = itemView.findViewById(R.id.rb_jawaban1);
            rbJawaban2 = itemView.findViewById(R.id.rb_jawaban2);
            rbJawaban3 = itemView.findViewById(R.id.rb_jawaban3);
            rbJawaban4 = itemView.findViewById(R.id.rb_jawaban4);

            rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int radioButtonID = rgGroup.getCheckedRadioButtonId();
                    View radioButton = rgGroup.findViewById(radioButtonID);
                    int idx = rgGroup.indexOfChild(radioButton);

                    if (soal.get(getAdapterPosition()).getType() == ModelSoalUjianData.TYPE_PG){

                        if(jawaban.get(getAdapterPosition()) != null){
                            jawaban.get(getAdapterPosition()).setJawaban(soal.get(getAdapterPosition()).getJawaban().get(idx).getJawaban());
                            jawaban.get(getAdapterPosition()).setIdJawaban(soal.get(getAdapterPosition()).getJawaban().get(idx).getIdJawaban());
                            jawaban.get(getAdapterPosition()).setIdDetailSoal(soal.get(getAdapterPosition()).getJawaban().get(idx).getIdDetailSoal());
                        } else {
                            jawaban.add(new ModelInsertData.DataJawaban(soal.get(getAdapterPosition()).getJawaban().get(idx).getIdDetailSoal(),soal.get(getAdapterPosition()).getJawaban().get(idx).getIdJawaban(),soal.get(getAdapterPosition()).getJawaban().get(idx).getJawaban()));
                        }
                }
                }
            });

        }
        // Your First view holder
        // Connect with the items in the layout here
    }

    public class viewHolder2 extends RecyclerView.ViewHolder {
        TextView tvNumber;
        TextView tvEssay;
        EditText edtEssay;
         viewHolder2(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvEssay = itemView.findViewById(R.id.tv_essay);
            edtEssay = itemView.findViewById(R.id.edt_jawaban_essay);

            edtEssay.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                   if (!s.equals("")) {
//                       jawaban.get(getAdapterPosition()).setJawaban();
//                   }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (soal.get(getAdapterPosition()).getType() == ModelSoalUjianData.TYPE_ESSAY) {
                        jawaban.get(getAdapterPosition()).setJawaban(edtEssay.getText().toString());
                    }
                }
            });
        }
        // Your Second view holder
        // Connect with the items in the layout here
    }
}
