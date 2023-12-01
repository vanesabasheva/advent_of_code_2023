package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class Calibration {
  private final List<String> numbers = new ArrayList<>() {
    {
      add("zero");
      add("one");
      add("two");
      add("three");
      add("four");
      add("five");
      add("six");
      add("seven");
      add("eight");
      add("nine");
      add("0");
      add("1");
      add("2");
      add("3");
      add("4");
      add("5");
      add("6");
      add("7");
      add("8");
      add("9");
    }
  };

  private final Map<String, String> stringToDigitMap = new HashMap<>() {
    {
      put("zero", "0");
      put("one", "1");
      put("two", "2");
      put("three", "3");
      put("four", "4");
      put("five", "5");
      put("six", "6");
      put("seven", "7");
      put("eight", "8");
      put("nine", "9");
    }

  };

  private SortedMap<Integer, String> sortedByIndex = new TreeMap<>();
  BufferedReader reader;

  public Calibration(BufferedReader reader) {
    this.reader = reader;
  }

  public int calibrateWithDigitsAsString() throws IOException {
    int sum = 0;
    String line = reader.readLine();

    while (line != null) {

      // iterate through all possible digits (nine)
      for (String integer : numbers) {

        // check whether the current digit is present in the line
        if (line.contains(integer)) {
          int firstIndex = line.indexOf(integer);
          sortedByIndex.put(firstIndex, integer);

          int lastIndex = line.lastIndexOf(integer);
          if(lastIndex != firstIndex) {
            // store the index of the occurring number in a sorted tree
            sortedByIndex.put(lastIndex, integer);
          }
        }
      }

      // get the smallest index, and the largest respectively, which represent the first occurrence of a number and the last
      int firstNumberOccurrence = sortedByIndex.firstKey();
      int lastNumberOccurrence = sortedByIndex.lastKey();

      String firstNumber = sortedByIndex.get(firstNumberOccurrence);
      String lastNumber = sortedByIndex.get(lastNumberOccurrence);


      int calibratedNum = getCalibratedNumber(firstNumber, lastNumber);

      // increase the total amount
      sum += calibratedNum;

      //reset the tree
      sortedByIndex = new TreeMap<>();
      // proceed to next line
      line = reader.readLine();
    }

    return sum;
  }

  public int calibrate() throws IOException {
    int sum = 0;
    String line = reader.readLine();

    while (line != null) {
      int i = 0;
      char firstNumber = '\\';
      char lastNumber = '\\';

      while (i < line.length()) {

        // check if current char is a digit
        if (Character.isDigit(line.charAt(i))) {

          // check if this is the first occurrence of a digit
          if ((firstNumber == '\\')) {
            firstNumber = line.charAt(i);
            lastNumber = line.charAt(i);
          }

          // otherwise set the last occurrence of a digit to the current digit
          else {
            lastNumber = line.charAt(i);
          }
        }
        i++;
      }

      // build a string from the two digits as chars
      String str = new String(new char[] {firstNumber, lastNumber});
      int calibratedNum = Integer.parseInt(str);

      // increase the total amount
      sum += calibratedNum;

      // proceed to next line
      line = reader.readLine();
    }

    return sum;
  }

  private int getCalibratedNumber(String num1, String num2){
    if(num1.length() > 1){
      num1 = stringToDigitMap.get(num1);
    }

    if(num2.length() > 1){
      num2 = stringToDigitMap.get(num2);
    }

    String str = String.join("", num1, num2);

    return Integer.parseInt(str);
  }


}
