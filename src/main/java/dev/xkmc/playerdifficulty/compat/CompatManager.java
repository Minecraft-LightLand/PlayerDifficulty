package dev.xkmc.playerdifficulty.compat;

import dev.xkmc.playerdifficulty.compat.champions.SpawnReset;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.ModList;

public class CompatManager {

	public static void onSpawn(LivingEntity entity) {
		if (entity.level.isClientSide())
			return;
		Player player = entity.level.getNearestPlayer(entity, 128);
		if (player == null)
			return;
		if (ModList.get().isLoaded("champions")) {
			SpawnReset.onSpawn(player, entity);
		}
	}

}
