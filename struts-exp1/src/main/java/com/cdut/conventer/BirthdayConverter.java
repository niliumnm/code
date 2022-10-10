package com.cdut.conventer;

import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class BirthdayConverter extends StrutsTypeConverter {
    private static final DateFormat[] ACCEPT_DATA_FORMAT = {
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyy/MM/dd"),
            new SimpleDateFormat("yyyy.MM.dd"),
            new SimpleDateFormat("yyyy*MM*dd"),
    };
    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        String dataString = (String) strings[0];
        for (DateFormat format : ACCEPT_DATA_FORMAT) {
            try {
                return format.parse(dataString);
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    @Override
    public String convertToString(Map map, Object o) {
        return new SimpleDateFormat("yyyy-MM-dd").format(o);
    }
}
