package com.m.colourgram.utils;

import android.content.Context;

import com.m.colourgram.calendar.CalendarHelper;
import com.m.colourgram.colordays.ColorDay;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Minnie on 6/30/16.
 */
public class RealmHelper {
    private Context mContext;
    private RealmConfiguration mRealmConfiguration;
    private Realm mRealm;
    private CalendarHelper calendarHelper;

    public RealmHelper(Context context){
        mContext = context;
        calendarHelper = new CalendarHelper();
        mRealmConfiguration = new RealmConfiguration
                .Builder(context).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(mRealmConfiguration);
        mRealm = Realm.getDefaultInstance();
    }

    public void init(){
        mRealm.beginTransaction();

        //이번달 저장된 컬러데이 가져오기
        RealmResults<ColorDay> colorDayList = mRealm.where(ColorDay.class).equalTo("year", calendarHelper.getCurrentYear())
                .equalTo("month", calendarHelper.getCurrentMonth()).findAll();


        int currentDate = Integer.parseInt(calendarHelper.getCurrentDate());
        int lastDate = 1;
        int size = colorDayList.size();

        //저장된 데이터가 있고
        //마지막 날짜를 어제로 설정
        if(size != 0){
            lastDate =  Integer.parseInt((colorDayList.get(size-1)).getDate());
        } else {
            lastDate = 0;
        }


        //오늘날짜의 컬러데이
        ColorDay colorDay = mRealm.where(ColorDay.class).equalTo("year", calendarHelper.getCurrentYear())
                .equalTo("month", calendarHelper.getCurrentMonth())
                .equalTo("date", calendarHelper.getCurrentDate()).findFirst();

        if(size != currentDate ){//길이 체크
            L.e("size ( "+size+" ) and date ( "+currentDate+" ) are different  ");
            if(lastDate+1 != currentDate ){ //마지막 날짜랑 어제랑 다르면
                L.e("lastDate ( "+lastDate+" ) and date ( "+(currentDate-1)+" ) are different  ");
                for(int i = lastDate+1 ; i < currentDate; i++){
                    L.e("add days ["+i+"]");
                    colorDay = mRealm.createObject(ColorDay.class); // Create managed objects directly
                    colorDay.setColor("");
                    colorDay.setMood("");
                    colorDay.setYear(calendarHelper.getCurrentYear());
                    colorDay.setMonth(calendarHelper.getCurrentMonth());
                    if( i < 10 ){
                        colorDay.setDate("0"+Integer.toString(i));
                    } else {
                        colorDay.setDate(Integer.toString(i));

                    }
                    colorDay.setDay(calendarHelper.getCurrentDayOfWeek(calendarHelper.getCurrentYear(), calendarHelper.getCurrentMonth(), Integer.toString(i)));
                }
            }
            else {
                //마지막 날짜가 어제이면 글쓸때 하루 생성하면 됩니다
            }
        }

        mRealm.commitTransaction();
    }
    public void saveColorDay(String color, String mood){
        mRealm.beginTransaction();

        ColorDay colorDay = mRealm.where(ColorDay.class).equalTo("year", calendarHelper.getCurrentYear())
                .equalTo("month", calendarHelper.getCurrentMonth())
                .equalTo("date", calendarHelper.getCurrentDate()).findFirst();


        if(colorDay != null){
            colorDay.setColor(color);
            colorDay.setMood(mood);

        } else {

            colorDay = mRealm.createObject(ColorDay.class); // Create managed objects directly
            colorDay.setColor(color);
            colorDay.setMood(mood);
            colorDay.setYear(calendarHelper.getCurrentYear());
            colorDay.setMonth(calendarHelper.getCurrentMonth());
            colorDay.setDate(calendarHelper.getCurrentDate());
            colorDay.setDay(calendarHelper.getCurrentDayOfWeek(calendarHelper.getCurrentYear(), calendarHelper.getCurrentMonth(), calendarHelper.getCurrentDate()));
        }


        mRealm.commitTransaction();
    }

    public void setDefaultColorDay(String year, String month, String date){
        mRealm.beginTransaction();

        ColorDay colorDay = mRealm.createObject(ColorDay.class); // Create managed objects directly
        colorDay.setYear(year);
        colorDay.setMonth(month);
        colorDay.setDate(date);
        colorDay.setColor("#ECF0F1");
        colorDay.setMood("");
        mRealm.commitTransaction();
    }


    public ArrayList<ColorDay> getAllColorDay(String year, String month){
        ArrayList<ColorDay> arrayResult = new ArrayList<>();
        mRealm.beginTransaction();
        RealmResults<ColorDay> realmResults = mRealm.where(ColorDay.class)
                .equalTo("year", year)
                .equalTo("month", month).findAll();
        mRealm.commitTransaction();

        for(int i = 0; i< realmResults.size(); i++){

                arrayResult.add(realmResults.get(i));
        }
        L.d("LAST :::: " + realmResults);
        if(realmResults== null){

            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year), Integer.parseInt(month), 1);
            int last= calendar.getMaximum(Calendar.DAY_OF_MONTH);

            L.d("LAST :::: " + last);
        }

        return arrayResult;
    }

    public ArrayList<ColorDay> getColorDaysForMonth(String year, String month){
        ArrayList<ColorDay> arrayResult = new ArrayList<>();
        mRealm.beginTransaction();
        RealmResults<ColorDay> realmResults = mRealm.where(ColorDay.class)
                .equalTo("year", year)
                .equalTo("month", month).findAll();

        L.d("get color days >> year " + year +", month " + month);
        L.d("get color days >> " + realmResults.toString());
        mRealm.commitTransaction();
        for(int i = 0; i< realmResults.size(); i++){
            arrayResult.add(realmResults.get(i));
        }


        return arrayResult;
    }


    public ColorDay getToday(){
        mRealm.beginTransaction();
        ColorDay result = mRealm.where(ColorDay.class).equalTo("year", calendarHelper.getCurrentYear())
                .equalTo("month", calendarHelper.getCurrentMonth())
                .equalTo("date", calendarHelper.getCurrentDate()).findFirst();
        mRealm.commitTransaction();

        return result;
    }



}
