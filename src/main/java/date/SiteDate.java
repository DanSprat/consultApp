package date;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.Objects;

public class SiteDate implements Comparable <SiteDate>{
    private final static String months [] = {"января","февраля","марта","апреля","мая","июня","июля","августа","сентября","октября","ноября","декабря"};
    private final static String DOWs [] = {"Понедельник","Вторник","Среда","Четверг","Пятница","Суббота","Воскресенье"};

    public static String getDOW(int num){
        return DOWs[num];
    }
    private int dayOfWeek;
    private int day;
    private int month;
    private long millis;

    public SiteDate(int dayOfWeek,int day,int month,long millis){
        this.dayOfWeek=dayOfWeek;
        this.day=day;
        this.month=month;
        this.millis = millis;
    }

    public SiteDate(long millis){
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis),ZoneId.systemDefault());
        dayOfWeek = zonedDateTime.getDayOfWeek().getValue();
        day = zonedDateTime.getDayOfMonth();
        month = zonedDateTime.getMonthValue();
        this.millis = millis;

    }
    public SiteDate(){
    }

    public void setDate(long millis){
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
        dayOfWeek = zonedDateTime.getDayOfWeek().getValue();
        day =  zonedDateTime.getDayOfMonth();
        month = zonedDateTime.getMonthValue();
        this.millis = millis;
    }


    @Override
    public String toString() {
        return DOWs[dayOfWeek-1] +", "+day+" "+months[month-1];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() !=obj.getClass()) return false;
        SiteDate siteDate = (SiteDate) obj;
        return this.day == siteDate.day && this.month == siteDate.month && this.dayOfWeek == siteDate.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, day, month);
    }

    @Override
    public int compareTo(SiteDate o) {
       return Long.compare(this.millis,o.millis);
    }
}
