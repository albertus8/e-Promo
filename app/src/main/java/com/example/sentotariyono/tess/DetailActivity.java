package com.example.sentotariyono.tess;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Sentot Ariyono on 7/11/2016.
 */
public class DetailActivity extends AppCompatActivity {
    // Set Collapsing Toolbar layout to the screen


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("aaa");

    }



    // Set title of Detail page


}
