package com.m.colourgram.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.colourgram.R;
import com.m.colourgram.colordays.ColorDay;
import com.m.colourgram.utils.L;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Minnie on 8/25/16.
 */
public class CalendarGridAdapter extends BaseAdapter {
    private final List<String> list;
    private final LayoutInflater inflater;
    private Calendar calendar;
    private Context context;
    private ArrayList<ColorDay> colorDays;

    public CalendarGridAdapter(Context context, List<String> list, ArrayList<ColorDay> colorDays){
        this.context = context;
        this.list = list;
        this.colorDays = colorDays;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return position;
    }

    @Override
    public View getView(int position, android.view.View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
            holder = new ViewHolder();

            holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.tv_item_back);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


       String sToday = String.valueOf(getItem(position));

        holder.tvItemGridView.setText(""+sToday);
        L.d("calendar date : " + sToday);
        if(!"".equals(sToday)){
            int iToday = Integer.parseInt(sToday);

         if(colorDays.size() >= iToday) {
             ColorDay item = colorDays.get(iToday-1);
             L.d("date : " + item.getDate());

             if (!"".equals(item.getColor()) && item.getColor() != null) {
                 holder.tvItemGridView.setTextColor(Color.BLACK);
                 holder.relativeLayout.setBackgroundColor(Color.parseColor(item.getColor()));
             }
         }
        }


        return convertView;
    }

    private class ViewHolder{
        TextView tvItemGridView;
        RelativeLayout relativeLayout;
    }
}
