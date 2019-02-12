package cn.davidma.runicarcanology.util;

import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

	public static String BASE = Info.MOD_ID;
	
	// Property.
	public static String RUNE_FILTER = "runeFilter";
	
	// Activatable Rune.
	public static String IS_ACTIVE = "isActive";
	public static String RUNE_FACING = "runeFacing";
	
	// Animation.
	public static String ANIMATION_LIST = "animationList";
	public static String RUNE_TYPE = "runeType";
	public static String CURR_TIME = "currTime";
	
	// Arcane Workbench.
	public static String INVENTORY = "inventory";
	public static String CRAFTING_TICK = "craftingTick";
	public static String IS_CRAFTING = "isCrafting";
	
	// Item Filter.
	public static String FILTER = "filter";
	public static String STACK_LIST = "stackList";
	public static String WHITELIST = "whitelist";
	public static String IGNORE_META = "ignoreMeta";
	
	public static NBTTagCompound getEssentialNBT(ItemStack stack) {
		NBTTagCompound base = stack.getTagCompound();
		if (base == null) base = new NBTTagCompound();
		NBTTagCompound nbt = base.getCompoundTag(BASE);
		if (nbt == null) nbt = new NBTTagCompound();
		return nbt;
	}
	
	public static void setEssentialNBT(ItemStack stack, NBTTagCompound nbt) {
		NBTTagCompound base = stack.getTagCompound();
		if (base == null) base = new NBTTagCompound();
		base.setTag(BASE, nbt);
		stack.setTagCompound(base);
	}
}
