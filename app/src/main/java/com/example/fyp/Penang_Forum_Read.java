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

public class Penang_Forum_Read extends AppCompatActivity
{
    ListView lvBlog;
    ArrayList<HashMap<String, String>> Bloglist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penang__forum__read);

        lvBlog = findViewById(R.id.listviewInfo);

        Bloglist = new ArrayList<>();
        loadBlog("All");

    }

    private void loadBlog(final String Name)
    {
        class LoadBlog extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Name",Name);
                Request_Handler rh = new Request_Handler();
                Bloglist = new ArrayList<>();
                String s = rh.sendPostRequest
                        ("http://tommyworkspace.com/testing/load_blog.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.e ("Jason",s);
                Bloglist.clear();
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray Poparray = jsonObject.getJSONArray("Name");
                    Log.e("Jason",jsonObject.toString());
                    for (int i=0;i<Poparray.length();i++)
                    {
                        JSONObject c = Poparray.getJSONObject(i);
                        String name = c.getString("Name");
                        String title = c.getString("Title");
                        String content = c.getString("Content");

                        HashMap<String,String> infolisthash = new HashMap<>();
                        infolisthash.put("Name",name);
                        infolisthash.put("Title",title);
                        infolisthash.put("Content",content);

                        Bloglist.add(infolisthash);

                    }
                }catch (final JSONException e)
                {
                    Log.e("JSONERROR",e.toString());
                }

                ListAdapter adapter = new Custom_Penang_Forum(
                        Penang_Forum_Read.this, Bloglist,
                        R.layout.activity_penang_read_forum, new String[]
                        {"Name","Title","Content"}, new int[]
                        {R.id.textView,R.id.textView2,R.id.textView3});

                lvBlog.setAdapter(adapter);
            }

        }
        LoadBlog loadBlog = new LoadBlog();
        loadBlog.execute();
    }
}
