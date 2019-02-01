package cn.davidma.runicarcanology.network.client;

import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo.Color;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class RuneAnimationMessage implements IMessage {

	private EnumRuneAnimation runeAnimation;
	private BlockPos tileEntityPosition;
	
	public RuneAnimationMessage(EnumRuneAnimation runeAnimation, BlockPos tileEntityPosition) {
		this.runeAnimation = runeAnimation;
		this.tileEntityPosition = tileEntityPosition;
	}
	
	public RuneAnimationMessage() {
		
	}
	
	public EnumRuneAnimation getRuneAnimation() {
		return this.runeAnimation;
	}
	
	public BlockPos getTileEntityPosition() {
		return this.tileEntityPosition;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			double x = buf.readDouble();
			double y = buf.readDouble();
			double z = buf.readDouble();
			int index = buf.readInt();
			this.tileEntityPosition = new BlockPos(x, y, z);
			this.runeAnimation = EnumRuneAnimation.values()[index];
		} catch(IndexOutOfBoundsException error) {
			System.err.println("IndexOutOfBoundsException in RuneAnimationMessage message.");
			return;
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(this.tileEntityPosition.getX());
		buf.writeDouble(this.tileEntityPosition.getY());
		buf.writeDouble(this.tileEntityPosition.getZ());
		buf.writeInt(runeAnimation.ordinal());
	}
	
	@Override
	public String toString() {
		BlockPos pos = this.tileEntityPosition;
		return String.format("RuneAnimationMessage[%d, %d, %d]", pos.getX(), pos.getY(), pos.getZ());
	}
}
