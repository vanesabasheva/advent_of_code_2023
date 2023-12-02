package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CubeGame {
  private final Map<String, Integer> colorToQuantityMap;

  public CubeGame(Map<String, Integer> colorToQuantityMap) {
    this.colorToQuantityMap = colorToQuantityMap;
  }

  public int getPossibleGames(BufferedReader reader) {
    int sumIds = 0;
    int currentGameId = 1;

    try {
      String line = reader.readLine();
      while (line != null) {

        // get the whole game with three draws
        String game = line.split(": ")[1];

        // save each individual draw in a separate string
        String[] draws = game.split("; ");

        // iterate through all draws and check if there is a draw that exceeds the possible amount
        boolean isAValidGame = this.isAValidGame(draws);

        // if invalid, add to the sum with invalid game ids
        if (isAValidGame) {
          sumIds += currentGameId;
        }

        currentGameId++;
        line = reader.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sumIds;
  }

  public int getPowerOfCubes(BufferedReader reader) {
    int powerSum = 0;
    try {
      String line = reader.readLine();
      while (line != null) {

        // get the whole game with three draws
        String game = line.split(": ")[1];

        // save each individual draw in a separate string
        String[] draws = game.split("; ");

        // iterate through all draws and check if there is a draw that exceeds the possible amount
        int currentPower = getPowerOfGame(draws);

        powerSum += currentPower;


        line = reader.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return powerSum;
  }

  private int getPowerOfGame(String[] draws) {
    Map<String, Integer> currentColorToQuantityMap = new HashMap<>() {
      {
        put("red", 0);
        put("green", 0);
        put("blue", 0);
      }
    };

    for (String draw : draws) {

      String[] cubesDrawn = draw.split(", ");

      // check how many cubes of each color were drawn
      for (String cubesWithColor : cubesDrawn) {

        String[] amountToColor = cubesWithColor.split("\\s");
        int amount = Integer.parseInt(amountToColor[0]);
        String color = amountToColor[1];
        int currentAmount = currentColorToQuantityMap.get(color);

        if (currentAmount < amount) {
          currentColorToQuantityMap.put(color, amount);
        }
      }
    }
    int power = 1;
    for (Map.Entry<String, Integer> color : currentColorToQuantityMap.entrySet()) {
      power = power * color.getValue();
    }
    return power;
  }

  private boolean isAValidGame(String[] draws) {

    for (String draw : draws) {

      String[] cubesDrawn = draw.split(", ");

      // check how many cubes of each color were drawn
      for (String cubesWithColor : cubesDrawn) {

        String[] amountToColor = cubesWithColor.split("\\s");
        int amount = Integer.parseInt(amountToColor[0]);
        String color = amountToColor[1];

        // get the number of cubes of the current color that are contained in the bag
        int maxAmount = this.colorToQuantityMap.get(color);

        // check if the amount exceeds the possible amount
        if (maxAmount < amount) {
          return false;
        }
      }
    }
    return true;
  }
}
