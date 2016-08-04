package com.example.sentotariyono.tess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Windows10 on 27/03/2016.
 */
public class LihatData extends AppCompatActivity implements ListView.OnItemClickListener {

    // Definisikan ListView
    private ListView listView;
    // Variabel untuk format String JSON
    private String JSON_STRING;

    ArrayAdapter<String> adapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        // Inisialiasi ListView
        listView = (ListView) findViewById(R.id.listView);
        // berikan event klik disini
        listView.setOnItemClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("e-Promo");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        //   Method GetJSON
        getJSON();
        menukategori();
    }


    public void menukategori(){
        Spinner itemlist =  (Spinner)findViewById(R.id.pilihkategoria);


        list = new ArrayList<String>();
        list.add("Food & Beverages");
        list.add("Clothes");
        list.add("Entertainment");
        list.add("Gadget & Electronics");

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemlist.setAdapter(adapter);
    }

    // Buat Methode untuk ambil data dari Server
    private void TampilData(){
        // Data dalam bentuk Array kemudian akan kita ubah menjadi JSON Object
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            // FOR untuk ambil data
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                // TAG_ID dan TAG_NAME adalah variabel yang ada di Class Config.java,
                String id = jo.getString(Config.TAG_NAMA);
                String nama = jo.getString(Config.TAG_DESKRIPSI);
                String kategori = jo.getString(Config.TAG_KATEGORI);
               //String gambar = jo.getString(Config.TAG_GAMBAR);
                HashMap<String,String> mahasiswa = new HashMap<>();
                mahasiswa.put(Config.TAG_NAMA,id);
                mahasiswa.put(Config.TAG_DESKRIPSI,nama);
                mahasiswa.put(Config.TAG_KATEGORI,kategori);
                //mahasiswa.put(Config.TAG_GAMBAR, gambar);
                list.add(mahasiswa);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Tampilkan datanya dalam Layout Lihat Data
        ListAdapter adapter = new SimpleAdapter(
                LihatData.this, list, R.layout.lihatdata,
                new String[]{Config.TAG_NAMA,Config.TAG_DESKRIPSI,Config.TAG_KATEGORI},
                new int[]{R.id.id, R.id.name,R.id.desc});
        // Tampilkan dalam bentuk ListView
        listView.setAdapter(adapter);
    }

    // Methode ambil data JSON yang kita definisikan dalam bentuk AsyncTask
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatData.this,"Pengambilan Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                // Panggil method tampil data
                TampilData();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                // Proses nya sesuai alamat URL letak script PHP yang kita set di Class Config.java
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // menggunakan Intent untuk pemmanggilan class ViewData
        Intent intent = new Intent(this, ViewData.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        // Kita akan ambil data berdasarkan ID, TAG_ID ini berada di class Config.java
        String empId = map.get(Config.TAG_NAMA).toString();
        intent.putExtra(Config.URL_ID,empId);
        // Tampilkan class ViewData
        startActivity(intent);
    }
}
