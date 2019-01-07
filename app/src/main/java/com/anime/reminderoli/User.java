package com.anime.reminderoli;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Parcelable{
    String id;
    String nama;
    String pass;
    String email;
    String noPol;
    String foto;
    String kmSekarang;
    String kmService;
    String status;


    protected User(Parcel in) {
        id = in.readString();
        nama = in.readString();
        pass = in.readString();
        email = in.readString();
        noPol = in.readString();
        foto = in.readString();
        kmSekarang = in.readString();
        kmService = in.readString();
        status = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    User(JSONObject obj) {
        try {
            String id = obj.getString("id_user");
            String nama = obj.getString("userName");
            String pass = obj.getString("password");
            String email = obj.getString("email");
            String noPol = obj.getString("noPol");
            String foto = obj.getString("foto");
            String kmSekarang = obj.getString("kmAwal");
            String kmService = obj.getString("kmAkhir");
            String status = obj.getString("status");

            this.id = id;
            this.nama = nama;
            this.pass = pass;
            this.email = email;
            this.noPol = noPol;
            this.foto = foto;

            this.kmSekarang = kmSekarang;
            this.kmService = kmService;
            this.status = status;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoPol() {
        return noPol;
    }

    public void setNoPol(String noPol) {
        this.noPol = noPol;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKmSekarang() {
        return kmSekarang;
    }

    public void setKmSekarang(String kmSekarang) {
        this.kmSekarang = kmSekarang;
    }

    public String getKmService() {
        return kmService;
    }

    public void setKmService(String kmService) {
        this.kmService = kmService;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(pass);
        dest.writeString(email);
        dest.writeString(noPol);
        dest.writeString(foto);
        dest.writeString(kmSekarang);
        dest.writeString(kmService);
        dest.writeString(status);
    }
}
