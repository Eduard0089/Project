
import java.time.LocalTime;

public class Timer {
    private final LocalTime creationTime = LocalTime.now();
    public LocalTime getTime(){
        return creationTime;
    }
}
