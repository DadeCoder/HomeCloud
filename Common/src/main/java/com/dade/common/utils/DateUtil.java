package com.dade.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dade on 2017/3/23.
 */
public class DateUtil {

    public static Date getTomorrow(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getYesterday(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static Date addOneDay(Date last){
        Calendar c = Calendar.getInstance();
        c.setTime(last);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

//    public static void main(String[] args) {
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH");
////        System.out.println(sf.format(DateUtil.getTomorrow()));
////        System.out.println(sf.format(DateUtil.addOneDay(new Date())));
//
////        System.out.println(new Date().getTime());
////        System.out.println(getTomorrow().getTime());   // getTime() 越大，时间越晚
//
////        System.out.println(new Date().before(getTomorrow()));
////        System.out.println(new Date().after(getTomorrow()));
//
////        System.out.println(sf.format(getYesterday()));
//        System.out.println(getYesterday().getTime());
//
//    }

}
