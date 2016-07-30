package com.example.sentotariyono.tess;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sentot Ariyono on 7/11/2016.
 */
public class Tablist2Fragment extends Fragment {
    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tablist2, null);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycle_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);



        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return recyclerView;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;

        public ImageView picture;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent ){
            super(inflater.inflate(R.layout.tablist2, parent, false));
            title = (TextView)itemView.findViewById(R.id.tile_title);

            picture = (ImageView)itemView.findViewById(R.id.tile_picture);
            //untuk activity_detail.xml
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, SearchActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 4;
        private String[] mTitle;
        private String[] mLittleDescription;
        private Drawable[] pict;
        public ContentAdapter(Context context){
            Resources resources = context.getResources();
            mTitle = resources.getStringArray(R.array.title_kategori);

            TypedArray a = resources.obtainTypedArray(R.array.pict_kategori);
            pict = new Drawable[a.length()];
            for (int i = 0; i<pict.length; i++){
                pict[i] = a.getDrawable(i);
            }

            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        public void onBindViewHolder(ViewHolder holder, int position){
            // no-op
            holder.picture.setImageDrawable(pict[position % pict.length]);
            holder.title.setText(mTitle[position % mTitle.length]);

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
