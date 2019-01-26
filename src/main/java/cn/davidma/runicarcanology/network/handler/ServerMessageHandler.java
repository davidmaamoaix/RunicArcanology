package cn.davidma.runicarcanology.network.handler;

import cn.davidma.runicarcanology.network.RuneAnimationMessage;
import cn.davidma.runicarcanology.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ServerMessageHandler implements IMessageHandler<RuneAnimationMessage, IMessage> {

	@Override
	public IMessage onMessage(RuneAnimationMessage message, MessageContext ctx) {
		
		// Check.
		if (ctx.side != Side.SERVER) {
			System.err.println("RuneAnimationMessage received on wrong side.");
		}
		
		if (!message.isValid()) {
			System.err.println("Invalid message.");
			return null;
		}
		
		EntityPlayerMP sender = ctx.getServerHandler().player;
		if (sender == null) {
			System.err.println("Cannot find sender of message.");
			return null;
		}
		
		WorldServer world = sender.getServerWorld();
		world.addScheduledTask(new Runnable() {

			@Override
			public void run() {
				int dim = sender.dimension;
				MinecraftServer server = sender.mcServer;
				for (EntityPlayerMP player: server.getPlayerList().getPlayers()) {
					if (dim == player.dimension) {
						RuneAnimationMessage newMessage = new RuneAnimationMessage(message.getRuneAnimation(), message.getTileEntityPosition());
						CommonProxy.simpleNetworkWrapper.sendTo(newMessage, player);
					}
				}
			}
			
		});
		
		return null;
	}

}
