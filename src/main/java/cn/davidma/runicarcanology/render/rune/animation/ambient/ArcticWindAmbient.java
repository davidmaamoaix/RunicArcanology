package cn.davidma.runicarcanology.render.rune.animation.ambient;

import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneSymbol;

public class ArcticWindAmbient extends RuneSymbol {

	@Override
	public EnumCircle getEnumCircle() {
		return EnumCircle.ARCTIC_WIND;
	}

	@Override
	public float[] getColor() {
		return new float[] {86 / 255.0F, 158 / 255.0F, 214 / 255.0F};
	}
}
