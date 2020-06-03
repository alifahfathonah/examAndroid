package com.example.asus.skripsi;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDaftarUjian implements Parcelable{

    @SerializedName("data")
    @Expose
    private List<ModelDaftarUjianData> data = null;

    List<ModelDaftarUjianData> getData() {
        return data;
    }

    public void setData(List<ModelDaftarUjianData> data) {
        this.data = data;
    }

    private ModelDaftarUjian(Parcel in) {
        data = in.createTypedArrayList(ModelDaftarUjianData.CREATOR);
    }

    public static final Creator<ModelDaftarUjian> CREATOR = new Creator<ModelDaftarUjian>() {
        @Override
        public ModelDaftarUjian createFromParcel(Parcel in) {
            return new ModelDaftarUjian(in);
        }

        @Override
        public ModelDaftarUjian[] newArray(int size) {
            return new ModelDaftarUjian[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
    }
}
