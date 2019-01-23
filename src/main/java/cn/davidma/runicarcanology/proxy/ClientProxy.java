package cn.davidma.runicarcanology.proxy;

import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.registry.RAItems;
import cn.davidma.runicarcanology.render.tesr.ArcaneWorkbenchTESR;
import cn.davidma.runicarcanology.tileentity.ArcaneWorkbenchTileEntity;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
	public void registerTileEntitySpecialRenderer() {
		ClientRegistry.bindTileEntitySpecialRenderer(ArcaneWorkbenchTileEntity.class, new ArcaneWorkbenchTESR());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		this.registerTileEntitySpecialRenderer();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
