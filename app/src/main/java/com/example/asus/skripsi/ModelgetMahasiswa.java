package com.example.asus.skripsi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelgetMahasiswa {

    @SerializedName("nim")
    @Expose
    private String nim;

    @SerializedName("nama_mhs")
    @Expose
    private String namaMhs;

    @SerializedName("nama_prodi")
    @Expose
    private String namaProdi;

    @SerializedName("semester")
    @Expose
    private String semester;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public String getNamaProdi() {
        return namaProdi;
    }

    public void setNamaProdi(String namaProdi) {
        this.namaProdi = namaProdi;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
