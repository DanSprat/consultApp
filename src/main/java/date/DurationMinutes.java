package date;

public class DurationMinutes {

    private long millis;

    public DurationMinutes(){

    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public int getDuration(){
        return (int)(millis / 1000 /60);
    }
}
