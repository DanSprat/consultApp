package Listeners;

import DB.DataBase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static DB.DataBase.INSTANCE;

@WebListener()
public class InitListener implements ServletContextListener {

    private static class ThreadInit extends Thread {
        private static ThreadInit threadInit;
        private static  long SEVEN_DAYS = 604_800_000;
        private ThreadInit(){

        }
        public static synchronized ThreadInit getInstance(){
            if (threadInit == null){
                threadInit = new ThreadInit();
            }
            return threadInit;
        }

        @Override
        public void run() {
            ZonedDateTime currentZDT;
            int currentDay= ZonedDateTime.now(ZoneId.systemDefault()).getDayOfMonth();
            int nextDay;
            long currentMillis;
            boolean is = false;
            while (!interrupted()){
                currentZDT =  ZonedDateTime.now(ZoneId.systemDefault());
                nextDay = currentZDT.getDayOfMonth();
                if (nextDay != currentDay){
                    currentDay = nextDay;
                    currentZDT = ZonedDateTime.of(currentZDT.getYear(),currentZDT.getMonthValue(),currentZDT.getDayOfMonth(), currentZDT.getHour(), currentZDT.getMinute(),0,0,ZoneId.systemDefault());
                    currentMillis = currentZDT.toInstant().toEpochMilli();
                    List<DataBase.Schedule.Value> scheduleList = INSTANCE.schedule.getAll();
                    List<DataBase.Consultations.Consultation> consultationList = INSTANCE.consultations.getAll();
                    for (DataBase.Consultations.Consultation consultation: consultationList){
                        if (consultation.student == null || consultation.start < currentMillis) {
                            try {
                                INSTANCE.consultations.remove(new DataBase.Consultations.Key(consultation.mentor,consultation.start));
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                    for (var elem:scheduleList){
                        try {
                            int plusDays = (currentZDT.getDayOfWeek().getValue() - elem.day_of_week) % 7;
                            long plusMillis = plusDays * 24 * 60 * 60 * 1000 + elem.start;
                            INSTANCE.consultations.put(new DataBase.Consultations.Consultation(elem.mentor,currentMillis + plusMillis,elem.duration,null,""));
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ThreadInit.getInstance().start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ThreadInit.getInstance().interrupt();
    }


}
