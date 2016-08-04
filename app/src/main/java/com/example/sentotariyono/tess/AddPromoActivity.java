package com.example.sentotariyono.tess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Sentot Ariyono on 7/23/2016.
 */
public class AddPromoActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private String filemanagerstring;
    private EditText nama,alamat,cp,desc;
    private Button buttonAdd;
    //copasan image
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri filePath;
    public static final String UPLOAD_URL = "http://192.168.1.4/epromo/upload.php";
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    private int PICK_IMAGE_REQUEST = 1;
    //end copas
    ArrayAdapter<String> adapter;
    List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_addpromo);
        nama = (EditText) findViewById(R.id.txt_namapromo);
        alamat = (EditText) findViewById(R.id.txt_alamat);
        cp = (EditText) findViewById(R.id.txt_cp);
        desc = (EditText) findViewById(R.id.txt_deskripsi);
        buttonAdd = (Button) findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                TambahData();
                uploadImage();
            }
        });
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showFileChooser();
            }
        });
        menukategori();
    }
    //copasan upload image
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //dapetin url image
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(AddPromoActivity.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(AddPromoActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                //String name = editTextName.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, "tes");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    // end copasan upload ========================

    public void menukategori(){
        Spinner itemlist =  (Spinner)findViewById(R.id.pilihkategori);


        list = new ArrayList<String>();
        list.add("Food & Beverages");
        list.add("Clothes");
        list.add("Entertainment");
        list.add("Gadget & Electronics");

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemlist.setAdapter(adapter);
    }


    /* public void onClick(View v){
         if(v == buttonAdd){
             Toast.makeText(AddPromoActivity.this, "asdsa", Toast.LENGTH_SHORT).show();
             final String TAG = AddPromoActivity.class.getSimpleName();
             Log.d(TAG,"tes");
             TambahData();
         }
     }*/
    //Adding an employee
    void TambahData(){
        Spinner spinner = (Spinner)findViewById(R.id.pilihkategori);
        final String kategoriStr = spinner.getSelectedItem().toString().trim();

        // Ubah setiap View EditText ke tipe Data String
        final String namaStr = nama.getText().toString().trim();
        final String alamatStr = alamat.getText().toString().trim();
        final String cpStr = cp.getText().toString().trim();
        final String descStr = desc.getText().toString().trim();
        // Pembuatan Class AsyncTask yang berfungsi untuk koneksi ke Database Server

        class TambahData extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddPromoActivity.this,"Proses Kirim Data...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddPromoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                // Sesuaikan bagian ini dengan field di tabel Mahasiswa
                params.put(Config.KEY_EMP_NAMA,namaStr);
                params.put(Config.KEY_EMP_KATEGORI,kategoriStr);
                params.put(Config.KEY_EMP_ALAMAT,alamatStr);
                params.put(Config.KEY_EMP_CP,cpStr);
                params.put(Config.KEY_EMP_DESKRIPSI,descStr);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }
        // Jadikan Class TambahData Sabagai Object Baru
        TambahData ae = new TambahData();
        ae.execute();
    }
}
