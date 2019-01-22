package cn.davidma.runicarcanology.capability;

import cn.davidma.runicarcanology.util.NBTHelper;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class RACapabilities {

	@CapabilityInject(IActivatable.class)
	static final Capability<IActivatable> ACTIVATABLE_RUNE = null;
	
	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(IActivatable.class, new CapabilityStorage(), ActivatableRune.class);
	}
	
	public static class CapabilityStorage implements IStorage<IActivatable> {

		@Override
		public NBTBase writeNBT(Capability<IActivatable> capability, IActivatable instance, EnumFacing side) {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean(NBTHelper.RUNE_WORKING, instance.isWorking());
			return nbt;
		}

		@Override
		public void readNBT(Capability<IActivatable> capability, IActivatable instance, EnumFacing side, NBTBase nbt) {
			
		}
	}
}
