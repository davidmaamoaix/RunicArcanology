package cn.davidma.runicarcanology.capability;

import cn.davidma.runicarcanology.util.NBTHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class ActivatableRune implements IActivatable, INBTSerializable<NBTTagCompound> {
	
	private boolean working;

	@Override
	public void activate() {
		this.working = true;
	}

	@Override
	public void deactivate() {
		this.working = false;
	}

	@Override
	public boolean isWorking() {
		return this.working;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean(NBTHelper.RUNE_WORKING, this.working);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.working = nbt.getBoolean(NBTHelper.RUNE_WORKING);
	}
}
