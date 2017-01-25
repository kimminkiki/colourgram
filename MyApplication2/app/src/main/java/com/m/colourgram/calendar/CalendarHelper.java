package com.m.colourgram.calendar;

import com.m.colourgram.utils.L;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Minnie on 8/25/16.
 */
public class CalendarHelper {

    private Date mDate;

    public CalendarHelper(){

        //이번달 1일이 무슨 요일인지 판단 mCal.set(Year, Month, Day)
        long now = System.currentTimeMillis();
        mDate = new Date(now);

//        calendar.set(Integer.parseInt(curYearFormat.format(mDate)), Integer.parseInt(curMonthFormat.format(mDate))-1, 1);
    }

    public String getCurrentYear(){
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        return curYearFormat.format(mDate);

    }

    public String getCurrentMonth(){
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        return curMonthFormat.format(mDate);
    }

    public String getMonthText(String MM){
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);

        String month = "";
        switch (MM) {
            case "01":
            case "1":
                month="JANUARY";
                break;
            case "02":
            case "2":
                month="FEBRUARY";
                break;
            case "03":
            case "3":
                month="MARCH";
                break;
            case "04":
            case "4":
                month="APRIL";
                break;
            case "05":
            case "5":
                month="MAY";
                break;
            case "06":
            case "6":
                month="JUNE";
                break;
            case "07":
            case "7":
                month="JULY";
                break;
            case "08":
            case "8":
                month="AUGUST";
                break;
            case "09":
            case "9":
                month="SEPTEMBER";
                break;
            case "10":
                month="OCTOBER";
                break;
            case "11":
                month="NOVEMBER";
                break;
            case "12":
                month="DECEMBER";
                break;
        }
        return month;
    }

    public String getCurrentDate(){
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
        L.e(curDayFormat.format(mDate));
        return curDayFormat.format(mDate);
    }

    public String getCurrentDayOfWeek(String yyyy, String MM, String dd){
        Calendar cal = Calendar.getInstance();

        cal.set(Integer.parseInt(yyyy),(Integer.parseInt(MM))-1,Integer.parseInt(dd));
        String strweek = null;
        L.e("getCurrentDayOfWeek : " + cal.getTime());

        int mWeek = cal.get(Calendar.DAY_OF_WEEK);
        L.e("cal.get(Calendar.DAY_OF_WEEK) : " +mWeek);

        switch(mWeek){
            case 1:
                strweek = "SUN";
                break;
            case 2:
                strweek = "MON";
                break;
            case 3:
                strweek = "TUE";
                break;
            case 4:
                strweek = "WED";
                break;
            case 5:
                strweek = "THU";
                break;
            case 6:
                strweek = "FRI";
                break;
            case 7:
                strweek = "SAT";
                break;
        }
        return strweek;
    }



}
