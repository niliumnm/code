package com.cdut.chapter03.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    public static String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}
