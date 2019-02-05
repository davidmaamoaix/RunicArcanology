package cn.davidma.runicarcanology.render.rune.animation.ambient;

import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneSymbol;

public class NatureGiftAmbient extends RuneSymbol {

	@Override
	public EnumCircle getEnumCircle() {
		return EnumCircle.NATURE_GIFT;
	}

	@Override
	public float[] getColor() {
		return new float[] {0.09F, 0.45F, 0.23F};
	}

}
