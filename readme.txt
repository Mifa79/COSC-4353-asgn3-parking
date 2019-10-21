This program can model any parking garage with the following required information: capacity, number of entrances/exits, and price per parking hour.

## The input text file:
- The first line of the input file contains 3 ordered numbers: capacity, number of entrances/exits, and price per parking hour.
  Example: 10 2 2.5
  10 is the parking capacity, number of entrances = number of exits = 2, parking rate = $2.5/hour
- For the rest of the input file, each line has the following format:
  8:00 arrive: Car1#1 Car2#2        or        8:10 exit: Car1#2
  8:00 is the time the car(s) arrives/exit; Car1 and Car2 are the car names; #1 and #2 are the entrance/exit number that the car(s) chose.
  If the number of entrances/exits is n, at a certain time (8:00 for example), there are maximum n cars that can arrive/exit at the same time (8:00).
  If at a certain time (8:00 for example), there are both cars arrive and exit, the exit line will be put above the arrive line.


## The output of the program: 
   The program will print to screen the actions of the customer cars and the garage accordingly.
- If the parking is NOT full, car arrives at entrance number n will get a time-stamped ticket, then entrance n opens, and the car enters the parking right away.
- Car exits at exit gate number n will present its ticket, pay their parking bill. Exit gate n then opened, and the car exits right away.
- If the parking is FULL, car arrives at entrance number n will have to wait there until spot is available (when another car successfully exits the parking).
- If there are multiple cars waiting at the entrance gates, "first comes first served" will apply. If waiting cars happen to have the same arrive time, but only one spot is available, one of those waiting cars will be admitted.


## How to run the program:
- The program can be run by an IDE that supports Java language.
- Run the program in an IDE, then when being asked, enter the text file name that you want to test (only 1 or 2/3/4/5). 
- The screen output can be compared with the results I put in the text files in the "test output" folder.