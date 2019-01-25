package cn.davidma.runicarcanology.render.rune.animation.core;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import net.minecraft.util.EnumFacing;

public class RuneAnimation {

	public List<CircleStats> circles;
	
	public RuneAnimation() {
		circles = new ArrayList<CircleStats>();
	}
	
	public void tick(double x, double y, double z, double time) {
		
		for(CircleStats i: circles) {
			EnumCircle circle = i.getCircle();
			EnumFacing facing = i.getFacing();
			double diameter = i.getDiameter();
			double rotationSpeed = i.getRotationSpeed();
			double rotationOffset = i.getRotationOffset();
			double newX = x + i.getxOffset();
			double newY = y + i.getyOffset();
			double newZ = z + i.getzOffset();
			float[] color = i.getColor();
			AnimationHelper.drawCircle(circle, newX, newY, newZ, diameter, time * rotationSpeed + rotationOffset, facing, color);
		}
	}
}
