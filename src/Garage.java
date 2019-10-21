import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;

public class Garage {
    private int capacity;
    private int entranceNum;
    private int exitNum;
    private double pricePerHour;
    private ArrayList<Entrance> entranceList = new ArrayList<Entrance>();
    private ArrayList<Exit> exitList = new ArrayList<Exit>();
    private ArrayList<Car> carList = new ArrayList<Car>();
    private ArrayList<WaitCar> waitCarList = new ArrayList<WaitCar>();


    public Garage(){
        capacity = -1;
        entranceNum = -1;
        exitNum = -1;
        pricePerHour = -1;
    }

    public Garage(int the_capacity, int the_entranceNum, int the_exitNum, double thePrice){
        capacity = the_capacity;
        entranceNum = the_entranceNum;
        exitNum = the_exitNum;
        pricePerHour = thePrice;
        createEntrance(the_entranceNum);
        createExit(the_exitNum);
    }

    private ArrayList<Entrance> createEntrance(int the_entranceNum){
        for (int i = 0; i < the_entranceNum; i++){
            int entranceNum = i + 1;
            String entranceName = "Entrance_" + entranceNum;
            Entrance Entrance = new Entrance(entranceName);
            entranceList.add(Entrance);
        }
        return entranceList;
    }

    private ArrayList<Exit> createExit(int the_exitNum){
        for (int i = 0; i < the_exitNum; i++){
            int exitNum = i + 1;
            String exitName = "Exit_" + exitNum;
            Exit Exit = new Exit(exitName);
            exitList.add(Exit);
        }
        return exitList;
    }

    public void admitCar(Car the_car, int the_entranceNum){
        entranceList.get(the_entranceNum-1).open(the_car);
        carList.add(the_car);
    }

    public void exitCar(String the_car, int the_exitNum, LocalTime the_Exit_Time){
        billCar(the_car, the_Exit_Time);
        exitList.get(the_exitNum-1).open(the_car, the_Exit_Time);

        for (int i = 0; i < carList.size(); i++){
            if (carList.get(i).getName().equals(the_car)){
                carList.remove(carList.get(i));
//                System.out.println("size: " + carList.size());
            }
        }

        if (waitCarList.size() != 0){
            int index = 0;
            LocalTime temp = waitCarList.get(0).getArriveTime();
            for (int i = 1; i < waitCarList.size(); i++){
                if (waitCarList.get(i).getArriveTime().isBefore(temp)){
                    temp = waitCarList.get(i).getArriveTime();
                    index = i;
                }
            }

            Car nowEnterCar = new Car(waitCarList.get(index).getCarName(), the_Exit_Time);
            admitCar(nowEnterCar, waitCarList.get(index).getEntranceNum());
            waitCarList.remove(waitCarList.get(index));
        }
    }

    public boolean isFull(){
        if (carList.size() == capacity){
            return true;
        } else {
            return false;
        }
    }


    public void billCar(String the_car, LocalTime the_ExitTime){
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getName().equals(the_car)) {
                LocalTime enterTime = carList.get(i).getEnter_time();
                Long totalMin = enterTime.until(the_ExitTime, MINUTES);
                double totalHour = totalMin/60.0000;
                String totalHourStr = String.format("%.2f", totalHour);
                double payAmount = totalHour * pricePerHour;
                String payAmountStr = String.format("%.2f", payAmount);
                System.out.print("At " + the_ExitTime + ", "  + the_car +  " presents their ticket and pays " + payAmountStr +
                        " dollars for " + totalHourStr + " hours parking. ");
            }
        }
    }

    public void addWaitCar(WaitCar theCar){
        waitCarList.add(theCar);
        System.out.println("Parking Garage is full, " + theCar.getCarName() + " has just arrived at " + theCar.getArriveTime() +
                " and is now waiting at entrance_" + theCar.getEntranceNum() + " until spot is available.");
    }

}
