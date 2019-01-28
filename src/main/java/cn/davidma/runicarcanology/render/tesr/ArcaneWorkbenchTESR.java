package cn.davidma.runicarcanology.render.tesr;

import java.util.List;

import org.lwjgl.opengl.GL11;

import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.reference.Settings;
import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.ambient.WorkbenchAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.tileentity.ArcaneWorkbenchTileEntity;
import net.minecraft.block.BlockPressurePlate;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ArcaneWorkbenchTESR extends TileEntitySpecialRenderer<ArcaneWorkbenchTileEntity> {
	
	private static double MAX_RADIUS = 1.6D;
	private static int START_TICK = 20;
	private static int END_TICK = 200;
	private static int VANISH_TICK = 100;
	private static int VANISH_LENGTH = 20;
	
	@Override
	public void render(ArcaneWorkbenchTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		
		// Render passive fx.
		this.renderRuneAnimation(te, x, y, z);
		this.renderCrafting(te, x, y, z);
	}
	
	private void renderRuneAnimation(ArcaneWorkbenchTileEntity te, double x, double y, double z) {
		ItemStack stack = new ItemStack(Blocks.QUARTZ_BLOCK);
		
		float worldTime = te.getWorld().getTotalWorldTime();
		
		// Tick animations.
		for (RuneAnimation i: te.getAnimations()) {
			i.draw(x, y, z, worldTime);
		}
		
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x + 0.5D, y + 1.75D, z + 0.5D);
		GlStateManager.scale(0.25D, 0.25D, 0.25D);
		GlStateManager.rotate(worldTime * 4, 0, 1, 0);
		GlStateManager.rotate(35, 1, 0, 0);
		GlStateManager.rotate(45, 0, 0, 1);
		
		this.renderItem(stack);
		
		GlStateManager.popMatrix();
	}
	
	private void renderCrafting(ArcaneWorkbenchTileEntity te, double x, double y, double z) {
		if (!te.isCrafting()) return;
		List<ItemStack> ingredients = te.getTempIngredients();
		if (ingredients == null) return;
		
		int count = ingredients.size();
		double worldTime = te.getWorld().getTotalWorldTime();
		double degree = worldTime;
		
		double distance = MAX_RADIUS * Math.min((double) te.getCraftingTick() / (double) START_TICK, 1);
		int vanishPoint = Settings.CRAFTING_DURATION - VANISH_TICK;
		int currTick = te.getCraftingTick();
		double size = 0.5;
		
		// I am not putting this into one compound min/max function. Nope.
		if (currTick > vanishPoint) {
			if (currTick > vanishPoint + VANISH_LENGTH) {
				size = 0;
			} else {
				double mul = (double) (currTick - vanishPoint) / (double) VANISH_LENGTH;
				mul = 1 - mul;
				size *= mul;
			}
		}

		for (ItemStack i: ingredients) {
			double xOffset = distance * Math.sin(Math.toRadians(degree)) + 0.5D;
			double zOffset = distance * Math.cos(Math.toRadians(degree)) + 0.5D;
			GlStateManager.pushMatrix();
			GlStateManager.translate(xOffset + x, y + 0.5D, zOffset + z);
			GlStateManager.scale(size, size, size);
			GlStateManager.rotate((float) (worldTime * 4), 0, 1, 0);
			this.renderItem(i);
			GlStateManager.popMatrix();
			degree += 360 / count;
		}
	}
	
	private void renderItem(ItemStack stack) {
		GL11.glColor4f(1, 1, 1, 0.5F);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
	}
}
