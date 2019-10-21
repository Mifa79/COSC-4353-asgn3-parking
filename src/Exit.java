import java.time.LocalTime;

public class Exit {
    private String exitName;

    public Exit(String the_exitNum){
        exitName = the_exitNum;
    }

    public void open(String the_car, LocalTime the_ExitTime){
        System.out.println(exitName + " opens, " + the_car + " exits at " + the_ExitTime);
    }
}
