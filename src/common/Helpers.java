package common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {
  // returns all numbers as string found in the given string str
  public static List<String> findIntegers(String str) {
    Pattern integerPattern = Pattern.compile("\\d+");
    Matcher matcher = integerPattern.matcher(str);

    List<String> integerList = new ArrayList<>();
    while (matcher.find()) {
      integerList.add(matcher.group());
    }

    return integerList;
  }

  public static List<Long> convertStringListToLongList(List<String> numbersListAsString) {
    List<Long> numbersList = new ArrayList<>();
    for (String numberAsString : numbersListAsString) {
      numbersList.add(Long.parseLong(numberAsString));
    }
    return numbersList;
  }
}
