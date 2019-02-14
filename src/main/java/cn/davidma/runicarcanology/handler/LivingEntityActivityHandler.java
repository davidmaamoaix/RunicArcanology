package cn.davidma.runicarcanology.handler;

import java.util.List;

import com.google.common.base.Predicates;

import cn.davidma.runicarcanology.item.StormBringer;
import cn.davidma.runicarcanology.util.Msg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingEntityActivityHandler {

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
				AxisAlignedBB box = player.getEntityBoundingBox().offset(player.motionX, player.motionY, player.motionZ).grow(2.5D);
				List<Entity> entities = player.world.getEntitiesInAABBexcluding(player, box, Predicates.instanceOf(EntityLiving.class));
				for (Entity i: entities) {
					
					// Should always be true.
					if (i instanceof EntityLiving) {
						player.world.addWeatherEffect(new EntityLightningBolt(player.world, i.posX, i.posY, i.posZ, true));
						((EntityLiving) i).attackEntityFrom(DamageSource.causePlayerDamage(player), (float) (speed / 20.0F));
					}
				}
				player.world.createExplosion(player, player.posX, player.posY, player.posZ, (float) speed / 10, false);
			}
		}
	}
}
