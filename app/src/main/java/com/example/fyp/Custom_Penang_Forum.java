package com.example.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Custom_Penang_Forum extends SimpleAdapter
{
    private Context mContext;
    public LayoutInflater inflater=null;

    public Custom_Penang_Forum(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
    {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi=convertView;
        try
        {
            if(convertView==null)
                vi = inflater.inflate(R.layout.activity_penang_read_forum, null);
            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView tvtitle = vi.findViewById(R.id.textView);
            TextView tvname = vi.findViewById(R.id.textView2);
            TextView tvcontent = vi.findViewById(R.id.textView3);

            String dname = (String)data.get("Name");
            String dtitle = (String)data.get("Title");
            String dcontent = (String)data.get("Content");

            tvname.setText(dname);
            System.out.println();
            tvtitle.setText(dtitle);
            System.out.println();
            tvcontent.setText(dcontent);

        }

        catch (IndexOutOfBoundsException e)
        {

        }

        return vi;
    }
}

