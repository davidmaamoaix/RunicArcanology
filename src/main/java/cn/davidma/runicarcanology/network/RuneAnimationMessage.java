package cn.davidma.runicarcanology.network;

import cn.davidma.runicarcanology.render.rune.EnumRune;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo.Color;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class RuneAnimationMessage implements IMessage {

	private boolean valid;
	private EnumRune runeAnimation;
	private BlockPos tileEntityPosition;
	
	public RuneAnimationMessage(EnumRune runeAnimation, BlockPos tileEntityPosition) {
		this.runeAnimation = runeAnimation;
		this.tileEntityPosition = tileEntityPosition;
		this.valid = true;
	}
	
	public RuneAnimationMessage() {
		this.valid = false;
	}
	
	public EnumRune getRuneAnimation() {
		return this.runeAnimation;
	}
	
	public BlockPos getTileEntityPosition() {
		return this.tileEntityPosition;
	}
	
	public boolean isValid() {
		return this.valid;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			double x = buf.readDouble();
			double y = buf.readDouble();
			double z = buf.readDouble();
			int index = buf.readInt();
			this.tileEntityPosition = new BlockPos(x, y, z);
			this.runeAnimation = EnumRune.values()[index];
		} catch(IndexOutOfBoundsException error) {
			System.err.println("IndexOutOfBoundsException in RuneAnimationMessage message.");
			return;
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.valid) return;
		buf.writeDouble(this.tileEntityPosition.getX());
		buf.writeDouble(this.tileEntityPosition.getY());
		buf.writeDouble(this.tileEntityPosition.getX());
		buf.writeInt(runeAnimation.ordinal());
	}

}
