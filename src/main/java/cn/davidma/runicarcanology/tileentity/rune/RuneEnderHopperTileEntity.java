package cn.davidma.runicarcanology.tileentity.rune;

import java.util.List;

import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.tileentity.base.ActivatableRuneTileEntity;
import cn.davidma.runicarcanology.tileentity.base.PlacableRuneTileEntity;
import cn.davidma.runicarcanology.util.inventory.InventoryHelper;
import cn.davidma.runicarcanology.util.inventory.ItemFilterHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class RuneEnderHopperTileEntity extends ActivatableRuneTileEntity {

	private static AxisAlignedBB DETECT_BOX = new AxisAlignedBB(-3, -2, -3, 4, 3, 4);
	
	private IItemHandler inv;
	
	@Override
	protected void createAnimations() {
		this.addPassiveAnimation(EnumRuneAnimation.ENDER_HOPPER_AMBIENT);
		this.addPassiveAnimation(EnumRuneAnimation.ARCTIC_WIND_ACTIVATABLE);
	}
	
	@Override
	public void update() {
		super.update();
		
		if (this.world.isRemote || !this.isActive()) return;
		if (this.inv == null) this.getInv();
		if (inv == null) return;
		
		List<Entity> entities = this.world.getEntitiesWithinAABBExcludingEntity(null, DETECT_BOX.offset(this.pos));
		for (Entity i: entities) {
			if (i instanceof EntityItem) {
				EntityItem entityItem = (EntityItem) i;
				ItemStack stack = entityItem.getItem();
				if (this.itemFilter.isStackValid(stack)) {
					ItemStack remain = InventoryHelper.insertStack(inv, stack);
					if (remain.isEmpty()) {
						entityItem.setDead();
					} else {
						entityItem.setItem(remain);
					}
				}
			}
		}
	}
	
	@Override
	public boolean canSetItemFilter() {
		return true;
	}
	
	private void getInv() {
		TileEntity tileEntity = world.getTileEntity(this.pos.offset(this.getRuneFacing().getOpposite()));
		if (tileEntity == null) return;
		this.inv = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.getRuneFacing());
	}
}
