package day3;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Engine {
  private final Map<Character, List<Integer>> symbolToIndicesMap = new HashMap<>();
  private final List<Character> symbols = new ArrayList<>() {
    {
      add('+');
      add('-');
      add('=');
      add('*');
      add('&');
      add('@');
      add('%');
      add('$');
      add('/');
      add('#');
    }
  };

  public Engine() {
  }

  public int getEnginePart(BufferedReader reader) {
    List<String> file = reader.lines().toList();
    int fileSize = file.size();

    int sum = 0;
    int partNumber;
    String line = file.get(0);
    String nextLine = file.get(1);

    List<String> numbers = this.findIntegers(line);
    for (String number : numbers) {

      if (isAPartNumber(line, null, nextLine, number)) {
        partNumber = Integer.parseInt(number);
        sum += partNumber;

      }
    }

    String previousLine;

    for (int i = 1; i < fileSize - 1; i++) {
      previousLine = file.get(i - 1);
      line = file.get(i);
      nextLine = file.get(i + 1);
      numbers = this.findIntegers(line);
      for (String number : numbers) {
        if (isAPartNumber(line, previousLine, nextLine, number)) {
          partNumber = Integer.parseInt(number);
          sum += partNumber;
        }
      }
    }
    previousLine = file.get(fileSize - 2);
    line = file.get(fileSize - 1);

    numbers = this.findIntegers(line);
    for (String number : numbers) {
      if (isAPartNumber(line, previousLine, null, number)) {
        partNumber = Integer.parseInt(number);
        sum += partNumber;
      }
    }

    return sum;
  }

  public boolean isAPartNumber(String line, String previousLine, String nextLine, String number) {
    int indexStart = line.indexOf(number);
    int indexEnd = indexStart + number.length();

    if (previousLine == null) {

      // check if number overlaps with symbol from the current line
      if (areSymbolsOverlapping(line, indexStart, indexEnd)) {
        return true;
      }

      // check if number overlaps with a symbol from the next line
      return areSymbolsOverlapping(nextLine, indexStart, indexEnd);
    } else if (nextLine == null) {
      // check if number overlaps with symbol from the current line
      if (areSymbolsOverlapping(line, indexStart, indexEnd)) {
        return true;
      }

      // check if number overlaps with a symbol from the previous line
      return areSymbolsOverlapping(previousLine, indexStart, indexEnd);
    } else {
      // check if number overlaps with a symbol from the previous line
      if (areSymbolsOverlapping(previousLine, indexStart, indexEnd)) {
        return true;
      }

      // check if number overlaps with symbol from the current line
      if (areSymbolsOverlapping(line, indexStart, indexEnd)) {
        return true;
      }

      // check if number overlaps with a symbol from the next line
      return areSymbolsOverlapping(nextLine, indexStart, indexEnd);

    }
  }

  private boolean areSymbolsOverlapping(String line, int indexStart, int indexEnd) {
    this.updateCharacterOccurrence(line);

    for (Map.Entry<Character, List<Integer>> characterOccurrences : symbolToIndicesMap.entrySet()) {
      for (Integer indexSymbol : characterOccurrences.getValue()) {
        if (indicesOverlap(indexStart, indexEnd, indexSymbol)) {
          return true;
        }
      }
    }
    return false;
  }

  public void updateCharacterOccurrence(String line) {
    for (Character symbol : symbols) {
      List<Integer> occurrencesOfSymbol = this.findCharacter(line, symbol);
      symbolToIndicesMap.put(symbol, occurrencesOfSymbol);
    }
  }

  public boolean indicesOverlap(int indexStart, int indexEnd, int indexSymbol) {
    return indexStart - 1 <= indexSymbol && indexSymbol <= indexEnd;
  }


  public List<Integer> findCharacter(String line, Character symbol) {
    List<Integer> indexes = new ArrayList<>();

    int index = 0;
    while (index != -1) {
      index = line.indexOf(symbol, index);
      if (index != -1) {
        indexes.add(index);
        index++;
      }
    }
    return indexes;
  }

  // returns all numbers as string found in the given line
  public List<String> findIntegers(String line) {
    Pattern integerPattern = Pattern.compile("\\d+");
    Matcher matcher = integerPattern.matcher(line);

    List<String> integerList = new ArrayList<>();
    while (matcher.find()) {
      integerList.add(matcher.group());
    }

    return integerList;
  }

}
