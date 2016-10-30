package de.kevinkepp.util;

/*
 * Tests if a binary number and a hexadecimal number are equal.
 * Both are given as strings.
 * Shows code reuse.
 */
public class CompareBinHex {

  public static boolean compareBinHex(String bin, String hex) {
    int n1 = toBase(bin, 2);
    int n2 = toBase(hex, 16);
    return n1 == n2;
  }

  public static int toBase(String s, int base) {
    int val = 0;
    for (int i = s.length() - 1; i >= 0; --i) {
      int digit = digitToValue(s.charAt(i));
      int exp = s.length() - 1 - i;
      val += digit * Math.pow(base, exp);
    }
    return val;
  }

  private static int digitToValue(char c) {
    if (Character.isDigit(c)) {
      return Integer.valueOf(c + "");
    } else {
      int charIntA = (int) 'a';
      int charInt = (int) Character.toLowerCase(c);
      return 10 + charInt - charIntA;
    }
  }
}
