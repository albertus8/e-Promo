package com.example.sentotariyono.tess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sentot Ariyono on 7/15/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText usr;
    private EditText pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usr = (EditText)findViewById(R.id.txtuser);
        pass = (EditText)findViewById(R.id.txtpassword);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button btn = (Button) findViewById(R.id.btnLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("e-Promo");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (usr.getText().toString().equals("admin") && pass.getText().toString().equals("admin")){




                    Context context = v.getContext();
                    Intent intent = new Intent(context, WelcomeActivity.class);
                    intent.putExtra("access", "admin");
                    context.startActivity(intent);
                }else{
                    Context context = v.getContext();
                    Toast.makeText(context,"Invalid Username/Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
