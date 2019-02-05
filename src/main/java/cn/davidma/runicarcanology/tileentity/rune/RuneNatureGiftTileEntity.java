package cn.davidma.runicarcanology.tileentity.rune;

import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.tileentity.base.PlacableRuneTileEntity;

public class RuneNatureGiftTileEntity extends PlacableRuneTileEntity {

	@Override
	protected void createAnimations() {
		this.addPassiveAnimation(EnumRuneAnimation.NATURE_GIFT_AMBIENT);
	}
}
