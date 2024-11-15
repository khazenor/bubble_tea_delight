package khazenor.bubble_tea_delight.definitions;
import org.codehaus.plexus.util.StringUtils;

public class ItemWrapper {
  public static String getDefaultItemId(String itemNameEnUs) {
    return itemNameEnUs.toLowerCase().replace(' ', '_');
  }

  public static String getDefaultItemName(String itemId) {
    return StringUtils.capitaliseAllWords(itemId.replace('_', ' '));
  }
  public ItemWrapper(String itemId, String nameEnUs) {
    this.itemId = itemId;
    this.nameEnUs = nameEnUs;
  }
  public ItemWrapper() {}

  public String itemId;
  public String nameEnUs;
}
