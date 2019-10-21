import java.time.LocalTime;

public class WaitCar {
    private String name;
    private LocalTime arriveTime;
    private int entranceNum;

    public WaitCar(){
        name = null;
        arriveTime = null;
        entranceNum = -1;
    }

    public WaitCar(String car_name, LocalTime the_arriveTime, int the_entranceNum){
        name = car_name;
        arriveTime = the_arriveTime;
        entranceNum = the_entranceNum;
    }

    public String getCarName(){
        return name;
    }

    public LocalTime getArriveTime(){
        return arriveTime;
    }

    public int getEntranceNum(){
        return entranceNum;
    }
}
