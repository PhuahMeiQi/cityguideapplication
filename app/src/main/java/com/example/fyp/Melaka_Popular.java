package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Melaka_Popular extends AppCompatActivity
{
    ListView lvPopular;
    ArrayList<HashMap<String, String>> MPoplist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melaka__popular);

        lvPopular = findViewById(R.id.listviewInfo);
        MPoplist = new ArrayList<>();
        loadPopular("All");
    }

    private void loadPopular(final String type)
    {
        class LoadPopular extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Type",type);
                Request_Handler rh = new Request_Handler();
                MPoplist = new ArrayList<>();
                String s = rh.sendPostRequest
                        ("http://tommyworkspace.com/cityguide/melaka/load_attraction.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.e ("Jason",s);
                MPoplist.clear();
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray Poparray = jsonObject.getJSONArray("Type");
                    Log.e("Jason",jsonObject.toString());
                    for (int i=0;i<Poparray.length();i++)
                    {
                        JSONObject c = Poparray.getJSONObject(i);
                        String name = c.getString("Name");
                        String info = c.getString("Info");
                        String address = c.getString("Address");
                        String phone = c.getString("Phone");

                        HashMap<String,String> infolisthash = new HashMap<>();
                        infolisthash.put("Name",name);
                        infolisthash.put("Info",info);
                        infolisthash.put("Address",address);
                        infolisthash.put("Phone",phone);

                        MPoplist.add(infolisthash);

                    }
                }catch (final JSONException e)
                {
                    Log.e("JSONERROR",e.toString());
                }

                ListAdapter adapter = new Custom_Melaka_Popular(
                        Melaka_Popular.this, MPoplist,
                        R.layout.activity_melaka_pop_list, new String[]
                        {"Name","Info","Address","Phone"}, new int[]
                        {R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4});

                lvPopular.setAdapter(adapter);
            }

        }
        LoadPopular loadPopular = new LoadPopular();
        loadPopular.execute();
    }
}
