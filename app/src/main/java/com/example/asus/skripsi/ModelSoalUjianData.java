package com.example.asus.skripsi;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSoalUjianData {
    static final int TYPE_PG = 0;
    static final int TYPE_ESSAY = 1;
    @SerializedName("id_soal")
    @Expose
    private String idSoal;
    @SerializedName("id_detail_soal")
    @Expose
    private String idDetailSoal;
    @SerializedName("soal")
    @Expose
    private String soal;
    @SerializedName("jenis_soal")
    @Expose
    private String jenisSoal;
    @SerializedName("jawaban")
    @Expose
    private List<Jawaban> jawaban = null;

    int getType(){
        if (getJenisSoal().equals("Pilihan Ganda")){
            return TYPE_PG;
        } else {
            return TYPE_ESSAY;
        }
    }

    public String getIdSoal() {
        return idSoal;
    }

    public void setIdSoal(String idSoal) {
        this.idSoal = idSoal;
    }

    String getIdDetailSoal() {
        return idDetailSoal;
    }

    public void setIdDetailSoal(String idDetailSoal) {
        this.idDetailSoal = idDetailSoal;
    }

    String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    private String getJenisSoal() {
        return jenisSoal;
    }

    public void setJenisSoal(String jenisSoal) {
        this.jenisSoal = jenisSoal;
    }

    List<Jawaban> getJawaban() {
        return jawaban;
    }

    public void setJawaban(List<Jawaban> jawaban) {
        this.jawaban = jawaban;
    }

    public class Jawaban {

        @SerializedName("id_jawaban")
        @Expose
        private String idJawaban;
        @SerializedName("id_detail_soal")
        @Expose
        private String idDetailSoal;
        @SerializedName("jawaban")
        @Expose
        private String jawaban;
        @SerializedName("nilai_jawaban")
        @Expose
        private String nilaiJawaban;

        String getIdJawaban() {
            return idJawaban;
        }

        public void setIdJawaban(String idJawaban) {
            this.idJawaban = idJawaban;
        }

        String getIdDetailSoal() {
            return idDetailSoal;
        }

        public void setIdDetailSoal(String idDetailSoal) {
            this.idDetailSoal = idDetailSoal;
        }

        String getJawaban() {
            return jawaban;
        }

        public void setJawaban(String jawaban) {
            this.jawaban = jawaban;
        }

        public String getNilaiJawaban() {
            return nilaiJawaban;
        }

        public void setNilaiJawaban(String nilaiJawaban) {
            this.nilaiJawaban = nilaiJawaban;
        }

    }
}
