package cn.davidma.runicarcanology.render.tesr.base;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.tileentity.ArcaneWorkbenchTileEntity;
import cn.davidma.runicarcanology.tileentity.base.RuneHandlingTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RuneHandlingTESR extends TileEntitySpecialRenderer<RuneHandlingTileEntity> {

	@Override
	public void render(RuneHandlingTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		this.renderRuneAnimation(te, x, y, z);
	}
	
	protected void renderRuneAnimation(RuneHandlingTileEntity te, double x, double y, double z) {
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
