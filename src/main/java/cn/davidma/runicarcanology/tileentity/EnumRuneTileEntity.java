package cn.davidma.runicarcanology.tileentity;

import cn.davidma.runicarcanology.tileentity.base.PlacableRuneTileEntity;
import cn.davidma.runicarcanology.tileentity.rune.RuneNatureGiftTileEntity;

public enum EnumRuneTileEntity {

	RUNE_NATURE_GIFT("rune_nature_gift", RuneNatureGiftTileEntity.class);
	
	private String name;
	private Class<? extends PlacableRuneTileEntity> runeTileEntityClass;
	
	private EnumRuneTileEntity(String name, Class<? extends PlacableRuneTileEntity> runeTileEntityClass) {
		this.name = name;
		this.runeTileEntityClass = runeTileEntityClass;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Class<? extends PlacableRuneTileEntity> getRuneTileEntityClass() {
		return this.runeTileEntityClass;
	}
	
	public static Class<? extends PlacableRuneTileEntity> findRuneTileEntityClassByName(String name) {
		for (EnumRuneTileEntity i: EnumRuneTileEntity.values()) {
			if (i.getName().equals(name)) return i.getRuneTileEntityClass();
		}
		return null;
	}
}
