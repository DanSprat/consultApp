package date;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    private ZonedDateTime date;

    DateTimeFormatter dateTimeFormatter;
    ZonedDateTime localDateTime;
    public DateFormat(){

    }

    public void setDate(long millis){
       date = Instant.ofEpochMilli(millis).atZone(ZoneId.of("Europe/Moscow"));
       localDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis),ZoneOffset.of("+00:00"));
       dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, d LLLL").withLocale(new Locale("RU"));
    }


    @Override
    public String toString() {
        return date.format(dateTimeFormatter);
    }

    public String getTime(){
        String time="";
        if (date.getHour()<10) time+="0";
        time+=date.getHour()+":";
        if (date.getMinute()<10) time+="0";
        time+=date.getMinute();
        return time;
    }

    public String getLocaleTime() {
        String time="";
        if (localDateTime.getHour()<10) time+="0";
        time+=localDateTime.getHour()+":";
        if (localDateTime.getMinute()<10) time+="0";
        time+=localDateTime.getMinute();
        return time;
    }
    public static String timeToString(int h,int m){
        String time = "";
        if (h<10) time+="0";
        time+=h+":";
        if (m<10) time+="0";
        time+=m;
        return time;
    }
}





