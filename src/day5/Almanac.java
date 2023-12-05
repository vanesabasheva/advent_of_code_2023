package day5;

import common.Helpers;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Almanac {
  private static final long BATCH_SIZE = 1000000000;
  private final List<String> file;
  private final List<Long> seeds;
  private final List<List<String>> mappings = new ArrayList<>();

  public Almanac(BufferedReader reader) {
    this.file = reader.lines().toList();
    this.seeds = this.getSeeds(file.get(0));
    this.initializeMappings();
  }

  public long getRealClosesLocation() {
    String[] textToSeeds = file.get(0).split(": ");
    List<String> seedsList = Helpers.findIntegers(textToSeeds[1]);
    long closestLocation = Long.MAX_VALUE;

    for (int i = 0; i < seedsList.size(); i += 2) {
      long seedStart = Long.parseLong(seedsList.get(i));
      long range = Long.parseLong(seedsList.get(i + 1));

      for (long j = 0; j < range; j += BATCH_SIZE) {
        long end = Math.min(j + BATCH_SIZE, range);
        for (long k = j; k < end; k++) {
          long seed = seedStart + k;
          // Process the seed here (map it through various stages)
          long location = calculateLocationForSeed(seed);
          if (location < closestLocation) {
            closestLocation = location;
          }
        }
      }
    }

    return closestLocation;

  }

  private long calculateLocationForSeed(long seed) {
    long currentSource = seed;
    for (List<String> mapping : mappings) {
      currentSource = this.getMappingNumber(currentSource, mapping);
    }
    return currentSource;
  }

  public long getClosestLocation() {
    long closestLocation = Long.MAX_VALUE;
    long currentSource;
    // check the destinations of each seed
    for (Long seed : seeds) {
      currentSource = seed;
      for (List<String> mapping : mappings) {
        currentSource = this.getMappingNumber(currentSource, mapping);
      }

      if (currentSource < closestLocation) {
        closestLocation = currentSource;
      }
    }

    return closestLocation;
  }

  private void initializeMappings() {
    List<String> currentList = new ArrayList<>();
    for (int i = 2; i < file.size(); i++) {

      // when we get to the next mapping part, add the numbers of the previous section to the completed mapping
      if (file.get(i).contains("map")) {
        mappings.add(currentList);
        currentList = new ArrayList<>();
        continue;
      }
      currentList.add(file.get(i));
    }
    mappings.add(currentList);
  }

  private List<Long> getSeeds(String firstLine) {
    String[] textToSeeds = firstLine.split(": ");
    List<String> seedsList = Helpers.findIntegers(textToSeeds[1]);

    return Helpers.convertStringListToLongList(seedsList);
  }

  private long getMappingNumber(long source, List<String> currentMapping) {
    for (String mapping : currentMapping) {
      List<Long> ranges = Helpers.convertStringListToLongList(Helpers.findIntegers(mapping));
      long destinationRange = ranges.get(0);
      long sourceStart = ranges.get(1);
      long rangeLength = ranges.get(2);

      if (source >= sourceStart && source < sourceStart + rangeLength) {
        return destinationRange + (source - sourceStart);
      }
    }
    return source;
  }
}
