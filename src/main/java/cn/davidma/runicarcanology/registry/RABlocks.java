package cn.davidma.runicarcanology.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.davidma.runicarcanology.block.ArcaneWorkbench;
import cn.davidma.runicarcanology.block.template.StandardBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class RABlocks {

	private static Map<String, Block> blocks;
	
	public static void instatiateAllBlocks() {
		new ArcaneWorkbench("arcane_workbench");
	}
	
	public static void addBlock(String name, Block block) {
		if (blocks == null) blocks = new HashMap<String, Block>();
		blocks.put(name, block);
	}
	
	public static Block getBlock(String name) {
		return blocks.get(name);
	}
	
	public static List<Block> getBlockList() {
		return new ArrayList<Block>(blocks.values());
	}
}
