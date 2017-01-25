package com.m.colourgram.colordays;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.m.colourgram.R;
import com.m.colourgram.utils.RealmHelper;
import com.m.colourgram.calendar.CalendarHelper;
import com.m.colourgram.utils.L;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * main page for color day list
 */
public class DayListFragment extends Fragment{

    private RealmHelper mRealmHelper;
    private  CalendarHelper mCalendarHelper;

    private ArrayList<ColorDay> mDayList;

    private View mView;

    private String mMonth, mYear;

    public DayListFragment(String year, String month){
        mMonth = month;
        mYear = year;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_day_list, container, false);

        ButterKnife.bind(getActivity());

        mRealmHelper= new RealmHelper(getActivity());
        mCalendarHelper  = new CalendarHelper();

        initList(mYear, mMonth);

        return mView;
    }

    public void initList(String year, String month){

        mDayList = mRealmHelper.getAllColorDay(year, month);
        L.d("day list : " + mDayList);
        DayListAdapter adapter = new DayListAdapter(getContext(),mDayList);
        ListView listView = (ListView)mView.findViewById(R.id.listview_day);
        listView.setAdapter(adapter);
        listView.setSelection(mDayList.size());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                L.d("onItemClick >> " + i);
                //mYear, mMonth, date = i+1;
            }
        });
    }



}
