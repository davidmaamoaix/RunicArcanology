package cn.davidma.runicarcanology.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

	// Register item renderer.
	public void registerItemRenderer(Item item, int meta, String id);
	
	// Register TileEntitySpecialRenderer.
	public void registerTileEntitySpecialRenderer();
	
	// Pre-initialization.
	public void preInit(FMLPreInitializationEvent event);
	
	// Initialization.
	public void init(FMLInitializationEvent event);
	
	// Post-initialization.
	public void postInit(FMLPostInitializationEvent event);
}
