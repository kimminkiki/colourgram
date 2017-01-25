package com.m.colourgram.colordays;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.colourgram.R;

import java.util.ArrayList;

/**
 * main page adapter for color day list
 */
public class DayListAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    private ArrayList<ColorDay> items;

    public DayListAdapter(Context context, ArrayList<ColorDay> arrayList){
        this.items = arrayList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ColorDay getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        ColorDay item = getItem(position);
        if(view==null) {
            view = inflater.inflate(R.layout.item_list_main, viewGroup, false);
            holder = new ViewHolder();

            holder.text_day = (TextView) view.findViewById(R.id.text_day);
            holder.text_date = (TextView) view.findViewById(R.id.text_date);
            holder.text_mood = (TextView) view.findViewById(R.id.text_mood);
            holder.layout_colorday = (RelativeLayout) view.findViewById(R.id.layout_colorday);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.text_day.setText(item.getDay());
        holder.text_date.setText(item.getDate());
        holder.text_mood.setText(item.getMood());

        String color = item.getColor();
        if("".equals(color) || color == null ||"#59767C".equals(color) ){

            color = "#ECF0F1";
        }
        holder.layout_colorday.setBackgroundColor(Color.parseColor(color));
        return view;
    }

    private class ViewHolder {
        TextView text_date, text_day, text_mood;
        RelativeLayout layout_colorday;
    }
}
