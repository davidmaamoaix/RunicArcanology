package cn.davidma.runicarcanology.render.tesr.base;

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
		
		// Tick animations.
		for (RuneAnimation i: te.getAnimations()) {
			i.draw(x, y, z, worldTime);
		}
	}
}
