package date;

public enum DayOfWeek {

    SUNDAY ("Воскресенье"),
    MONDAY ("Понедельник"),
    TUESDAY ("Вторник"),
    WEDNESDAY ("Среда"),
    THURSDAY ("Четверг"),
    FRIDAY ("Пятница"),
    SATURDAY ("Суббота");

    private String title;

    DayOfWeek(String title) {
        this.title = title;

    }

    @Override
    public String toString() {
        return title;
    }
}
