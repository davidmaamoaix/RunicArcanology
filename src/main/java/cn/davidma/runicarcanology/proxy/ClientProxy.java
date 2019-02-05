package cn.davidma.runicarcanology.proxy;

import cn.davidma.runicarcanology.network.client.RuneAnimationMessage;
import cn.davidma.runicarcanology.network.handler.ClientMessageHandler;
import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.registry.RAItems;
import cn.davidma.runicarcanology.render.particle.TextureStitcher;
import cn.davidma.runicarcanology.render.tesr.ArcaneWorkbenchTESR;
import cn.davidma.runicarcanology.render.tesr.PlacableRuneTESR;
import cn.davidma.runicarcanology.render.tesr.base.RuneHandlingTESR;
import cn.davidma.runicarcanology.tileentity.ArcaneWorkbenchTileEntity;
import cn.davidma.runicarcanology.tileentity.base.PlacableRuneTileEntity;
import cn.davidma.runicarcanology.tileentity.base.RuneHandlingTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy implements IProxy {
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
	public void registerTileEntitySpecialRenderer() {
		ClientRegistry.bindTileEntitySpecialRenderer(RuneHandlingTileEntity.class, new RuneHandlingTESR());
		ClientRegistry.bindTileEntitySpecialRenderer(ArcaneWorkbenchTileEntity.class, new ArcaneWorkbenchTESR());
		ClientRegistry.bindTileEntitySpecialRenderer(PlacableRuneTileEntity.class, new PlacableRuneTESR());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new TextureStitcher());
		this.registerTileEntitySpecialRenderer();
		CommonProxy.simpleNetworkWrapper.registerMessage(ClientMessageHandler.class, RuneAnimationMessage.class, CommonProxy.ANIMATION_MESSAGE_ID_CLIENT, Side.CLIENT);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
