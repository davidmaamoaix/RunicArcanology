package cn.davidma.runicarcanology.block;

import cn.davidma.runicarcanology.block.template.TransparentBlock;
import net.minecraft.block.material.Material;

import net.minecraft.util.math.AxisAlignedBB;

public class ArcaneWorkbench extends TransparentBlock {

	public ArcaneWorkbench(String name) {
		super(name, Material.IRON);
		this.BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.1875D, 1);
		this.COLLISION_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.125D, 1);
	}
}
