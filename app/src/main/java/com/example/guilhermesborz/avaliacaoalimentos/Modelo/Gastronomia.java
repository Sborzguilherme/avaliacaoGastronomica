package com.example.guilhermesborz.avaliacaoalimentos.Modelo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Micro Solution on 07/12/2016.
 */

public class Gastronomia implements Parcelable {

    private Float notaRatingBar;
    private String descricao;
    private String categoria;
    private Bitmap imagemCapturada;
    private double latitude;
    private double longitude;

    public Gastronomia(){

    }

    public Gastronomia(Parcel in) {
        setDescricao(in.readString());
        setCategoria(in.readString());
        setImagemCapturada((Bitmap) in.readParcelable(Bitmap.class.getClassLoader()));
        setNotaRatingBar(in.readFloat());
    }

    public static final Creator<Gastronomia> CREATOR = new Creator<Gastronomia>() {
        @Override
        public Gastronomia createFromParcel(Parcel in) {
            return new Gastronomia(in);
        }

        @Override
        public Gastronomia[] newArray(int size) {
            return new Gastronomia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getDescricao());
        dest.writeString(getCategoria());
        dest.writeParcelable(getImagemCapturada(), flags);
        dest.writeFloat(getNotaRatingBar());
    }

    public Float getNotaRatingBar() {
        return notaRatingBar;
    }

    public void setNotaRatingBar(Float notaRatingBar) {
        this.notaRatingBar = notaRatingBar;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Bitmap getImagemCapturada() {
        return imagemCapturada;
    }

    public void setImagemCapturada(Bitmap imagemCapturada) {
        this.imagemCapturada = imagemCapturada;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
