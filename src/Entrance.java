public class Entrance {
    private String entranceName;

    public Entrance(String the_entranceNum){
        entranceName = the_entranceNum;
    }

    public void open(Car the_car){
        System.out.println(the_car.getName() + " gets a " + the_car.getEnter_time() + " time-stamped ticket, " + entranceName +
                " opens, " + the_car.getName() + " enters the garage.");
    }
}
