package de.kevinkepp.util;

import org.junit.Assert;
import org.junit.Test;

/*
 * Tests if a binary number and a hexadecimal number are equal.
 * Both are given as strings.
 * Shows code reuse.
 */
public class CompareBinHexTest {

  @Test
  public void test() {
    String n1 = "0101001011";
    String n2 = "14B";
    Assert.assertTrue(CompareBinHex.compareBinHex(n1, n2));

    String n3 = "1101001011";
    String n4 = "14B";
    Assert.assertFalse(CompareBinHex.compareBinHex(n3, n4));
  }
}
