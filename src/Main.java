import day1.Calibration;
import day2.CubeGame;
import day3.Engine;

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


    System.out.println("Day 1, Task 1: " + day1.calibrate());
    System.out.println("Day 1, Task 2: " + day1.calibrateWithDigitsAsString());
    System.out.println("Day 2, Task 1: " + day2.getPossibleGames(new BufferedReader(new FileReader(file2))));
    System.out.println("Day 2, Task 2: " + day2.getPowerOfCubes(new BufferedReader(new FileReader(file2))));
    System.out.println("Day 3, Task 1: " + day3.getEnginePart(new BufferedReader(new FileReader(file3))));


    System.out.println("Hello world!");
  }
}