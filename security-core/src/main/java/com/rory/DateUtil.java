package com.rory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtil {
    public static String getNow(){
        Date date=new Date();
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       String str=sdf.format(date);
       return str;
    }
}
