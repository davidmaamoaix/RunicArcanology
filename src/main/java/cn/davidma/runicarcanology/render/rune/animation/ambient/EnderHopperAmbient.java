package cn.davidma.runicarcanology.render.rune.animation.ambient;

import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneSymbol;

public class EnderHopperAmbient extends RuneSymbol {

	@Override
	public EnumCircle getEnumCircle() {
		return EnumCircle.ENDER_HOPPER;
	}

	@Override
	public float[] getColor() {
		return new float[] {100 / 255.0F, 100 / 255.0F, 100 / 255.0F};
	}

}
