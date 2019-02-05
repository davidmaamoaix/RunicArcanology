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
		double yOffset = 0.5;
		double zOffset = 0.5;
		
		switch(facing) {
			case UP: break; // Do nothing.
			case DOWN: yOffset *= -1; break; // Negative yOffset.
			case NORTH: yOffset = -zOffset; zOffset = -yOffset; break;
			case SOUTH: yOffset = zOffset; zOffset = yOffset; break;
			case EAST: xOffset = yOffset; yOffset = xOffset; break;
			case WEST: xOffset = -yOffset; yOffset = -xOffset; break;
		}
		
		// Tick animations.
		for (RuneAnimation i: te.getAnimations()) {
			i.draw(x + xOffset, y + yOffset, z + zOffset, worldTime);
		}
	}
}
