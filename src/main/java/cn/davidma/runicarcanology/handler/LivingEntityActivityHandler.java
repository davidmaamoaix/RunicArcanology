package cn.davidma.runicarcanology.handler;

import java.util.List;

import com.google.common.base.Predicates;

import cn.davidma.runicarcanology.item.StormBringer;
import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.util.Msg;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingEntityActivityHandler {
	
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		if (!event.isCancelable()) return;
		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		if (player.world.isRemote) return;
		if (player.capabilities.isCreativeMode) return;
		
		// Press shift to pick up.
		if (event.getItem().getItem().getItem() instanceof StormBringer && !player.isSneaking()) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public void onRenderPlayerPost(RenderPlayerEvent.Post event) {
		EntityPlayer player = event.getEntityPlayer();
		if (player.inventory.getCurrentItem().getItem() instanceof StormBringer) {
			float[] color = new float[] {165 / 255.0F, 55 / 255.0F, 229 / 255.0F};
			AnimationHelper.drawCircle(EnumCircle.RUNE_CIRCLE, event.getX(), event.getY() + AnimationHelper.DISTINCTION_OFFSET, event.getZ(), 1.5, player.world.getTotalWorldTime() * 2, EnumFacing.UP, color);
		}
	}
	
	@SubscribeEvent
	public void onLivingTakeDamage(LivingHurtEvent event) {
		if (!event.isCancelable()) return;
		if (event.getEntityLiving().world.isRemote) return;
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			
			if (player.inventory.getCurrentItem().getItem() instanceof StormBringer) {
				if (event.getSource().equals(DamageSource.LIGHTNING_BOLT)) event.setCanceled(true);
				else if (event.getSource().equals(DamageSource.IN_FIRE)) event.setCanceled(true);
				else if (event.getSource().equals(DamageSource.ON_FIRE)) {
					player.extinguish();
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityFall(LivingFallEvent event) {
		if (!event.isCancelable()) return;
		if (!(event.getEntityLiving() instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		if (player.world.isRemote) return;
		
		if (player.inventory.getCurrentItem().getItem() instanceof StormBringer) {
			event.setCanceled(true);
			double speed = Math.min(player.fallDistance, 100);
			if (speed >= 10) {
				AxisAlignedBB box = player.getEntityBoundingBox().offset(player.motionX, player.motionY, player.motionZ).grow(5);
				List<Entity> entities = player.world.getEntitiesInAABBexcluding(player, box, Predicates.instanceOf(EntityLiving.class));
				for (Entity i: entities) {
					
					// Should always be true.
					if (i instanceof EntityLiving) {
						player.world.addWeatherEffect(new EntityLightningBolt(player.world, i.posX, i.posY, i.posZ, false));
						((EntityLiving) i).attackEntityFrom(DamageSource.causePlayerDamage(player), (float) (speed / 10.0F));
					}
				}
			}
		}
	}
}
