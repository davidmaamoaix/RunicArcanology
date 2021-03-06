package cn.davidma.runicarcanology.block.base;

import java.util.List;

import cn.davidma.runicarcanology.RunicArcanology;
import cn.davidma.runicarcanology.registry.RABlocks;
import cn.davidma.runicarcanology.registry.RAItems;
import cn.davidma.runicarcanology.registry.Registrable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class StandardBlockBase extends Block implements Registrable {
	
	protected String name;

	public StandardBlockBase(String name, Material material) {
		this(name, material, null);
	}

	public StandardBlockBase(String name, Material material, List<String> tooltip) {
		super(material);
		this.name = name;
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		RABlocks.addBlock(name, this);
		if (tooltip == null) RAItems.addItem(name, new ItemBlock(this).setRegistryName(name));
		else /* Add custom ItemBlock */;
	}

	@Override
	public void registerModels() {
		RunicArcanology.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
