package cn.davidma.runicarcanology.render.rune;

import cn.davidma.runicarcanology.render.rune.animation.activatable.CraftingStartAnimation;
import cn.davidma.runicarcanology.render.rune.animation.ambient.WorkbenchAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;

public enum EnumRuneAnimation {

	WORKBENCH_PASSIVE(WorkbenchAnimation.class), 
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
