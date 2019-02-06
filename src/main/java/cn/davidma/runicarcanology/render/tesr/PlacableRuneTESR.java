package cn.davidma.runicarcanology.render.tesr;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.tesr.base.RuneHandlingTESR;
import cn.davidma.runicarcanology.tileentity.base.PlacableRuneTileEntity;
import cn.davidma.runicarcanology.tileentity.base.RuneHandlingTileEntity;
import net.minecraft.util.EnumFacing;

public class PlacableRuneTESR extends RuneHandlingTESR {

	@Override
	protected void renderRuneAnimation(RuneHandlingTileEntity te, double x, double y, double z) {
		
		// This is a mess.
		if (!(te instanceof PlacableRuneTileEntity)) return;
		EnumFacing facing = ((PlacableRuneTileEntity) te).getRuneFacing();
		
		float worldTime = te.getWorld().getTotalWorldTime();
		
		// Center of block (default).
		double xOffset = 0.5;
		double yOffset = AnimationHelper.DISTINCTION_OFFSET;
		double zOffset = 0.5;
		
		// Tick animations.
		for (RuneAnimation i: te.getAnimations()) {
			i.draw(x + xOffset, y + yOffset, z + zOffset, worldTime);
		}
	}
}
