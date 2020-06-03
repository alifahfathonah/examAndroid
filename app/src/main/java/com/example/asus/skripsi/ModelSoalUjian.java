package com.example.asus.skripsi;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSoalUjian {
    @SerializedName("waktu_sekarang")
    @Expose
    private String waktuSekarang;
    @SerializedName("waktu_mulai")
    @Expose
    private String waktuMulai;

    @SerializedName("waktu_selesai")
    @Expose
    private String waktuSelesai;

    @SerializedName("data")
    @Expose
    private List<ModelSoalUjianData> data = null;

    List<ModelSoalUjianData> getData() {
        return data;
    }

    public void setData(List<ModelSoalUjianData> data) {
        this.data = data;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public String getWaktuSekarang() {
        return waktuSekarang;
    }

    public void setWaktuSekarang(String waktuSekarang) {
        this.waktuSekarang = waktuSekarang;
    }
}
