package cn.davidma.runicarcanology;

import cn.davidma.runicarcanology.capability.RACapabilities;
import cn.davidma.runicarcanology.proxy.IProxy;
import cn.davidma.runicarcanology.reference.Info;
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
		RACapabilities.registerCapabilities();
	}
	
	@EventHandler
	public void init (FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit (FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}