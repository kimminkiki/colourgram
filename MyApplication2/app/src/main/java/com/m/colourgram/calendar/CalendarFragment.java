package com.m.colourgram.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.m.colourgram.R;
import com.m.colourgram.colordays.ColorDay;
import com.m.colourgram.utils.L;
import com.m.colourgram.utils.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Minnie on 8/18/16.
 */
public class CalendarFragment extends Fragment {

    private TextView tvDate;            //년월 텍스트뷰
    private GridView mGridView;
    private CalendarGridAdapter mGridAdapter;

    private ArrayList<ColorDay> mColorList;
    private ArrayList<String> mDayList;

    private String mYear, mMonth;

    private int mFirstDay;

    public CalendarFragment(String year, String month){
        mYear = year;
        mMonth = month;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        tvDate = (TextView) view.findViewById(R.id.title_month);


        mDayList = new ArrayList<String>();
        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        CalendarHelper calendarHelper = new CalendarHelper();
        drawCalendar(mYear, mMonth);


        //현재날짜 텍스트뷰에 뿌려줌
        tvDate.setText(calendarHelper.getMonthText(mMonth));


        //그리드 어댑터 설정
        mGridAdapter = new CalendarGridAdapter(getActivity(), mDayList, mColorList);
        mGridView = (GridView) view.findViewById(R.id.gridview);
        mGridView.setAdapter(mGridAdapter);
        mGridAdapter.notifyDataSetChanged();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                L.d("onItemClick >> "+ (i - mFirstDay +2) );
            }
        });


        return view;
    }

    private void drawCalendar(String year, String month) {

        //이번달 1일이 무슨 요일인지 판단 mCal.set(Year, Month, Day)
        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        Calendar calendar =  Calendar.getInstance();
        calendar.set(Integer.parseInt(year), (Integer.parseInt(month))-1, 1);
//        calendar.set(Integer.parseInt(year), Integer.parseInt(month), 1);
        mFirstDay = calendar.get(Calendar.DAY_OF_WEEK);
        for(int i = 1; i<mFirstDay; i++){
            mDayList.add("");
        }

//        calendar.set(Calendar.MONTH, Integer.parseInt(month));

        calendar.set(Calendar.MONTH, 1);
        L.d("Calender ... " + calendar.getMaximum(Calendar.DAY_OF_MONTH));
        for (int i = 0; i < calendar.getMaximum(Calendar.DAY_OF_MONTH); i++) {

            mDayList.add("" + (i + 1));
        }


        RealmHelper realmHelper = new RealmHelper(getContext());
        mColorList = realmHelper.getAllColorDay(mYear, mMonth);

    }
}
