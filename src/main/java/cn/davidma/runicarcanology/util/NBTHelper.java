package cn.davidma.runicarcanology.util;

import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

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
}
