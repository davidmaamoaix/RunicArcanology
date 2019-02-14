package cn.davidma.runicarcanology.proxy;

import cn.davidma.runicarcanology.handler.LivingEntityActivityHandler;
import cn.davidma.runicarcanology.network.client.RuneAnimationMessage;
import cn.davidma.runicarcanology.recipes.CraftingHelper;
import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy implements IProxy {

	public static SimpleNetworkWrapper simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Info.MOD_ID);
	
	public static final byte ANIMATION_MESSAGE_ID_CLIENT = 43;
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {}

	@Override
	public void registerTileEntitySpecialRenderer() {}

	@Override
	public void preInit(FMLPreInitializationEvent event) {}

	@Override
	public void init(FMLInitializationEvent event) {}

	@Override
	public void postInit(FMLPostInitializationEvent event) {}
}
