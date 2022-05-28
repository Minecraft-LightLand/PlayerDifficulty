package dev.xkmc.playerdifficulty.events;

import dev.xkmc.playerdifficulty.compat.CompatManager;
import dev.xkmc.playerdifficulty.content.capability.DifficultyCap;
import dev.xkmc.playerdifficulty.content.spawn.SpawnHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobSpawnEventHandler {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onSpecialSpawnEarly(LivingSpawnEvent.SpecialSpawn evt) {
		LivingEntity entity = evt.getEntityLiving();
		Player player = entity.level.getNearestPlayer(entity, 128);
		if (player == null)
			return;
		DifficultyCap cap = CompatManager.getDifficultyCap(player);
		CompatManager.spawnChampion(cap, entity);
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onSpecialSpawnLate(LivingSpawnEvent.SpecialSpawn evt) {
		LivingEntity entity = evt.getEntityLiving();
		Player player = entity.level.getNearestPlayer(entity, 128);
		if (player == null)
			return;
		DifficultyCap cap = CompatManager.getDifficultyCap(player);
		SpawnHandler.onSpawn(cap, entity);
		CompatManager.spawnApotheosis(cap, entity);
	}

}
