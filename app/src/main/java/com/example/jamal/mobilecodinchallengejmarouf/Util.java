package com.example.jamal.mobilecodinchallengejmarouf;

import android.nfc.Tag;
import android.util.Log;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static Date date = new Date();
    static Calendar calendar = Calendar.getInstance();
    static long diffMillis = ((date.getTime())-259200000);
    static int mYear,mMonth,mDay;
    static final String  Tag="Util";
    static String targetDate;

    public static String  getTargetDate(){
        for(int i=1;i<9;i++){
            diffMillis-=259200000;
        }

        calendar.setTimeInMillis(diffMillis);
        targetDate=dateFormat.format(calendar.getTime());
        Log.v(Tag,targetDate);
        return targetDate;


    }

}
