package cn.davidma.runicarcanology.render.rune.animation.core;

import cn.davidma.runicarcanology.render.rune.EnumCircle;
import net.minecraft.util.EnumFacing;

public abstract class RuneSymbol extends RuneAnimation {

	public RuneSymbol() {
		super();
		CircleStats glow = new CircleStats(this.getEnumCircle(), EnumFacing.UP, 0, 0.5);
		glow.setRotationOffset(0);
		glow.setColor(this.getColor());
		this.circles.add(glow);
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		
	}
	
	public abstract EnumCircle getEnumCircle();
	public abstract float[] getColor();
}
