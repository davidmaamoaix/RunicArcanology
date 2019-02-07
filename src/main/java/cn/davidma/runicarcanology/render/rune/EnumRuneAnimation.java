package cn.davidma.runicarcanology.render.rune;

import cn.davidma.runicarcanology.render.rune.animation.activatable.NatureGiftActivatable;
import cn.davidma.runicarcanology.render.rune.animation.ambient.NatureGiftAmbient;
import cn.davidma.runicarcanology.render.rune.animation.ambient.WorkbenchAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.single.CraftingStartAnimation;


public enum EnumRuneAnimation {

	WORKBENCH_AMBIENT(WorkbenchAnimation.class),
	NATURE_GIFT_AMBIENT(NatureGiftAmbient.class),
	
	NATURE_GIFT_ACTIVATABLE(NatureGiftActivatable.class),
	
	CRAFTING_START(CraftingStartAnimation.class);
	
	private Class<? extends RuneAnimation> runeClass;
	
	private EnumRuneAnimation(Class<? extends RuneAnimation> runeClass) {
		this.runeClass = runeClass;
	}
	
	public RuneAnimation create() {
		try {
			return this.runeClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Class<? extends RuneAnimation> getRuneClass() {
		return this.runeClass;
	}
}
