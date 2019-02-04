package cn.davidma.runicarcanology.registry;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

public class CustomStateMapper extends StateMapperBase {

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		return new ModelResourceLocation(Info.MOD_ID + ":runic_plate_base", "");
	}

}
