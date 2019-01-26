package cn.davidma.runicarcanology.network.handler;

import cn.davidma.runicarcanology.network.RuneAnimationMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerMessageHandlerDummy implements IMessageHandler<RuneAnimationMessage, IMessage> {

	@Override
	public IMessage onMessage(RuneAnimationMessage message, MessageContext ctx) {
		System.err.println("Message handler dummy recieved message.");
		return null;
	}

}
