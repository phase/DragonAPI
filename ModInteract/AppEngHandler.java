/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract;

import java.lang.reflect.Field;

import net.minecraft.item.ItemStack;
import Reika.DragonAPI.Auxiliary.ModList;
import Reika.DragonAPI.Base.ModHandlerBase;
import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;

public class AppEngHandler extends ModHandlerBase {

	private static final AppEngHandler instance = new AppEngHandler();

	private ItemStack certus;

	private AppEngHandler() {
		super();
		int idcertus = -1;
		if (this.hasMod()) {
			try {
				Class ae = Class.forName("appeng.api.Materials");
				Field item = ae.getField("matQuartz");
				ItemStack quartz = (ItemStack)item.get(null);
				certus = quartz.copy();
			}
			catch (ClassNotFoundException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: AppEng class not found! Cannot read its contents!");
				e.printStackTrace();
			}
			catch (NoSuchFieldException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: AppEng certusBlock field not found! "+e.getMessage());
				e.printStackTrace();
			}
			catch (SecurityException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Cannot read AppEng class (Security Exception)! "+e.getMessage());
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Illegal argument for reading AppEng class!");
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Illegal access exception for reading AppEng class!");
				e.printStackTrace();
			}
		}
		else {
			this.noMod();
		}
	}

	public static AppEngHandler getInstance() {
		return instance;
	}

	@Override
	public boolean initializedProperly() {
		return certus != null;
	}

	@Override
	public ModList getMod() {
		return ModList.APPLIEDENERGISTICS;
	}

	public ItemStack getCertusQuartz() {
		return certus.copy();
	}

}