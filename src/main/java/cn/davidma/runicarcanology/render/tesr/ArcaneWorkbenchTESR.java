package cn.davidma.runicarcanology.render.tesr;

import org.lwjgl.opengl.GL11;

import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.render.animation.AnimationHelper;
import cn.davidma.runicarcanology.render.animation.Circle;
import cn.davidma.runicarcanology.tileentity.ArcaneWorkbenchTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ArcaneWorkbenchTESR extends TileEntitySpecialRenderer<ArcaneWorkbenchTileEntity> {

	@Override
	public void render(ArcaneWorkbenchTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		
		// Render passive fx.
		this.renderPassive(te, x, y, z);
	}
	
	private void renderPassive(ArcaneWorkbenchTileEntity te, double x, double y, double z) {
		ItemStack stack = new ItemStack(Blocks.QUARTZ_BLOCK);
		IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
		
		float worldTime = te.getWorld().getTotalWorldTime();
		
		AnimationHelper.drawCircle(Circle.DASH_CIRCLE, x, y, z, 2.5, worldTime * 4);
		
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x + 0.5D, y + 1.75D, z + 0.5D);
		GlStateManager.scale(0.25D, 0.25D, 0.25D);
		GlStateManager.rotate(worldTime * 4, 0, 1, 0);
		GlStateManager.rotate(35, 1, 0, 0);
		GlStateManager.rotate(45, 0, 0, 1);
		
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
		
		GlStateManager.popMatrix();
	}
}
