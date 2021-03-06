/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2016
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package reika.dragonapi.interfaces.block;

public interface SidedTextureIndex {
	
	public int getBlockTextureFromSideAndMetadata(int side, int metadata);
	public int getRenderType();
}
