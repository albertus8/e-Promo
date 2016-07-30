package com.example.sentotariyono.tess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sentot Ariyono on 7/11/2016.
 */
public class Tablist1Fragment extends Fragment {
    String[] title = new String[] {
            "CJR",
            "Burger King",
            "KFC",
            "MC Donald",
            "Pizza Hut"

    };

    // Array of integers points to images stored in /res/drawable/
    int[] flags = new int[]{
            R.drawable.promocjr,
            R.drawable.promoburgerking,
            R.drawable.promokfc,
            R.drawable.promomcd,
            R.drawable.promopizzahut
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout fl = (LinearLayout) inflater.inflate(R.layout.tablist1,container,false);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<title.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", "Country : " + title[i]);

            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }

        //--Keys used in Hashmap
        String[] from = { "flag","txt" };

        //--Ids of views in listview_layout
        int[] to = { R.id.img_icon,R.id.txt_title};

        //--Instantiating an adapter to store each items

        // R.layout.myList defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.mylist, from, to);
        final ListView ls = (ListView)fl.findViewById(R.id.listtask);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String ss = ls.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), title[i], Toast.LENGTH_SHORT).show();
                //Context context = view.getContext();
                //Intent intent = new Intent(context, DetailActivity.class);
                //context.startActivity(intent);
            }
        });
        return fl;

    }









    }

