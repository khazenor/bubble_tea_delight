package khazenor.bubble_tea_delight.definitions;

import java.util.ArrayList;

public class Drink {
  public static final String TIEQUANYIN = "tiequanyin";
  public static final String SUNMOONLAKE = "sunmoonlake";
  public static final String BILUOCHUN = "bailuochun";
  public static final String ASSAM = "assam";

  public static String[] teas() {
    return new String[]{TIEQUANYIN, SUNMOONLAKE, BILUOCHUN, ASSAM};
  }

  public static int[] levels() {
    return new int[]{0, 1, 2, 3, 4};
  }

  public static boolean[] booleanLevels() {
    return new boolean[]{true, false};
  }

  public static ArrayList<Drink> allDrinks () {
    ArrayList<Drink> drinks = new ArrayList<Drink>();
    for (String tea: teas()) {
      for (int sugarLevel: levels()) {
        for (int iceLevel: levels()) {
          for (boolean hasBoba: booleanLevels()) {
            drinks.add(new Drink(
              tea, sugarLevel, iceLevel, hasBoba
            ));
          }
        }
      }
    }
    return drinks;
  }

  public String itemId () {
    int hasBoba = this.hasBoba ? 1: 0;
    return "%s_%ds_%di_%db".formatted(this.tea, this.sugarLevel, this.iceLevel, hasBoba);
  }

  public int nutrition () {
    return 1;
  }

  public float saturationModifier () {
    return 2f;
  }

  public Drink(String tea, int sugarLevel, int iceLevel, boolean hasBoba) {
    this.tea = tea;
    this.sugarLevel = sugarLevel;
    this.iceLevel = iceLevel;
    this.hasBoba = hasBoba;
  }

  private String tea;
  private int sugarLevel;
  private int iceLevel;
  private boolean hasBoba;
}
