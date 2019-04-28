package com.example.happenings;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class clubs extends Activity {
    GridView mygrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apple);
        mygrid=findViewById(R.id.clubgrid);
        mygrid.setAdapter(new clubadapter(clubs.this));

    }
}

class Clubpojo
{
    int clubimage;
    String clubname;
    Clubpojo(int clubimage,String clubname)
    {
        this.clubimage=clubimage;
        this.clubname=clubname;

    }
}

class ViewHOlder
{
    ImageView clubimage;
    TextView clubname;
    ViewHOlder(View v)
    {
        clubimage=v.findViewById(R.id.clubpic);
        clubname=v.findViewById(R.id.clubname);
    }
}


class clubadapter extends BaseAdapter {

    Context context;
    ArrayList<Clubpojo> list=new ArrayList<>();
    clubadapter(Context context)
    {
        this.context=context;
        String[] clubnames={"IEEE","ISTE","IRIS","ROTARACT"};
        int[] clubimages={R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};
        for(int i=0;i<4;i++)
        {
            list.add(new Clubpojo(clubimages[i],clubnames[i]));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        ViewHOlder holder;
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row= inflater.inflate(R.layout.singlelayout,parent,false);
            holder=new ViewHOlder(row);
            row.setTag(holder);
        }
        else{
            row=convertView;
            holder= (ViewHOlder) row.getTag();
        }
        holder.clubimage.setImageResource(list.get(position).clubimage);
        holder.clubname.setText(list.get(position).clubname);



        return row;
    }
}