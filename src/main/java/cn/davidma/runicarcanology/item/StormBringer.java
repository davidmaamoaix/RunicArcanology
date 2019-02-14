package cn.davidma.runicarcanology.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicates;

import cn.davidma.runicarcanology.entity.StormBringerEntityItem;
import cn.davidma.runicarcanology.item.base.StandardItemBase;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StormBringer extends StandardItemBase {

	private static double FORCE = 7.0D;
	
	public StormBringer(String name) {
		super(name);
		this.setFull3D();
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		// Well... you have to jump in order to fly...
		if (!player.onGround) {
			player.getCooldownTracker().setCooldown(this, 20);
			Vec3d dir = player.getLookVec().normalize();
			player.addVelocity(dir.x * FORCE, dir.y * FORCE * 0.8, dir.z * FORCE);
		}
		
		return super.onItemRightClick(world, player, hand);
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}
	
	@Override
	public Entity createEntity(World world, Entity location, ItemStack stack) {
		Entity entity = new StormBringerEntityItem(world, location.posX, location.posY, location.posZ, stack);
		entity.motionX = location.motionX;
		entity.motionY = location.motionY;
		entity.motionZ = location.motionZ;
		return entity;
	}
}
