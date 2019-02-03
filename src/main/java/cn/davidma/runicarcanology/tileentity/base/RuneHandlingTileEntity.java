package cn.davidma.runicarcanology.tileentity.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import cn.davidma.runicarcanology.util.NBTHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public abstract class RuneHandlingTileEntity extends TileEntity implements ITickable {

	protected List<RuneAnimation> animations;
	
	public RuneHandlingTileEntity() {
		super();
		this.init();
	}
	
	public void init() {
		animations = new ArrayList<RuneAnimation>();
		this.createAnimations();
	}
	
	public void playAnimation(EnumRuneAnimation rune) {
		this.animations.add(rune.create());
	}
	
	@Override
	public void update() {
		List<RuneAnimation> expiredAnimations = new ArrayList<RuneAnimation>();
		for (RuneAnimation i: this.animations) {
			if (i instanceof SingleUseRuneAnimation) {
				SingleUseRuneAnimation animation = ((SingleUseRuneAnimation) i);
				animation.tick();
				if (animation.isExpired()) {
					expiredAnimations.add(i);
				}
			}
		}
		this.animations.removeAll(expiredAnimations);
	}
	
	public List<? extends RuneAnimation> getAnimations() {
		return new ArrayList<RuneAnimation>(this.animations);
	}
	
	public void addPassiveAnimation(EnumRuneAnimation animation) {
		this.animations.add(animation.create());
	}
	
	protected void save() {
		IBlockState state = this.world.getBlockState(this.pos);
		this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
		this.world.notifyBlockUpdate(pos, state, state, 3);
		this.world.scheduleBlockUpdate(this.pos, this.getBlockType(), 0, 0);
		this.markDirty();
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		handleUpdateTag(packet.getNbtCompound());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		NBTBase animationList = nbt.getTag(NBTHelper.ANIMATION_LIST);
		if (animationList instanceof NBTTagList) {
			for (NBTBase i: (NBTTagList) animationList) {
				if (i instanceof NBTTagCompound) {
					NBTTagCompound animationTag = (NBTTagCompound) i;
					EnumRuneAnimation animationType = EnumRuneAnimation.values()[animationTag.getInteger(NBTHelper.RUNE_TYPE)];
					RuneAnimation animation = animationType.create();
					if (animation instanceof SingleUseRuneAnimation) {
						((SingleUseRuneAnimation) animation).setCurrTime(animationTag.getInteger(NBTHelper.CURR_TIME));
					}
					this.animations.add(animation);
				}
			}
		}
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList animationList = new NBTTagList();
		for (RuneAnimation i: this.animations) {
			if (i instanceof SingleUseRuneAnimation) {
				SingleUseRuneAnimation animation = (SingleUseRuneAnimation) i;
				NBTTagCompound animationTag = new NBTTagCompound();
				animationTag.setInteger(NBTHelper.RUNE_TYPE, animation.getRuneType().ordinal());
				animationTag.setInteger(NBTHelper.CURR_TIME, animation.getCurrTime());
				animationList.appendTag(animationTag);
			}
		}
		nbt.setTag(NBTHelper.ANIMATION_LIST, animationList);
		return super.writeToNBT(nbt);
	}
	
	protected abstract void createAnimations();
}
