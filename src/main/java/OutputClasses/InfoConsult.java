package OutputClasses;

import DB.DataBase;

public class InfoConsult {
    public  String mentor;
    public  String mentor_name;
    public  long start;
    public  long duration;
    public  String student;
    public  String student_name;
    public  String comment;

    public InfoConsult(String mentor, String mentor_name, long start, long duration, String student, String student_name, String comment) {
        this.mentor = mentor;
        this.mentor_name = mentor_name;
        this.start = start;
        this.duration = duration;
        this.student = student;
        this.student_name = student_name;
        this.comment = comment;
    }
    public InfoConsult(DataBase.Consultations.Consultation consultation){
        this.mentor = consultation.mentor;
        this.mentor_name = DataBase.INSTANCE.users.findKey(mentor).name;
        this.start=consultation.start;
        this.student = consultation.student;
        if (consultation.student !=null){
            DataBase.Users.User user = DataBase.INSTANCE.users.findKey(student);
            if (user!=null)
                this.student_name = user.name;
        }

        this.comment = consultation.comment;
        this.start = consultation.start;
        this.duration = consultation.duration;
    }

    public String getMentor() {
        return mentor;
    }

    public String getMentor_name() {
        return mentor_name;
    }

    public long getStart() {
        return start;
    }

    public long getDuration() {
        return duration;
    }

    public String getStudent() {
        return student;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getComment() {
        return comment;
    }
}
