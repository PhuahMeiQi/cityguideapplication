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

public class Penang_Hotel extends AppCompatActivity
{
    ListView lvHotel;
    ArrayList<HashMap<String, String>> Acclist;
    TextView tv_one,tv_two,tv_three,tv_four,tv_five, tv_all;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penang__hotel);

        lvHotel = findViewById(R.id.listviewInfo);
        Acclist = new ArrayList<>();
        loadHotel("All");
        tv_all = findViewById(R.id.tv_all);
        tv_one = findViewById(R.id.tv_one);
        tv_two = findViewById(R.id.tv_two);
        tv_three = findViewById(R.id.tv_three);
        tv_four = findViewById(R.id.tv_four);
        tv_five = findViewById(R.id.tv_five);

        tv_all.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadHotel("All");
                Toast.makeText(getApplicationContext(),"All",Toast.LENGTH_SHORT).show();
            }
        });

        tv_one.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadHotel("1 star");
                Toast.makeText(getApplicationContext(),"1 star Hotel",Toast.LENGTH_SHORT).show();
            }
        });

        tv_two.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadHotel("2 stars");
                Toast.makeText(getApplicationContext(),"2 stars Hotel",Toast.LENGTH_SHORT).show();
            }
        });

        tv_three.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadHotel("3 stars");
                Toast.makeText(getApplicationContext(),"3 stars Hotel",Toast.LENGTH_SHORT).show();
            }
        });

        tv_four.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadHotel("4 stars");
                Toast.makeText(getApplicationContext(),"4 stars Hotel",Toast.LENGTH_SHORT).show();
            }
        });

        tv_five.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadHotel("5 stars");
                Toast.makeText(getApplicationContext(),"5 stars Hotel",Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void loadHotel(final String type)
    {
        class LoadHotel extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Type",type);
                Request_Handler rh = new Request_Handler();
                Acclist = new ArrayList<>();
                String s = rh.sendPostRequest
                        ("http://tommyworkspace.com/cityguide/load_hotel.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.e ("Jason",s);
                Acclist.clear();
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray Accarray = jsonObject.getJSONArray("Type");
                    Log.e("Jason",jsonObject.toString());
                    for (int i=0;i<Accarray.length();i++)
                    {
                        JSONObject c = Accarray.getJSONObject(i);
                        String name = c.getString("Name");
                        String address = c.getString("Address");
                        String phone = c.getString("Phone");
                        String star = c.getString("Type");
                        String id = c.getString("Id");

                        HashMap<String,String> infolisthash = new HashMap<>();
                        infolisthash.put("Name",name);
                        infolisthash.put("Address",address);
                        infolisthash.put("Phone",phone);
                        infolisthash.put("Type",star);
                        infolisthash.put("Id",id);

                        Acclist.add(infolisthash);

                    }
                }catch (final JSONException e)
                {
                    Log.e("JSONERROR",e.toString());
                }

                ListAdapter adapter = new Custom_Penang_Hotel(
                        Penang_Hotel.this, Acclist,
                        R.layout.activity_penang_hotel_list, new String[]
                        {"Name","Address","Phone","Type"}, new int[]
                        {R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4});

                lvHotel.setAdapter(adapter);
            }

        }
        LoadHotel loadHotel = new LoadHotel();
        loadHotel.execute();
    }
}
