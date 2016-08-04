package com.example.sentotariyono.tess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Windows10 on 27/03/2016.
 */
public class ViewData extends AppCompatActivity{
    // Inisialiasasi View
    private EditText editTextId,editTextNama,editTextKategori,editTextAlamat,editTextKontak,editTextDeskripsi;
    private ImageView imageViewGambar;
    // Inisialisasi Button
    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdata);
        Intent intent = getIntent();
        // ID
        id = intent.getStringExtra(Config.URL_ID);
        // Inisialisasi View dan Button
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextKategori = (EditText) findViewById(R.id.editTextKategori);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextKontak = (EditText) findViewById(R.id.editTextKontak);
        editTextDeskripsi = (EditText) findViewById(R.id.editTextDeskripsi);

        imageViewGambar = (ImageView) findViewById(R.id.gambarIV);

        // Tampilkan ID pada EditText ID
        editTextId.setText(id);
// Panggil Methood GetData
        getData();
    }

    // Buat Method GetData untuk ambil data diserver
    private void getData(){
        class getData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewData.this,"Proses Data...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                // Method Show Data
                ShowData(s);
                //loadImageFromURL("http://10.10.31.228/epromo/image/53.png",imageViewGambar);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ID,id);
                return s;
                //try {
                //    URL url = new URL("http://10.10.31.228/epromo/image/53.png");
                 //   Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    imageViewGambar.setImageBitmap(bmp);
                //}catch(Exception e){
                 //   Log.e("error",e.toString());
                //};

            }
        }
        getData ge = new getData();
        ge.execute();
    }

       // Method ShowData untuk tampilkan data pada setiap EditText
    private void ShowData(String json){
        try {
            // Jadikan sebagai JSON object
            Log.i("tagconvertstr", "["+json+"]");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            // Data berdasarkan di Tabel Database
            //String idd = c.getString(Config.TAG_ID);
            String nama = c.getString(Config.TAG_NAMA);
            String kategori = c.getString(Config.TAG_KATEGORI);
            String alamat = c.getString(Config.TAG_ALAMAT);
            String cp = c.getString(Config.TAG_CP);
            String deskripsi = c.getString(Config.TAG_DESKRIPSI);
            int gambar = getResources().getIdentifier(Config.TAG_GAMBAR, null, null);
// Tampilkan setiap data JSON format kedalam setiap EditText
            //editTextId.setText(idd);
            imageViewGambar.setImageResource(gambar);
            editTextNama.setText(nama);
            editTextKategori.setText(kategori);
            editTextAlamat.setText(alamat);
            editTextKontak.setText(cp);
            editTextDeskripsi.setText(deskripsi);
            //original
            //ORIGINALl

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
