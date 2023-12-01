import day1.Calibration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {

    String file1 = "src/assets/inputDay1.txt";
    Calibration day1 = new Calibration(new BufferedReader(new FileReader(file1)));
    System.out.println("Day 1, Task 1: " + day1.calibrate());
    System.out.println("Day 1, Task 2: " + day1.calibrateWithDigitsAsString());


    System.out.println("Hello world!");
  }
}