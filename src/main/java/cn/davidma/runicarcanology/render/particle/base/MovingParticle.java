package cn.davidma.runicarcanology.render.particle.base;

import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

// Yes, "MovingParticle" is a terrible name.
public abstract class MovingParticle extends RAParticle {

	private double fromX;
	private double fromY;
	private double fromZ;
	private double totalDistance;
	
	public MovingParticle(World world, double fromX, double fromY, double fromZ, double toX, double toY, double toZ, double speed) {
		super(world, fromX, fromY, fromZ, getUnitSpeed(fromX, fromY, fromZ, toX, toY, toZ, speed));
		this.totalDistance = MathHelper.distance(fromX, fromY, fromZ, toX, toY, toZ);
		this.fromX = fromX;
		this.fromY = fromY;
		this.fromZ = fromZ;
	}
	
	private static double[] getUnitSpeed(double fromX, double fromY, double fromZ, double toX, double toY, double toZ, double speed) {
		double distance = MathHelper.distance(fromX, fromY, fromZ, toX, toY, toZ);
		
		double unitX = (toX - fromX) / distance;
		double unitY = (toY - fromY) / distance;
		double unitZ = (toZ - fromZ) / distance;
		
		return new double[] {unitX * speed, unitY * speed, unitZ * speed};
	}
	
	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		if (MathHelper.distance(this.fromX, this.fromY, this.fromZ, this.posX, this.posY, this.posZ) > this.totalDistance) this.setExpired();
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}
}
