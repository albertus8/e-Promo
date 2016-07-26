package com.example.sentotariyono.tess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sentot Ariyono on 7/27/2016.
 */
public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button btn = (Button) findViewById(R.id.btnLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Register");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtUsername = (EditText) findViewById(R.id.txtuser);
                EditText edtPassword = (EditText) findViewById(R.id.txtpassword);
                EditText edtConfPassword = (EditText) findViewById(R.id.txtconfirmpassword);

                // check dulu, user ada di dalam db atau tidak
                if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("") || edtConfPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Mohon isi terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().toLowerCase().equals(edtConfPassword.getText().toString().toLowerCase())) {
                    Toast.makeText(RegisterActivity.this, "Berhasil Terdaftar", Toast.LENGTH_SHORT).show();
//                      Context context = v.getContext();
//                      Intent intent = new Intent(context, MainActivity.class);
//                      context.startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Password dan Confirm Password tidak cocok", Toast.LENGTH_SHORT).show();
                    edtPassword.setText("");
                    edtConfPassword.setText("");
                    edtPassword.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(edtPassword, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
    }
}
