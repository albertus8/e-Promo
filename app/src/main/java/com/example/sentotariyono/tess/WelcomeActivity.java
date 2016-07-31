package com.example.sentotariyono.tess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Sentot Ariyono on 7/19/2016.
 */
public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent i = getIntent();
        final String a = i.getStringExtra("access");
        Toast.makeText(getApplicationContext(), a ,Toast.LENGTH_SHORT).show();
        Button btn_lihatpromo = (Button) findViewById(R.id.caripromo);



        Button btn_login = (Button) findViewById(R.id.login);
        Button btn_addpromo = (Button) findViewById(R.id.tambahpromo);



        if (a=="admin"){;
            btn_login.setText("LOG OUT");
            //btn_addpromo.setVisibility(View.GONE);
        }else{

            btn_login.setText("LOG IN");
            //btn_addpromo.setVisibility(View.VISIBLE);
        }
        btn_addpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                if (a.equals("admin")){


                    Intent intent = new Intent(context, AddPromoActivity.class);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "Silahkan Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_lihatpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });


    }


}
