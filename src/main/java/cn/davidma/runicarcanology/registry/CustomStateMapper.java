package cn.davidma.runicarcanology.registry;

import cn.davidma.runicarcanology.block.base.PlacableRune;
import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.EnumFacing;

public class CustomStateMapper extends StateMapperBase {

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		PlacableRune rune = (PlacableRune) state.getBlock();
		String facing = state.getValue(rune.FACING).toString();
		return new ModelResourceLocation(Info.MOD_ID + ":runic_plate_base", "facing=" + facing);
	}

}
