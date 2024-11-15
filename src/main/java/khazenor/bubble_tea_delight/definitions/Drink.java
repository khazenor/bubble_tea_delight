package khazenor.bubble_tea_delight.definitions;

import java.util.ArrayList;
import java.util.Map;

import static java.util.Map.entry;

public class Drink extends FoodWrapper {
  public static final String TIEQUANYIN = "tiequanyin_oolong";
  public static final String SUNMOONLAKE = "sunmoonlake_black";
  public static final String BILUOCHUN = "biluochun_green";
  public static final String ASSAM = "assam_black";

  public static final String[] TEAS = {TIEQUANYIN, SUNMOONLAKE, BILUOCHUN, ASSAM};
  public static final int[] ADJUSTMENT_LEVLES = {0, 1, 2, 3, 4};

  public static final int[] TOPPING_LEVELS = {0, 1};

  public static final Map<String, String> TEA_NAMES = Map.ofEntries(
    entry(TIEQUANYIN, "Tiequanyin oolong"),
    entry(SUNMOONLAKE, "Sun Moon Lake black"),
    entry(BILUOCHUN, "Biluochun green"),
    entry(ASSAM, "Assam black")
  );

  public static final Map<Integer, String> LEVEL_NAMES = Map.ofEntries(
    entry(0, "no"),
    entry(1, "slight"),
    entry(2, "half"),
    entry(3, "less")
  );

  public static final int BASE_NUTRITION = 2;

  public static ArrayList<Drink> allDrinks () {
    ArrayList<Drink> drinks = new ArrayList<>();
    for (String tea: TEAS) {
      for (int bobaLevel: TOPPING_LEVELS) {
        for (int creamLevel: TOPPING_LEVELS) {
          for (int sugarLevel: ADJUSTMENT_LEVLES) {
            for (int iceLevel: ADJUSTMENT_LEVLES) {
              drinks.add(new Drink(
                tea, sugarLevel, iceLevel, bobaLevel, creamLevel
              ));
            }
          }
        }
      }
    }
    return drinks;
  }

  public boolean hasSugarIceAdjustments() {
    return this.hasIceAdjustment() || this.hasSugarAdjustment();
  }

  public boolean hasIceAdjustment () {
    return this.iceLevel < 4;
  }

  public boolean hasSugarAdjustment () {
    return this.sugarLevel < 4;
  }

  public boolean hasCreamer () {
    return this.creamLevel == 1;
  }

  public Drink(String tea, int sugarLevel, int iceLevel, int bobaLevel, int creamLevel) {
    super();
    this.tea = tea;
    this.sugarLevel = sugarLevel;
    this.iceLevel = iceLevel;
    this.bobaLevel = bobaLevel;
    this.creamLevel = creamLevel;

    // super fields
    this.itemId = this.getItemId();
    this.nameEnUs = this.getNameEnUs();
    this.nutrition = this.getNutrition();
    this.saturationModifier = this.getSaturationModifier();
  }

  public String tea;
  public int sugarLevel;
  public int iceLevel;
  public int bobaLevel;
  public int creamLevel;

  private String getItemId () {
    return "%s_%db_%dc_%ds_%di".formatted(this.tea, this.bobaLevel, this.creamLevel, this.sugarLevel, this.iceLevel);
  }

  private int getNutrition () {
    return BASE_NUTRITION + this.sugarLevel + this.bobaLevel * 2;
  }

  private float getSaturationModifier () {
    return 0.5f + (float) this.bobaLevel / 2;
  }

  private String getNameEnUs () {
    String name = "";
    name += TEA_NAMES.get(this.tea);
    if (this.bobaLevel == 1) {
      name += " bubble";
    }
    if (this.creamLevel == 1) {
      name += " milk";
    }
    name += " tea";
    if (this.hasSugarIceAdjustments()) {
      name += " (";
      if (this.hasSugarAdjustment()) {
        name += LEVEL_NAMES.get(this.sugarLevel) + " sugar";
        if (this.hasIceAdjustment()) {
          name += ", ";
        }
      }
      if (this.hasIceAdjustment()) {
        name += LEVEL_NAMES.get(this.iceLevel) + " ice";
      }
      name += ")";
    }
    return name;
  }
}
