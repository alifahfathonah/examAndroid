package com.example.asus.skripsi;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelInsertData {
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("id_ujian")
    @Expose
    private String idUjian;
    @SerializedName("data_jawaban")
    @Expose
    private List<DataJawaban> dataJawaban = null;

    @Override
    public String toString() {
        return "ModelInsertData{" +
                "nim='" + nim + '\'' +
                ", idUjian='" + idUjian + '\'' +
                ", dataJawaban=" + dataJawaban +
                '}';
    }

    ModelInsertData(String nim, String idUjian, List<DataJawaban> dataJawaban) {
        this.nim = nim;
        this.idUjian = idUjian;
        this.dataJawaban = dataJawaban;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getIdUjian() {
        return idUjian;
    }

    public void setIdUjian(String idUjian) {
        this.idUjian = idUjian;
    }

    public List<DataJawaban> getDataJawaban() {
        return dataJawaban;
    }

    public void setDataJawaban(List<DataJawaban> dataJawaban) {
        this.dataJawaban = dataJawaban;
    }

    public static class DataJawaban {

        @Override
        public String toString() {
            return "DataJawaban{" +
                    "idDetailSoal='" + idDetailSoal + '\'' +
                    ", idJawaban='" + idJawaban + '\'' +
                    ", jawaban='" + jawaban + '\'' +
                    '}';
        }

        DataJawaban(String idDetailSoal, String idJawaban, String jawaban) {
            this.idDetailSoal = idDetailSoal;
            this.idJawaban = idJawaban;
            this.jawaban = jawaban;
        }

        @SerializedName("id_detail_soal")
        @Expose
        private String idDetailSoal;
        @SerializedName("id_jawaban")
        @Expose
        private String idJawaban;
        @SerializedName("jawaban")
        @Expose
        private String jawaban;

        public String getIdDetailSoal() {
            return idDetailSoal;
        }

        public void setIdDetailSoal(String idDetailSoal) {
            this.idDetailSoal = idDetailSoal;
        }

        public String getIdJawaban() {
            return idJawaban;
        }

        public void setIdJawaban(String idJawaban) {
            this.idJawaban = idJawaban;
        }

        public String getJawaban() {
            return jawaban;
        }

        public void setJawaban(String jawaban) {
            this.jawaban = jawaban;
        }
    }
}
