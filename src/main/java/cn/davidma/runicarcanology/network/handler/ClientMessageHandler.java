package cn.davidma.runicarcanology.network.handler;

import cn.davidma.runicarcanology.network.RuneAnimationMessage;
import cn.davidma.runicarcanology.render.rune.EnumRune;
import cn.davidma.runicarcanology.tileentity.RuneHandlingTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientMessageHandler implements IMessageHandler<RuneAnimationMessage, IMessage> {

	@Override
	public IMessage onMessage(RuneAnimationMessage message, MessageContext ctx) {
		
		// Check.
		if (ctx.side != Side.CLIENT) {
			System.err.println("RuneAnimationMessage received on wrong side.");
			return null;
		}
		
		if (!message.isValid()) {
			System.err.println("Invalid message.");
			return null;
		}
		
		Minecraft minecraft = Minecraft.getMinecraft();
		minecraft.addScheduledTask(new Runnable() {
			
			@Override
			public void run() {
				BlockPos pos = message.getTileEntityPosition();
				EnumRune animation = message.getRuneAnimation();
				TileEntity tileEntity = minecraft.world.getTileEntity(pos);
				if (tileEntity != null && tileEntity instanceof RuneHandlingTileEntity) {
					((RuneHandlingTileEntity) tileEntity).playAnimation(animation);
				}
			}
		});
		return null;
	}
}
