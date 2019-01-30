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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
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
	private boolean isCrafting;
	private int craftingTick;
	
	// To store temporary ingredients (Client-side only).
	private List<ItemStack> tempIngredients;
	
	@Override
	protected void createAnimations() {
		this.addPassiveAnimation(EnumRune.WORKBENCH_PASSIVE);
	}
	
	@Override
	public void update() {
		super.update();
		if (this.world.isRemote) {
			this.craftingTick++;
		} else {
			if (this.isCrafting) {
				this.craftingTick++;
				if (this.craftingTick > Settings.CRAFTING_DURATION) {
					this.stopCrafting();
				}
			}
		}
	}
	
	public void playerClick(EntityPlayer player, List<? extends Entity> collidingEntity) {
		if (this.isCrafting) return;
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
					this.throwStack(i);
				}
				
				this.clearInventory();
				
				return;
			}
			
			this.playAnimation(EnumRune.CRAFTING_START);
			RuneAnimationMessage runeAnimationMessage = new RuneAnimationMessage(EnumRune.CRAFTING_START, this.pos);
			CommonProxy.simpleNetworkWrapper.sendToDimension(runeAnimationMessage, player.dimension);;
			
			this.startCrafting();
		}
	}
	
	private void startCrafting() {
		if (!this.isCrafting) {
			this.isCrafting = true;
			this.craftingTick = 0;
			this.save();
		}
	}
	
	private void stopCrafting() {
		this.isCrafting = false;
		this.throwStack(CraftingHelper.getCraftingResult(this.getIngredients()));
		this.clearInventory();
		this.tempIngredients = null;
		this.save();
	}
	
	private void throwStack(ItemStack stack) {
		double x = this.pos.getX() + 0.5;
		double y = this.pos.getY();
		double z = this.pos.getZ() + 0.5;
		EntityItem item = new EntityItem(this.world, x, y, z, stack);
		this.world.spawnEntity(item);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.inventory.deserializeNBT(nbt.getCompoundTag(NBTHelper.INVENTORY));
		this.craftingTick = nbt.getInteger(NBTHelper.CRAFTING_TICK);
		this.isCrafting = nbt.getBoolean(NBTHelper.IS_CRAFTING);
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag(NBTHelper.INVENTORY, this.inventory.serializeNBT());
		nbt.setBoolean(NBTHelper.IS_CRAFTING, this.isCrafting);
		nbt.setInteger(NBTHelper.CRAFTING_TICK, this.craftingTick);
		return super.writeToNBT(nbt);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(this.getPos().add(-3, 0, -3), this.getPos().add(3, 3, 3));
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		if (facing != null) return false;
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (facing != null) return null;
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
		return this.isCrafting;
	}
	
	public int getCraftingTick() {
		return this.craftingTick;
	}
	
	public List<ItemStack> getIngredients() {
		List<ItemStack> stacks = new ArrayList<ItemStack>();
		for (int i = 0; i < this.inventory.getSlots(); i++) {
			ItemStack stack = this.inventory.getStackInSlot(i);
			if (!stack.isEmpty()) stacks.add(stack);
		}
		return stacks;
	}
	
	public List<ItemStack> getTempIngredients() {
		if (this.tempIngredients == null) this.tempIngredients = this.getIngredients();
		return this.tempIngredients;
	}
	
	private void clearInventory() {
		this.inventory = new ItemStackHandler(MAX_INV_SIZE);
	}
}
