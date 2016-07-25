package com.example.sentotariyono.tess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Sentot Ariyono on 7/11/2016.
 */
public class Tablist1Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tablist1, null);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycle_view, container, false);

        ContentAdapter adapter = new ContentAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        // Set padding
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        return recyclerView;


    }





    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageButton btn_del;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent ){
            super(inflater.inflate(R.layout.tablist1, parent, false));

            btn_del = (ImageButton)itemView.findViewById(R.id.delete_button);
            btn_del.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View vv) {
                    Snackbar.make(vv, "HELLO WORLD", Snackbar.LENGTH_INDEFINITE).show();
                }
            });
            //untuk activity_detail.xml
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent);
                }
            });


        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;

        public ContentAdapter(){
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        public void onBindViewHolder(ViewHolder holder, int position){
            // no-op
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }




    }

