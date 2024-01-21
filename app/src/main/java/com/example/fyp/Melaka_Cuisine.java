package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Melaka_Cuisine extends AppCompatActivity
{
    TextView tv_crb, tv_nlaksa,tv_all,tv_dn,tv_sc,tv_nk,tv_pt,tv_ap,tv_kkgm,tv_dc,tv_nc,tv_pp;
    ListView lvCuisine;
    ArrayList<HashMap<String, String>> Locallist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melaka__cuisine);

        tv_crb = findViewById(R.id.tv_ckt);
        tv_nlaksa = findViewById(R.id.tv_laksa);
        tv_sc = findViewById(R.id.tv_sc);
        tv_dn = findViewById(R.id.tv_dn);
        tv_all = findViewById(R.id.tv_all);
        tv_nk = findViewById(R.id.tv_nk);
        tv_pt = findViewById(R.id.tv_pt);
        tv_ap = findViewById(R.id.tv_ap);
        tv_kkgm = findViewById(R.id.tv_kkgm);



        tv_dc = findViewById(R.id.tv_dc);
        tv_nc = findViewById(R.id.tv_nc);
        tv_pp = findViewById(R.id.tv_pp);

        lvCuisine = findViewById(R.id.listviewInfo);
        Locallist = new ArrayList<>();
        loadCuisine("All");

        tv_all.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("All");
                Toast.makeText(getApplicationContext(),"All",Toast.LENGTH_SHORT).show();
            }
        });

        tv_crb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Chicken Rice Ball");
                Toast.makeText(getApplicationContext(),"Chicken Rice Ball",Toast.LENGTH_SHORT).show();
            }
        });

        tv_nlaksa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Nyonya Laksa");
                Toast.makeText(getApplicationContext(),"Nyonya Laksa",Toast.LENGTH_SHORT).show();
            }
        });

        tv_dn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Duck Noodles");
                Toast.makeText(getApplicationContext(),"Duck Noodles",Toast.LENGTH_SHORT).show();
            }
        });

        tv_sc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Satay Celup");
                Toast.makeText(getApplicationContext(),"Satay Celup",Toast.LENGTH_SHORT).show();
            }
        });

        tv_nk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Nyonya Kuih");
                Toast.makeText(getApplicationContext(),"Nyonya Kuih",Toast.LENGTH_SHORT).show();
            }
        });

        tv_pt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Pai Tee");
                Toast.makeText(getApplicationContext(),"Pai Tee",Toast.LENGTH_SHORT).show();
            }
        });

        tv_ap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Ayam Pongteh");
                Toast.makeText(getApplicationContext(),"Ayam Pongteh",Toast.LENGTH_SHORT).show();
            }
        });

        tv_kkgm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Kuih Keria Gula Melaka");
                Toast.makeText(getApplicationContext(),"Kuih Keria Gula Melaka",Toast.LENGTH_SHORT).show();
            }
        });

        tv_dc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Durian Cendol");
                Toast.makeText(getApplicationContext(),"Durian Cendol",Toast.LENGTH_SHORT).show();
            }
        });

        tv_nc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Nyonya Cendol");
                Toast.makeText(getApplicationContext(),"Nyonya Cendol",Toast.LENGTH_SHORT).show();
            }
        });

        tv_pp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Putu Piring");
                Toast.makeText(getApplicationContext(),"Putu Piring",Toast.LENGTH_SHORT).show();
            }
        });













    }

    private void loadCuisine(final String type)
    {
        class LoadCuisine extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Type",type);
                Request_Handler rh = new Request_Handler();
                Locallist = new ArrayList<>();
                String s = rh.sendPostRequest
                        ("http://tommyworkspace.com/cityguide/melaka/load_local.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.e ("Jason",s);
                Locallist.clear();
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray Localarray = jsonObject.getJSONArray("Type");
                    Log.e("Jason",jsonObject.toString());
                    for (int i=0;i<Localarray.length();i++)
                    {
                        JSONObject c = Localarray.getJSONObject(i);
                        String id = c.getString("Id");
                        String name = c.getString("Name");
                        String address = c.getString("Address");
                        String phone = c.getString("Phone");
                        String halal = c.getString("Halal");

                        HashMap<String,String> infolisthash = new HashMap<>();
                        infolisthash.put("Id",id);
                        infolisthash.put("Name",name);
                        infolisthash.put("Address",address);
                        infolisthash.put("Phone",phone);
                        infolisthash.put("Halal",halal);

                        Locallist.add(infolisthash);

                    }
                }catch (final JSONException e)
                {
                    Log.e("JSONERROR",e.toString());
                }

                ListAdapter adapter = new Custom_Melaka_Cuisine(
                        Melaka_Cuisine.this, Locallist,
                        R.layout.activity_penang_cuisine_list, new String[]
                        {"Name","Address","Phone","Halal"}, new int[]
                        {R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4});

                lvCuisine.setAdapter(adapter);
            }

        }
        LoadCuisine loadCuisine = new LoadCuisine();
        loadCuisine.execute();
    }


}
