package cn.davidma.runicarcanology.tileentity.rune;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.tileentity.base.ActivatableRuneTileEntity;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class RuneNatureGiftTileEntity extends ActivatableRuneTileEntity {

	private static final int COOLDOWN = 100;
	private static final int CHANCE = 25;
	
	private int currCooldown = COOLDOWN;
	
	@Override
	protected void createAnimations() {
		this.addPassiveAnimation(EnumRuneAnimation.NATURE_GIFT_AMBIENT);
		this.addPassiveAnimation(EnumRuneAnimation.NATURE_GIFT_ACTIVATABLE);
	}
	
	@Override
	public void update() {
		super.update();
		
		if (this.world.isRemote) return;
		if (!this.isActive()) return;
		
		if (this.currCooldown-- > 0) return;
		this.currCooldown = COOLDOWN;
		
		for (int i = -3; i < 5; i++) {
			for (int j = -3; j < 5; j++) {
				for (int k = -2; k < 4; k++) {
					BlockPos cropPos = this.pos.add(new BlockPos(i, k, j));
					IBlockState state = this.world.getBlockState(cropPos);
					if (state.getBlock() instanceof IGrowable) {
						IGrowable crop = (IGrowable) state.getBlock();
						if (crop.canGrow(this.world, cropPos, state, world.isRemote)) {
							if (crop.canUseBonemeal(this.world, this.world.rand, cropPos, state)) {
								if (MathHelper.randomInt(0, CHANCE) == 0) {
									crop.grow(this.world, this.world.rand, cropPos, state);
								}
							}
						}
					}
				}
			}
		}
	}
}
