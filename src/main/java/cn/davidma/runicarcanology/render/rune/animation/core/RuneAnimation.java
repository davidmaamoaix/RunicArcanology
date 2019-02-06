package cn.davidma.runicarcanology.render.rune.animation.core;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;

public class RuneAnimation {

	protected EnumRuneAnimation runeType;
	
	public List<CircleStats> circles;
	
	public RuneAnimation() {
		circles = new ArrayList<CircleStats>();
		for (EnumRuneAnimation i: EnumRuneAnimation.values()) {
			if (i.getRuneClass() == this.getClass()) {
				this.runeType = i;
				break;
			}
		}
	}
	
	public void draw(double x, double y, double z, double time) {
		
		for(CircleStats i: circles) {
			EnumCircle circle = i.getCircle();
			EnumFacing facing = i.getFacing();
			double diameter = i.getDiameter();
			double rotationSpeed = i.getRotationSpeed();
			double rotationOffset = i.getRotationOffset();
			double newX = x + i.getxOffset();
			double newY = y + i.getyOffset();
			double newZ = z + i.getzOffset();
			double initialRotation = i.getInitialRotation();
			float[] color = i.getColor();
			
			double temp;
			switch(facing) {
				case UP: break; // Do nothing.
				case DOWN: newY *= -1; break; // Negative yOffset.
				case NORTH: temp = newY; newY = -newZ; newZ = -temp; break; // y, z = -z, -y.
				case SOUTH: temp = newY; newY = newZ; newZ = temp; break; // Swap y & z.
				case EAST: temp = newX; newX = newY; newY = temp; break;
				case WEST: temp = newX; newX = -newY; newY = -temp; break;
			}
			
			AnimationHelper.drawCircle(circle, newX, newY, newZ, diameter, time * rotationSpeed + rotationOffset + initialRotation, facing, color);
		}
	}
	
	public EnumRuneAnimation getRuneType() {
		return this.runeType;
	}
}
