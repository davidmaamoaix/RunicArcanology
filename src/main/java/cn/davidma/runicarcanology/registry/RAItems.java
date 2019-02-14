package cn.davidma.runicarcanology.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.davidma.runicarcanology.item.ItemFilter;
import cn.davidma.runicarcanology.item.StormBringer;
import cn.davidma.runicarcanology.item.base.StandardItemBase;
import net.minecraft.item.Item;

public class RAItems {

	private static Map<String, Item> items;
	
	public static void instantiateAllItems() {
		new StandardItemBase("runic_plate");
		new ItemFilter("item_filter");
		new StormBringer("storm_bringer");
	}
	
	public static void addItem(String name, Item item) {
		if (items == null) items = new HashMap<String, Item>();
		items.put(name, item);
	}
	
	public static Item getItem(String name) {
		return items.get(name);
	}
	
	public static List<Item> getItemList() {
		return new ArrayList<Item>(items.values());
	}
}
