package day4;

import common.Helpers;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScratchCard {
  private final List<String> file;
  private int scratchCardsWon = 0;

  public ScratchCard(BufferedReader reader) {
    this.file = reader.lines().toList();
  }


  public int getPoints() {
    int fileSize = file.size();
    List<String> winningNumbers;
    List<String> cardNumbers;

    // for counting scratch cards
    int[] instances = new int[fileSize];
    Arrays.fill(instances, 1);

    int matchCount = 0;
    int globalPoints = 0;
    int points = 0;

    for (int i = 0; i < fileSize; i++) {
      List<List<String>> numbersLists = getListNumbers(file.get(i));
      winningNumbers = numbersLists.get(0);
      cardNumbers = numbersLists.get(1);

      for (String a : winningNumbers) {
        for (String b : cardNumbers) {
          if (a.equals(b)) {
            if (matchCount == 0) {
              matchCount++;
              points = 1;
            } else {
              matchCount++;
              points = points * 2;
            }

          }
        }
      }

      // For each match, add the copy cards to the corresponding card
      for (int j = i + 1; j <= i + matchCount && j < fileSize; j++) {
        instances[j] += instances[i];
      }

      globalPoints += points;
      matchCount = 0;
      points = 0;
    }

    scratchCardsWon = Arrays.stream(instances).sum();
    return globalPoints;
  }

  public int getScratchCardsWon() {
    return this.scratchCardsWon;
  }

  private List<List<String>> getListNumbers(String line) {
    String[] dayToNumbers = line.split(": ");
    String[] winningNumbersToMyNumbers = dayToNumbers[1].split("\\|");
    List<List<String>> lists = new ArrayList<>();
    List<String> winningNumbers = Helpers.findIntegers(winningNumbersToMyNumbers[0]);
    List<String> cardNumbers = Helpers.findIntegers(winningNumbersToMyNumbers[1]);
    lists.add(winningNumbers);
    lists.add(cardNumbers);
    return lists;
  }
}
