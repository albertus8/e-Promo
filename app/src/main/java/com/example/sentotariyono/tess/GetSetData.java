package com.example.sentotariyono.tess;

import java.util.Date;

/**
 * Created by albertus on 22/07/2016.
 */
public class GetSetData {
    private String idPromo;
    private String namaPromo;
    private String kategoriPromo;
    private String deskripsiPromo;
    private Date tanggalPembuatan;
    private String urlDataImage;

    public GetSetData(String idPromo,String namaPromo,String kategoriPromo, String deskripsiPromo, Date tanggalPembuatan, String urlDataImage)
    {
        this.idPromo=idPromo;
        this.namaPromo=namaPromo;
        this.kategoriPromo=kategoriPromo;
        this.deskripsiPromo=deskripsiPromo;
        this.tanggalPembuatan=tanggalPembuatan;
        this.urlDataImage=urlDataImage;
    }

    public String getDeskripsiPromo() {
        return deskripsiPromo;
    }

    public void setDeskripsiPromo(String deskripsiPromo) {
        this.deskripsiPromo = deskripsiPromo;
    }

    public String getKategoriPromo() {
        return kategoriPromo;
    }

    public void setKategoriPromo(String kategoriPromo) {
        this.kategoriPromo = kategoriPromo;
    }

    public String getNamaPromo() {
        return namaPromo;
    }

    public void setNamaPromo(String namaPromo) {
        this.namaPromo = namaPromo;
    }

    public Date getTanggalPembuatan() {
        return tanggalPembuatan;
    }

    public void setTanggalPembuatan(Date tanggalPembuatan) {
        this.tanggalPembuatan = tanggalPembuatan;
    }

    public String getUrlDataImage() {
        return urlDataImage;
    }

    public void setUrlDataImage(String urlDataImage) {
        this.urlDataImage = urlDataImage;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }
}
