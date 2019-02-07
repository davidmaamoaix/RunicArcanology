package cn.davidma.runicarcanology.render.rune.animation.core;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import net.minecraft.util.EnumFacing;

public class CircleStats {
	
	private EnumCircle circle;
	private EnumFacing facing;
	private double diameter;
	private double rotationSpeed;
	private double rotationOffset;
	private double xOffset, yOffset, zOffset;
	private double initialRotation;
	private float[] color;

	public CircleStats(EnumCircle circle, EnumFacing facing, double rotationSpeed, double diameter) {
		
		// Center of block (default).
		this.xOffset = 0;
		this.yOffset = -0.5 + AnimationHelper.DISTINCTION_OFFSET;
		this.zOffset = 0;
		
		this.color = new float[] {1, 1, 1, 1};
		this.circle = circle;
		this.facing = facing;
		this.rotationSpeed = rotationSpeed;
		this.rotationOffset = 0;
		this.diameter = diameter;
		this.initialRotation = 0;
	}
	
	public EnumCircle getCircle() {
		return this.circle;
	}
	
	public EnumFacing getFacing() {
		return this.facing;
	}
	
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
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
	
	public double getRotationOffset() {
		return this.rotationOffset;
	}
	
	public void setRotationOffset(double rotationOffset) {
		this.rotationOffset = rotationOffset;
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
	
	public float getAlpha() {
		if (this.color.length == 4) return this.color[3];
		return 1;
	}
	
	public void setAlpha(float alpha) {
		this.color = new float[] {this.color[0], this.color[1], this.color[2], alpha};
	}

	public double getInitialRotation() {
		return this.initialRotation;
	}
	
	public void setInitialRotation(double initialRotation) {
		this.initialRotation = initialRotation;
	}
}
