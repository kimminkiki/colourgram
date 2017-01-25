package com.m.colourgram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.m.colourgram.calendar.CalendarFragment;
import com.m.colourgram.calendar.CalendarHelper;
import com.m.colourgram.colordays.AddDayActivity;
import com.m.colourgram.colordays.ColorDay;
import com.m.colourgram.colordays.DayListFragment;
import com.m.colourgram.utils.L;
import com.m.colourgram.utils.RealmHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Minnie on 08/12/2016.
 */
public class MainActivity extends FragmentActivity {

    private Fragment mFragment;

    public final static String FRAGMENT_DAY_LIST = "FRAGMENT_DAY_LIST";
    public final static String FRAGMENT_CALENDAR = "FRAGMENT_CALENDAR";

    public String mMonth, mYear;
    public FragmentTabHost mTabHost;
    public CalendarHelper calendarHelper;

    @BindView(R.id.text_add) TextView textAdd;
    @BindView(R.id.text_info) TextView textInformation;
    @BindView(R.id.text_month) TextView textMonth;
    @BindView(R.id.text_year) TextView textYear;
    @BindView(R.id.change_type) TextView changeScreen;
    @BindView(R.id.grid_menu) GridView monthView;

    private boolean isVisibleMonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        calendarHelper = new CalendarHelper();

        mMonth = calendarHelper.getCurrentMonth();
        mYear = calendarHelper.getCurrentYear();
        L.d("mMonth [" + mMonth + "]");
        textMonth.setText(calendarHelper.getMonthText(mMonth));
        textYear.setText(calendarHelper.getCurrentYear());

        String[] month = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

        GridAdapter adapter = new GridAdapter(getApplicationContext(), month);
        monthView.setAdapter(adapter);
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                L.d("onItemClick [" + i + "]");
                setFragment(Integer.toString(i+1));
                mMonth = Integer.toString(i+1);
                textMonth.setText(calendarHelper.getMonthText(mMonth));
            }
        });

        mFragment =  new CalendarFragment(mYear, mMonth);
        switchFragment(new CalendarFragment(mYear, mMonth));

    }

    public void setFragment(String month){
        L.d("fragment : " + mFragment.getClass());

        if(mFragment instanceof DayListFragment){
            switchFragment(new DayListFragment(mYear, month));

        } else if(mFragment instanceof CalendarFragment){
            switchFragment(new CalendarFragment(mYear, month));
        }
    }

    public void switchFragment(Fragment fragment){
        L.d("fragment : " + fragment.getClass());

        if(isVisibleMonth) {
            monthView.setVisibility(View.GONE);
            isVisibleMonth = false;
        }

        mFragment = fragment;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();

    }


    @OnClick(R.id.text_month)
    public void ShowMonthMenu(){
        if(isVisibleMonth){
            monthView.setVisibility(View.GONE);
            isVisibleMonth = false;
        } else {
            monthView.setVisibility(View.VISIBLE);
            isVisibleMonth = true;
        }
    }


    @OnClick(R.id.text_add)
    public void AddDay(){

        RealmHelper realmHelper = new RealmHelper(this);
        ColorDay today = realmHelper.getToday();

        Intent intent = new Intent(this, AddDayActivity.class);

        if(today != null) {
            intent.putExtra("mood", today.getMood());
        }

        startActivity(intent);
    }

    @OnClick(R.id.text_info)
    public void Information(){
        startActivity(new Intent(this, InformationActivity.class));
    }

    @OnClick(R.id.change_type)
    public void setChangeScreen(){

        if(mFragment instanceof DayListFragment){
            switchFragment(new CalendarFragment(mYear, mMonth));
        } else if(mFragment instanceof CalendarFragment){
            switchFragment(new DayListFragment(mYear, mMonth));
        }
    }

    class GridAdapter extends BaseAdapter {
        String[] mData;
        Context mContext;
        LayoutInflater inflater;
        public GridAdapter(Context context, String[] strings) {
            mContext = context;
            mData = strings;
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mData.length;
        }

        @Override
        public Object getItem(int i) {
            return mData[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)  {
            if (view==null)
                view = inflater.inflate(android.R.layout.simple_list_item_1, null);

            TextView tv = (TextView) view.findViewById(android.R.id.text1);
            tv.setText(getItem(i).toString());
            return view;
        }
    }

}
