package cn.davidma.runicarcanology.render.rune.animation.core;

import cn.davidma.runicarcanology.render.rune.Circle;
import net.minecraft.util.EnumFacing;

public class CircleStats {
	
	private Circle circle;
	private EnumFacing facing;
	private double diameter;
	private double rotationSpeed;
	private double xOffset, yOffset, zOffset;
	private float[] color;

	public CircleStats(Circle circle, EnumFacing facing, double rotationSpeed, double diameter) {
		this.xOffset = 0;
		this.yOffset = 0;
		this.zOffset = 0;
		this.color = new float[] {0, 0, 0};
		this.circle = circle;
		this.facing = facing;
		this.rotationSpeed = rotationSpeed;
		this.diameter = diameter;
	}
	
	public Circle getCircle() {
		return this.circle;
	}
	
	public EnumFacing getFacing() {
		return this.facing;
	}

	public double getDiameter() {
		return this.diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	public double getRotationSpeed() {
		return this.rotationSpeed;
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public double getxOffset() {
		return this.xOffset;
	}

	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public double getyOffset() {
		return this.yOffset;
	}

	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	public double getzOffset() {
		return this.zOffset;
	}

	public void setzOffset(double zOffset) {
		this.zOffset = zOffset;
	}
	
	public float[] getColor() {
		return this.color;
	}
	
	public void setColor(float[] color) {
		this.color = color;
	}
}
