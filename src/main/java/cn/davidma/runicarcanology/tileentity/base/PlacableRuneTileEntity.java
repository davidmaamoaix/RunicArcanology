package cn.davidma.runicarcanology.tileentity.base;

import com.google.common.base.Predicate;

import cn.davidma.runicarcanology.item.ItemFilter;
import cn.davidma.runicarcanology.item.base.IRuneTool;
import cn.davidma.runicarcanology.render.rune.animation.core.ActivatableRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.util.Msg;
import cn.davidma.runicarcanology.util.NBTHelper;
import cn.davidma.runicarcanology.util.inventory.ItemFilterHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public abstract class PlacableRuneTileEntity extends RuneHandlingTileEntity {
	
	private EnumFacing runeFacing;
	
	protected ItemFilterHelper itemFilter = new ItemFilterHelper();
	
	public EnumFacing getRuneFacing() {
		return this.runeFacing;
	}
	
	public void setRuneFacing(EnumFacing facing) {
		this.runeFacing = facing;
		
		double initialRotation = 0;
		switch(this.runeFacing) {
			case UP: initialRotation = -135;break;
			case NORTH: initialRotation = 45;break;
			case EAST: initialRotation = 45; break;
			default: initialRotation = 135; break;
		}
		
		for (RuneAnimation i: this.animations) {
			for (CircleStats circle: i.circles) {
				circle.setFacing(this.runeFacing);
				circle.setRotationOffset(initialRotation);
			}
		}
	}
	
	public void playerClick(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();
		Item currItem = stack.getItem();
		if (currItem instanceof ItemFilter) {
			ItemFilter itemFilter = (ItemFilter) currItem;
			if (this.canSetItemFilter()) {
				ItemFilterHelper itemFilterHelper = ItemFilterHelper.filterFromStack(stack);
				this.setItemFilter(itemFilterHelper);
				
				String listType = itemFilterHelper.isWhitelist() ? "Whitelist" : "Blacklist";
				int size = itemFilterHelper.size();
				Msg.tellPlayer(player, "alert.apply_filter_success.key", listType, size);
				this.save();
			} else {
				Msg.tellPlayer(player, "error.cannot_apply_filter.key");
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.runeFacing = EnumFacing.values()[nbt.getInteger(NBTHelper.RUNE_FACING)];
		this.setRuneFacing(this.runeFacing); // To update all the runes.
		this.itemFilter = ItemFilterHelper.filterFromNBT(nbt.getCompoundTag(NBTHelper.RUNE_FILTER));
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger(NBTHelper.RUNE_FACING, this.runeFacing.ordinal());
		nbt.setTag(NBTHelper.RUNE_FILTER, this.itemFilter.serializeNBT());
		return super.writeToNBT(nbt);
	}
	
	public boolean canSetItemFilter() {
		return false;
	}
	
	public void setItemFilter(ItemFilterHelper itemFilter) {
		if (this.canSetItemFilter()) {
			this.itemFilter = itemFilter.copy();
		}
	}

	public void onRuneDestroy() {
		
	}
}
