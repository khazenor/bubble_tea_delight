package khazenor.bubble_tea_delight.definitions;

import java.util.ArrayList;

public class Drink {
  public static final String TIEQUANYIN = "tiequanyin_oolong";
  public static final String SUNMOONLAKE = "sunmoonlake_black";
  public static final String BILUOCHUN = "biluochun_green";
  public static final String ASSAM = "assam_black";

  public static final int BASE_NUTRITION = 2;

  public static String[] teas() {
    return new String[]{TIEQUANYIN, SUNMOONLAKE, BILUOCHUN, ASSAM};
  }

  public static int[] levels() {
    return new int[]{0, 1, 2, 3, 4};
  }

  public static int[] bobaLevels() {
    return new int[]{0, 1};
  }

  public static ArrayList<Drink> allDrinks () {
    ArrayList<Drink> drinks = new ArrayList<Drink>();
    for (String tea: teas()) {
      for (int sugarLevel: levels()) {
        for (int iceLevel: levels()) {
          for (int bobaLevel: bobaLevels()) {
            drinks.add(new Drink(
              tea, sugarLevel, iceLevel, bobaLevel
            ));
          }
        }
      }
    }
    return drinks;
  }

  public String itemId () {
    return "%s_%ds_%di_%db".formatted(this.tea, this.sugarLevel, this.iceLevel, this.bobaLevel);
  }

  public int nutrition () {
    return BASE_NUTRITION + this.sugarLevel + this.bobaLevel * 2;
  }

  public float saturationModifier () {
    return 0.5f + (float) this.bobaLevel / 2;
  }

  public Drink(String tea, int sugarLevel, int iceLevel, int bobaLevel) {
    this.tea = tea;
    this.sugarLevel = sugarLevel;
    this.iceLevel = iceLevel;
    this.bobaLevel = bobaLevel;
  }

  public String tea;
  public int sugarLevel;
  public int iceLevel;
  public int bobaLevel;
}
