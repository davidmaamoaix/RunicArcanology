package cn.davidma.runicarcanology.tileentity.rune;

import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.tileentity.base.ActivatableRuneTileEntity;

public class RuneArcticWindTileEntity extends ActivatableRuneTileEntity {

	@Override
	protected void createAnimations() {
		this.addPassiveAnimation(EnumRuneAnimation.ARCTIC_WIND_AMBIENT);
		this.addPassiveAnimation(EnumRuneAnimation.ARCTIC_WIND_ACTIVATABLE);
	}
}
