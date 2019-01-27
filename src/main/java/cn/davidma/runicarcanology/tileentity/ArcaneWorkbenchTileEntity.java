package cn.davidma.runicarcanology.tileentity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import cn.davidma.runicarcanology.network.client.RuneAnimationMessage;
import cn.davidma.runicarcanology.proxy.CommonProxy;
import cn.davidma.runicarcanology.recipes.CraftingHelper;
import cn.davidma.runicarcanology.reference.Settings;
import cn.davidma.runicarcanology.render.rune.EnumRune;
import cn.davidma.runicarcanology.util.Msg;
import cn.davidma.runicarcanology.util.NBTHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ArcaneWorkbenchTileEntity extends RuneHandlingTileEntity {

	private static int MAX_INV_SIZE = 128;
	
	private ItemStackHandler inventory = new ItemStackHandler(MAX_INV_SIZE);
	
	// To control the progress of crafting.
	private boolean crafting;
	private int craftingTick;
	
	@Override
	protected void createAnimations() {
		this.addAnimation(EnumRune.WORKBENCH_PASSIVE);
		this.addAnimation(EnumRune.CRAFTING_START);
	}
	
	@Override
	public void update() {
		super.update();
		if (this.crafting) {
			this.craftingTick++;
			if (this.craftingTick > Settings.CRAFTING_DURATION) {
				this.crafting = false;
				this.onCraftingFinish();
			}
		}
	}
	
	public void playerClick(EntityPlayer player, List<? extends Entity> collidingEntity) {
		if (!this.enoughSpace()) {
			Msg.tellPlayer(player, I18n.format("error.workbench_space.key"));
		} else {
			this.clearInventory();
			IItemHandler itemHandler = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
			
			int index = 0;
			List<ItemStack> ingredients = new ArrayList<ItemStack>();
			for (Entity entity: collidingEntity) {
				if (entity instanceof EntityItem) {
					
					ItemStack stack = ((EntityItem) entity).getItem();
					ItemStack newStack = stack.copy();
					newStack.setCount(1);
					for (int i = 0; i < stack.getCount(); i++) {
						this.inventory.insertItem(index++, newStack.copy(), false);
						ingredients.add(newStack.copy());
					}
					entity.setDead();
				}
			}
			
			// Throw items back if invalid recipe.
			ItemStack output = CraftingHelper.getCraftingResult(ingredients);
			if (output.isEmpty()) {
				Msg.tellPlayer(player, I18n.format("error.invalid_recipe.key"));
				
				// Biu biu biu!
				for (ItemStack i: ingredients) {
					double x = this.pos.getX() + 0.5;
					double z = this.pos.getZ() + 0.5;
					EntityItem item = new EntityItem(this.world, x, this.pos.getY(), z, i);
					this.world.spawnEntity(item);
				}
				
				this.clearInventory();
				
				return;
			}
			
			
			if (player instanceof EntityPlayerMP) {
				RuneAnimationMessage runeAnimationMessage = new RuneAnimationMessage(EnumRune.CRAFTING_START, this.pos);
				CommonProxy.simpleNetworkWrapper.sendTo(runeAnimationMessage, (EntityPlayerMP) player);
			}
			
			this.markDirty();
		}
	}
	
	private void startCrafting() {
		if (!this.crafting) {
			this.crafting = true;
			this.craftingTick = 0;
		}
	}
	
	private void onCraftingFinish() {
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.inventory.deserializeNBT(nbt.getCompoundTag(NBTHelper.INVENTORY));
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag(NBTHelper.INVENTORY, this.inventory.serializeNBT());
		return super.writeToNBT(nbt);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(this.getPos().add(-3, 0, -3), this.getPos().add(3, 3, 3));
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) this.inventory : super.getCapability(capability, facing);
	}
	
	public boolean enoughSpace() {
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					
					// Do not check the position of this block.
					if (i == 0 && j == 0 && k == 0) continue;
					
					BlockPos check = new BlockPos(this.pos.getX() + i, this.pos.getY() + k, this.pos.getZ() + j);
					if (!this.world.isAirBlock(check)) return false;
				}
			}
		}
		return true;
	}
	
	public boolean isCrafting() {
		return this.crafting;
	}
	
	private void clearInventory() {
		this.inventory = new ItemStackHandler(MAX_INV_SIZE);
	}
}
