package cn.davidma.runicarcanology.registry;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;

public class RATileEntities {
	
	private static Map<String, Class> tileEntityClasses;

	public static void addTileEntityClass(ResourceLocation registryName, Class tileEntityClass) {
		if (tileEntityClasses == null) tileEntityClasses = new HashMap<String, Class>();
		tileEntityClasses.put(registryName.toString(), tileEntityClass);
	}
	
	public static Class getTileEntityClass(String name) {
		return tileEntityClasses.get(name);
	}
	
	public static Map<String, Class> getTileEntityClassMap() {
		return tileEntityClasses;
	}
}
