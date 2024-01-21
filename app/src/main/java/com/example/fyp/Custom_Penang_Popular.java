package com.example.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Custom_Penang_Popular extends SimpleAdapter
{
    private Context mContext;
    public LayoutInflater inflater=null;

    public Custom_Penang_Popular(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
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
                vi = inflater.inflate(R.layout.activity_penang_pop_list, null);
            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView tvname = vi.findViewById(R.id.textView);
            TextView tvinfo = vi.findViewById(R.id.textView2);
            TextView tvadd = vi.findViewById(R.id.textView3);
            TextView tvphone = vi.findViewById(R.id.textView4);

            CircleImageView imgrest =vi.findViewById(R.id.imageView2);

            String dname = (String)data.get("Name");
            String dinfo = (String)data.get("Info");
            String dadd = (String)data.get("Address");
            String dphone =(String) data.get("Phone");

            tvname.setText(dname);
            System.out.println();
            tvinfo.setText(dinfo);
            System.out.println();
            tvadd.setText("Address: "+dadd);
            tvphone.setText("Phone: "+dphone);

            String image_url = "http://tommyworkspace.com/cityguide/images/PenangAttractions/"+dname+".jpg";
            Picasso.get().load(image_url).networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(300,200).centerCrop().into(imgrest);

        }

        catch (IndexOutOfBoundsException e)
        {

        }

        return vi;
    }
}
