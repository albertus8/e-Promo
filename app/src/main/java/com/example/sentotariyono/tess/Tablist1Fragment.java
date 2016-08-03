package com.example.sentotariyono.tess;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sentot Ariyono on 7/11/2016.
 */
public class Tablist1Fragment extends Fragment {

    private ListView listView;
    // Variabel untuk format String JSON
    private String JSON_STRING;

    // Array of integers points to images stored in /res/drawable/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout fl = (LinearLayout) inflater.inflate(R.layout.tablist1,container,false);
        // R.layout.myList defines the layout of each item
        ///SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.mylist, from, to);
        ///final ListView ls = (ListView)fl.findViewById(R.id.listtask);
        ///ls.setAdapter(adapter);
        getJSON();
        return fl;

    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Pengambilan Data","Wait...",false,false);
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
                //String kategori = jo.getString(Config.TAG_KATEGORI);

                HashMap<String,String> mahasiswa = new HashMap<>();
                mahasiswa.put(Config.TAG_NAMA,id);
                mahasiswa.put(Config.TAG_DESKRIPSI,nama);
                //mahasiswa.put(Config.TAG_KATEGORI,kategori);
                list.add(mahasiswa);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Tampilkan datanya dalam Layout Lihat Data
        ListAdapter adapter = new SimpleAdapter(
                getContext(), list, R.layout.mylist,
                new String[]{Config.TAG_NAMA,Config.TAG_DESKRIPSI/*,Config.TAG_KATEGORI*/},
                new int[]{R.id.id, R.id.name/*R.id.kategori*/});
        // Tampilkan dalam bentuk ListView
        listView.setAdapter(adapter);
    }










}

