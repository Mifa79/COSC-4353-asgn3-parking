import java.time.LocalTime;

public class Car {
    private String name;
    private LocalTime enter_time;


    public Car(){
        name = null;
        enter_time = null;
    }

    public Car(String car_name, LocalTime the_enter_time){
        name = car_name;
        enter_time = the_enter_time;
    }


    public void setEnter_time(LocalTime the_time) {
        enter_time = the_time;
    }

    public String getName(){
        return name;
    }

    public LocalTime getEnter_time(){
        return enter_time;
    }


}
