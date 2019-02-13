package cn.davidma.runicarcanology.tileentity.rune;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.tileentity.base.ActivatableRuneTileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class RuneArcticWindTileEntity extends ActivatableRuneTileEntity {

	private static AxisAlignedBB DETECT_BOX = new AxisAlignedBB(-5, -2, -5, 6, 3, 6);
	private static final double SLOW_AMOUNT = 0.01;
	
	private Map<Entity, double[]> slowedProjectiles;
	
	public RuneArcticWindTileEntity() {
		super();
		this.slowedProjectiles = new HashMap<Entity, double[]>();
	}
	
	@Override
	protected void createAnimations() {
		this.addPassiveAnimation(EnumRuneAnimation.ARCTIC_WIND_AMBIENT);
		this.addPassiveAnimation(EnumRuneAnimation.ARCTIC_WIND_ACTIVATABLE);
	}
	
	@Override
	public void update() {
		super.update();
		
		List<Entity> prevProjectiles = new ArrayList<Entity>(this.slowedProjectiles.keySet());
		
		if (!this.isActive()) {
			this.restoreSpeed(prevProjectiles);
			return;
		}
		
		List<Entity> collidingEntities = this.world.getEntitiesWithinAABBExcludingEntity(null, DETECT_BOX.offset(this.pos));
		
		
		for (Entity i: collidingEntities) {
			if (i instanceof EntityPlayer) continue;
			if (i instanceof IProjectile) {
				prevProjectiles.remove(i);
				
				if (!this.slowedProjectiles.containsKey(i)) {
					this.slowedProjectiles.put(i, new double[] {i.motionX, i.motionY, i.motionZ});
				}
				
				double[] originSpeed = this.slowedProjectiles.get(i);
				
				i.setVelocity(originSpeed[0] * SLOW_AMOUNT, originSpeed[1] * SLOW_AMOUNT, originSpeed[2] * SLOW_AMOUNT);
				i.velocityChanged = true;
				
			} else {
				i.setInWeb();
				i.motionX *= 0.01;
				i.motionY *= 0.01;
				i.motionZ *= 0.01;
				i.velocityChanged = true;
			}
		}
		
		this.restoreSpeed(prevProjectiles);
	}
	
	@Override
	public void onRuneDestroy() {
		List<Entity> prevProjectiles = new ArrayList<Entity>(this.slowedProjectiles.keySet());
		this.restoreSpeed(prevProjectiles);
	}
	
	private void restoreSpeed(List<Entity> prevProjectiles) {
		for (Entity i: prevProjectiles) {
			if (!i.isDead) {
				double[] originSpeed = this.slowedProjectiles.get(i);
				i.motionX = originSpeed[0];
				i.motionY = originSpeed[1];
				i.motionZ = originSpeed[2];
				i.velocityChanged = true;
			}
			this.slowedProjectiles.remove(i);
		}
	}
}
