package nc.init;

import nc.Global;
import nc.NuclearCraft;
import nc.config.NCConfig;
import nc.entity.EntityFeralGhoul;
import nc.worldgen.biome.NCBiomes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class NCEntities {
	
	public static void register() {
		if (NCConfig.entity_register[0]) {
			registerEntity("feral_ghoul", EntityFeralGhoul.class, 0, 0x967D73, 0x302C28);
			for (EnumCreatureType type : new EnumCreatureType[] {EnumCreatureType.CREATURE, EnumCreatureType.MONSTER}) {
				EntityRegistry.addSpawn(EntityFeralGhoul.class, Short.MAX_VALUE, 1, 2, type, NCBiomes.NUCLEAR_WASTELAND);
			}
		}
	}
	
	private static void registerEntity(String name, Class<? extends Entity> clazz, int entityId, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Global.MOD_ID, name), clazz, Global.MOD_ID + "." + name, entityId, NuclearCraft.instance, NCConfig.entity_tracking_range, 1, true, color1, color2);
	}
}
