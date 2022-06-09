package dev.xkmc.playerdifficulty.compat.enchantwithmob;

import com.baguchan.enchantwithmob.EnchantWithMob;
import com.baguchan.enchantwithmob.utils.MobEnchantUtils;
import dev.xkmc.playerdifficulty.content.capability.DifficultyCap;
import dev.xkmc.playerdifficulty.init.data.DifficultyConfig;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class MobEnchantSpawn {

	public static void onSpawn(DifficultyCap level, LivingEntity entity) {
		entity.getCapability(EnchantWithMob.MOB_ENCHANT_CAP).ifPresent((cap) -> {
			int lv = getLevel(level, entity.level.random);
			MobEnchantUtils.addRandomEnchantmentToEntity(entity, cap, entity.level.getRandom(), lv, true);
		});
	}

	public static int getLevel(DifficultyCap level, Random random) {
		double pre = level.getDifficultyValue() * DifficultyConfig.COMMON.mobEnchantFactor.get();
		double var = DifficultyConfig.COMMON.mobEnchantVariance.get();
		return (int) Math.round(pre * (1 + var * (random.nextDouble() * 2 - 1)));
	}

}
