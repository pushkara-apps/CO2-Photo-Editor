package com.gabmus.co2photoeditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomFXAdapter extends BaseAdapter{
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomFXAdapter(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        if (MainActivity.FX.FXList[position].fxActive)
            rowView = inflater.inflate(R.layout.effect_list_item_active, null);
        else
            rowView = inflater.inflate(R.layout.effect_list_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textViewListItem);
        holder.img=(ImageView) rowView.findViewById(R.id.imageViewListItem);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        return rowView;
    }

}