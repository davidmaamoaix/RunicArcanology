package cn.davidma.runicarcanology.item.template;

import cn.davidma.runicarcanology.RunicArcanology;
import cn.davidma.runicarcanology.registry.RAItems;
import cn.davidma.runicarcanology.registry.Registrable;
import net.minecraft.item.Item;

public class StandardItemBase extends Item implements Registrable {

	public StandardItemBase(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		RAItems.addItem(name, this);
	}

	@Override
	public void registerModels() {
		RunicArcanology.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
