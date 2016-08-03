package com.example.sentotariyono.tess;

/**
 * Created by Windows10 on 20/03/2016.
 */
public class Config {
    //192.168.1.103
    //Alamat URL tempat kita meletakkan script PHP di PC Server
    // Link untuk INSERT Data

    public static final String URL_ADD="http://192.168.1.4/epromo/create.php";
    public static final String URL_GET_ALL = "http://192.168.1.4/epromo/read.php";
    public static final String URL_GET_ID="http://192.168.1.4/epromo/get_data.php?id=";
    // Link untuk Update data
    //public static final String URL_UPDATE_EMP="http://192.168.1.105:81/epromo/update.php";
    // Link Untuk Hapus Data
    //public static final String URL_DELETE_EMP="http://192.168.1.105:81/epromo/delete.php?id=";

    // Filed yang digunakan untuk dikirimkan ke Database, sesuaikan saja dengan Field di Tabel Mahasiswa
    public static final String KEY_EMP_ID = "nomor";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_KATEGORI = "kategori";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_CP = "kontak";
    public static final String KEY_EMP_DESKRIPSI = "deskripsi";
    public static final String KEY_EMP_GAMBAR = "gambar";
    // Tags Format JSON
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "nomor";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_KATEGORI = "kategori";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_CP = "kontak";
    public static final String TAG_DESKRIPSI = "deskripsi";
    public static final String TAG_GAMBAR = "gambar";

    public static final String URL_ID = "emp_id";

}