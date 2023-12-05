import day1.Calibration;
import day2.CubeGame;
import day3.Engine;
import day4.ScratchCard;
import day5.Almanac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) throws IOException {

    String file1 = "src/assets/inputDay1.txt";
    String file2 = "src/assets/inputDay2.txt";
    String file3 = "src/assets/inputDay3.txt";
    String file4 = "src/assets/inputDay4.txt";
    String file5 = "src/assets/inputDay5.txt";


    Map<String, Integer> colorToQuantityMap = new HashMap<>() {
      {
        put("red", 12);
        put("green", 13);
        put("blue", 14);
      }
    };

    Calibration day1 = new Calibration(new BufferedReader(new FileReader(file1)));
    CubeGame day2 = new CubeGame(colorToQuantityMap);
    Engine day3 = new Engine();
    ScratchCard day4 = new ScratchCard(new BufferedReader(new FileReader(file4)));
    Almanac day5 = new Almanac(new BufferedReader(new FileReader(file5)));


    System.out.println("Day 1, Task 1: " + day1.calibrate());
    System.out.println("Day 1, Task 2: " + day1.calibrateWithDigitsAsString());
    System.out.println("Day 2, Task 1: " + day2.getPossibleGames(new BufferedReader(new FileReader(file2))));
    System.out.println("Day 2, Task 2: " + day2.getPowerOfCubes(new BufferedReader(new FileReader(file2))));
    System.out.println("Day 3, Task 1: " + day3.getEnginePart(new BufferedReader(new FileReader(file3))));
    System.out.println("Day 4, Task 1: " + day4.getPoints());
    System.out.println("Day 4, Task 2: " + day4.getScratchCardsWon());
    System.out.println("Day 5, Task 1: " + day5.getClosestLocation());
    // System.out.println("Day 5, Task 2: " + day5.getRealClosesLocation()); //it takes a lot of time

    System.out.println("Hello world!");
  }
}