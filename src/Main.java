import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.time.LocalTime;


public class Main {
    public static void main (String[] args){
        String line;
        Scanner fileIn = null;
        String fileName = null;
        int theCapacity = 0, theEntranceNum = 0, theExitNum = 0;
        double thePricePerHour = 0;

        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the text file name you want to test (just 1 or 2/3/4/5): ");
            fileName = keyboard.next() + ".txt";

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
        }


        try {
            fileIn = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Test file could not be found or opened.");
            System.exit(0);
        }

        String firstLine = fileIn.nextLine();
        String garageInfo[] = firstLine.split(" ");
        theCapacity = Integer.valueOf(garageInfo[0]);
        theEntranceNum = Integer.valueOf(garageInfo[1]);
        theExitNum = theEntranceNum;
        thePricePerHour = Double.valueOf(garageInfo[2]);
        Garage theGarage = new Garage(theCapacity, theEntranceNum, theExitNum, thePricePerHour);


        while (fileIn.hasNextLine()) {
            line = fileIn.nextLine();
            String Words[] = line.split(" ");

            if (line.contains("arrive:")){
                String arriveTimeStr = Words[0];
                String arriveTimeComponent[] = arriveTimeStr.split(":");
                int arriveHour = Integer.valueOf(arriveTimeComponent[0]);
                int arriveMin = Integer.valueOf(arriveTimeComponent[1]);
                LocalTime arriveTime = LocalTime.of(arriveHour, arriveMin);

                int index = 2;

                for (int i = 2; i < Words.length; i++) {
                    if (!theGarage.isFull()){
                        String carInfo = Words[i];
                        String carInfoComponent[] = carInfo.split("#");
                        String carName = carInfoComponent[0];
                        int EntranceNum = Integer.valueOf(carInfoComponent[1]);
//                        System.out.println("test entrance1 " + carInfoComponent[1]);

                        Car theCar = new Car(carName, arriveTime);
                        theGarage.admitCar(theCar, EntranceNum);
                        index = index + 1;

                    } else {
                        break;
                    }
                }

                if (index <= Words.length-1){
                    for (int i = index; i < Words.length; i++) {
                        String carInfo = Words[i];
                        String carInfoComponent[] = carInfo.split("#");
                        String carName = carInfoComponent[0];
                        int EntranceNum = Integer.valueOf(carInfoComponent[1]);
//                        System.out.println("test entrance2 " + carInfoComponent[1]);

                        WaitCar theCar = new WaitCar(carName, arriveTime, EntranceNum);
                        theGarage.addWaitCar(theCar);
                    }
                }
            }

            if (line.contains("exit:")){
                String exitTimeStr = Words[0];
                String exitTimeComponent[] = exitTimeStr.split(":");
                int exitHour = Integer.valueOf(exitTimeComponent[0]);
                int exitMin = Integer.valueOf(exitTimeComponent[1]);
                LocalTime exitTime = LocalTime.of(exitHour, exitMin);

                for (int i = 2; i < Words.length; i++) {
                    String carInfo = Words[i];
                    String carInfoComponent[] = carInfo.split("#");
                    String carName = carInfoComponent[0];
                    int ExitNum = Integer.valueOf(carInfoComponent[1]);

                    theGarage.exitCar(carName, ExitNum, exitTime);
                }
            }

        }
    }
}
