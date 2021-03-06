package reika.dragonapi.asm.patchers.hooks.event.render;

import net.minecraftforge.classloading.FMLForgePlugin;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import reika.dragonapi.asm.patchers.Patcher;
import reika.dragonapi.libraries.java.ReikaASMHelper;

public class TileRender extends Patcher {

	public TileRender() {
		super("net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher", "bmk");
	}

	@Override
	protected void apply(ClassNode cn) {
		MethodNode m = ReikaASMHelper.getMethodByName(cn, "func_147549_a", "renderTileEntityAt", "(Lnet/minecraft/tileentity/TileEntity;DDDF)V");
		InsnList fire = new InsnList();
		fire.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lcpw/mods/fml/common/eventhandler/EventBus;"));
		fire.add(new TypeInsnNode(Opcodes.NEW, "Reika/DragonAPI/Instantiable/Event/Client/TileEntityRenderEvent"));
		fire.add(new InsnNode(Opcodes.DUP));
		fire.add(new VarInsnNode(Opcodes.ALOAD, 1));
		fire.add(new VarInsnNode(Opcodes.DLOAD, 2));
		fire.add(new VarInsnNode(Opcodes.DLOAD, 4));
		fire.add(new VarInsnNode(Opcodes.DLOAD, 6));
		fire.add(new VarInsnNode(Opcodes.FLOAD, 8));
		fire.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "Reika/DragonAPI/Instantiable/Event/Client/TileEntityRenderEvent", "<init>", "(Lnet/minecraft/tileentity/TileEntity;DDDF)V", false));
		fire.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "cpw/mods/fml/common/eventhandler/EventBus", "post", "(Lcpw/mods/fml/common/eventhandler/Event;)Z", false));
		fire.add(new InsnNode(Opcodes.POP));
		for (int i = 0; i < m.instructions.size(); i++) {
			AbstractInsnNode ain = m.instructions.get(i);
			if (ain.getOpcode() == Opcodes.INVOKEVIRTUAL) {
				MethodInsnNode min = (MethodInsnNode)ain;
				String func = FMLForgePlugin.RUNTIME_DEOBF ? "func_147500_a" : "renderTileEntityAt";
				if (min.name.equals(func)) {
					m.instructions.insert(min, fire);
					break;
				}
			}
		}
	}
}
