package com.example.asus.skripsi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDaftarUjianData implements Parcelable {
    @SerializedName("id_ujian")
    @Expose
    private String idUjian;

    @SerializedName("id_soal")
    @Expose
    private String idSoal;

    @SerializedName("kd_matkul")
    @Expose
    private String kdMatkul;

    @SerializedName("nama_matkul")
    @Expose
    private String namaMatkul;

    @SerializedName("tgl_ujian")
    @Expose
    private String tglUjian;

    @SerializedName("waktu_mulai_ujian")
    @Expose
    private String waktuMulaiUjian;

    @SerializedName("waktu_selesai_ujian")
    @Expose
    private String waktuSelesaiUjian;

    @SerializedName("token_ujian")
    @Expose
    private String tokenUjian;

    private ModelDaftarUjianData(Parcel in) {
        idUjian = in.readString();
        idSoal = in.readString();
        kdMatkul = in.readString();
        namaMatkul = in.readString();
        tglUjian = in.readString();
        waktuMulaiUjian = in.readString();
        waktuSelesaiUjian = in.readString();
        tokenUjian = in.readString();
    }

    public static final Creator<ModelDaftarUjianData> CREATOR = new Creator<ModelDaftarUjianData>() {
        @Override
        public ModelDaftarUjianData createFromParcel(Parcel in) {
            return new ModelDaftarUjianData(in);
        }

        @Override
        public ModelDaftarUjianData[] newArray(int size) {
            return new ModelDaftarUjianData[size];
        }
    };

    public String getIdUjian() {
        return idUjian;
    }

    public void setIdUjian(String idUjian) {
        this.idUjian = idUjian;
    }

    public String getIdSoal() {
        return idSoal;
    }

    public void setIdSoal(String idSoal) {
        this.idSoal = idSoal;
    }

    public String getKdMatkul() {
        return kdMatkul;
    }

    public void setKdMatkul(String kdMatkul) {
        this.kdMatkul = kdMatkul;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getTglUjian() {
        return tglUjian;
    }

    public void setTglUjian(String tglUjian) {
        this.tglUjian = tglUjian;
    }

    public String getWaktuMulaiUjian() {
        return waktuMulaiUjian;
    }

    public void setWaktuMulaiUjian(String waktuMulaiUjian) {
        this.waktuMulaiUjian = waktuMulaiUjian;
    }

    public String getWaktuSelesaiUjian() {
        return waktuSelesaiUjian;
    }

    public void setWaktuSelesaiUjian(String waktuSelesaiUjian) {
        this.waktuSelesaiUjian = waktuSelesaiUjian;
    }

    public String getTokenUjian(){
        return tokenUjian;
    }

    public void setTokenUjian(String tokenUjian) {
        this.tokenUjian = tokenUjian;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUjian);
        dest.writeString(idSoal);
        dest.writeString(kdMatkul);
        dest.writeString(namaMatkul);
        dest.writeString(tglUjian);
        dest.writeString(waktuMulaiUjian);
        dest.writeString(waktuSelesaiUjian);
        dest.writeString(tokenUjian);
    }
}
