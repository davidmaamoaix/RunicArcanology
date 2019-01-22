package cn.davidma.runicarcanology.registry;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegistry(Register<Item> event) {
		RAItems.instantiateAllItems();
		List<Item> items = RAItems.getItemList();
		Item[] modItems = new Item[items.size()];
		modItems = items.toArray(modItems);
		event.getRegistry().registerAll(modItems);
	}
	
	@SubscribeEvent
	public static void onBlockRegistry(Register<Block> event) {
		RABlocks.instatiateAllBlocks();
		List<Block> blocks = RABlocks.getBlockList();
		Block[] modBlocks = new Block[blocks.size()];
		modBlocks = blocks.toArray(modBlocks);
		event.getRegistry().registerAll(modBlocks);
	}
	
	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		for (Item i: RAItems.getItemList()) {
			if (i instanceof Registrable) ((Registrable) i).registerModels();
		}
		for (Block i: RABlocks.getBlockList()) {
			if (i instanceof Registrable) ((Registrable) i).registerModels();
		}
	}
}
