package cn.davidma.runicarcanology;

import cn.davidma.runicarcanology.handler.LivingEntityActivityHandler;
import cn.davidma.runicarcanology.proxy.IProxy;
import cn.davidma.runicarcanology.recipes.CraftingHelper;
import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Info.MOD_ID, name = Info.NAME, version = Info.VERSION)
public class RunicArcanology {

	@Instance(Info.MOD_ID)
	public static RunicArcanology instance;
	
	@SidedProxy(serverSide = Info.COMMON_PROXY, clientSide = Info.CLIENT_PROXY)
	public static IProxy proxy;
	
	@EventHandler
	public void preInit (FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init (FMLInitializationEvent event) {
		proxy.init(event);
		MathHelper.init();
		CraftingHelper.init();
		MinecraftForge.EVENT_BUS.register(new LivingEntityActivityHandler());
	}
	
	@EventHandler
	public void postInit (FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
