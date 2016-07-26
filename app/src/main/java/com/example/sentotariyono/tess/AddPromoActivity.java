package com.example.sentotariyono.tess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sentot Ariyono on 7/23/2016.
 */
public class AddPromoActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1;
    private static final int PICK_FILE_REQUEST = 1;
    private String selectedImagePath;
    private String filemanagerstring;
    private String selectedFilePath;

    ImageView imageView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_addpromo);
        menukategori();
        chooseImage();
    }

    public void menukategori(){
        Spinner itemlist =  (Spinner)findViewById(R.id.pilihkategori);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Food & Beverages");
        list.add("Clothes");
        list.add("Entertainment");
        list.add("Gadget & Electronics");

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemlist.setAdapter(adapter);
    }

    public void chooseImage(){
        ImageView img = (ImageView)findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
//                Intent intent = new Intent();
//                intent.setType("images/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), SELECT_PICTURE);
            }
        });
//        showFileChooser();
    }

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FILE_REQUEST);
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        TextView txtt = (TextView) findViewById(R.id.text_img);
        if (resultCode== RESULT_OK){
            if (requestCode == SELECT_PICTURE){
                if(data == null){
//                    no data present
                    return;
                }

                Uri selectedFileUri = data.getData();
                selectedFilePath = selectedFileUri.getPath();

                Bitmap bmp = BitmapFactory.decodeFile(selectedFilePath);
                imageView.setImageBitmap(bmp);

//                Log.i(TAG , "Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    txtt.setText(selectedFilePath);
                }else{
                    Toast.makeText(this,"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }

//                Uri selectedImageUri = data.getData();
//                //OI FILE Manager
//                filemanagerstring = selectedImageUri.getPath();
//
//                //MEDIA GALLERY
//                selectedImagePath = getPath(selectedImageUri);
//                txtt.setText(filemanagerstring);
            }
        }
    }

    public String getPath (Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor!=null){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);

        }else{
            return null;
        }
    }

    public void tambahPromoAction(View v){
        if(filemanagerstring != null ){


            try {

            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else{
            Toast.makeText(AddPromoActivity.this,"There's no selected file",Toast.LENGTH_SHORT).show();
        }
    }

    public class AddItem extends AsyncTask<String,Void,String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            pd = new ProgressDialog(AddPromoActivity.this);
            pd.setTitle("Loading");
            pd.setMessage("Adding new item...");
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
//            return null;
            String readStream = "";

            try
            {
                URL urlx = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) urlx.openConnection();
                readStream = readStream(con.getInputStream());

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return readStream;
        }

        private String readStream(InputStream in) {
            //return null;
            StringBuilder sb = new StringBuilder();
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
