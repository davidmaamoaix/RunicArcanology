package cn.davidma.runicarcanology.entity;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StormBringerEntityItem extends EntityItem {
	
	public StormBringerEntityItem(World world, double x, double y, double z, ItemStack stack) {
		super(world, x, y, z, stack);
		this.setNoDespawn();
		this.setPickupDelay(30);
		this.isImmuneToFire = true;
	}
	
	@Override
	public void onUpdate() {
		if (!this.world.isRemote) {
			if (this.world.getTotalWorldTime() % 100 == 0) {
				this.world.addWeatherEffect(new EntityLightningBolt(this.world, this.posX, this.posY, this.posZ, false));
			}
			
			BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
			if (this.world.getBlockState(pos).getBlock().equals(Blocks.FIRE)) {
				this.world.setBlockToAir(pos);
			}
			
			this.extinguish();
		}
		super.onUpdate();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}
}
