package dev.xkmc.playerdifficulty.events;

import dev.xkmc.playerdifficulty.compat.CompatManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobSpawnEventHandler {

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onSpecialSpawn(LivingSpawnEvent.SpecialSpawn evt) {
		LivingEntity entity = evt.getEntityLiving();
		CompatManager.onSpawn(entity);

	}


}
