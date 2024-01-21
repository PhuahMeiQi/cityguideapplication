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

public class Penang_Cuisine extends AppCompatActivity
{
    TextView tv_ckt, tv_laksa,tv_dkc,tv_crm,tv_hpm,tv_apm,tv_lbk,tv_lrm,tv_tcc,tv_psr,tv_all;
    ListView lvCuisine;
    ArrayList<HashMap<String, String>> Locallist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penang__cuisine);

        tv_ckt = findViewById(R.id.tv_ckt);
        tv_laksa = findViewById(R.id.tv_laksa);
        tv_all = findViewById(R.id.tv_all);
        tv_dkc = findViewById(R.id.tv_dkc);
        tv_crm = findViewById(R.id.tv_crm);
        tv_hpm = findViewById(R.id.tv_hpm);
        tv_apm = findViewById(R.id.tv_apm);
        tv_lbk = findViewById(R.id.tv_lbk);
        tv_lrm = findViewById(R.id.tv_lrm);
        tv_tcc = findViewById(R.id.tv_tcc);
        tv_psr = findViewById(R.id.tv_psr);

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

        tv_ckt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Char Koay Teow");
                Toast.makeText(getApplicationContext(),"Char Koay Teow",Toast.LENGTH_SHORT).show();
            }
        });

        tv_laksa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Laksa");
                Toast.makeText(getApplicationContext(),"Asam Laksa",Toast.LENGTH_SHORT).show();
            }
        });

        tv_dkc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Duck Koay Chap");
                Toast.makeText(getApplicationContext(),"Duck Koay Chap",Toast.LENGTH_SHORT).show();
            }
        });

        tv_crm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Curry Mee");
                Toast.makeText(getApplicationContext(),"Curry Mee",Toast.LENGTH_SHORT).show();
            }
        });

        tv_hpm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Hokkien Prawn Mee");
                Toast.makeText(getApplicationContext(),"Hokkien Mee",Toast.LENGTH_SHORT).show();
            }
        });

        tv_apm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Apom Manis");
                Toast.makeText(getApplicationContext(),"Apom Manis",Toast.LENGTH_SHORT).show();
            }
        });

        tv_lbk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Lor Bak");
                Toast.makeText(getApplicationContext(),"Lor Bak",Toast.LENGTH_SHORT).show();
            }
        });

        tv_lrm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Lor Mee");
                Toast.makeText(getApplicationContext(),"Lor Mee",Toast.LENGTH_SHORT).show();
            }
        });

        tv_tcc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Teochew Chendul");
                Toast.makeText(getApplicationContext(),"Teochew Chendol",Toast.LENGTH_SHORT).show();
            }
        });

        tv_psr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadCuisine("Pasembur");
                Toast.makeText(getApplicationContext(),"Pasembur",Toast.LENGTH_SHORT).show();
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
                        ("http://tommyworkspace.com/cityguide/load_local.php",hashMap);
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

                ListAdapter adapter = new Custom_Penang_Cuisine(
                        Penang_Cuisine.this, Locallist,
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
