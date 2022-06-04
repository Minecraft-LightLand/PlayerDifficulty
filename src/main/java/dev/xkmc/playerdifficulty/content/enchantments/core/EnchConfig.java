package dev.xkmc.playerdifficulty.content.enchantments.core;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Supplier;

public record EnchConfig(Enchantment.Rarity rarity, EnchantmentCategory category, ConflictGroup group, Type type, int max_level,
						 Supplier<Enchantment>... exclude) {

	@SafeVarargs
	public EnchConfig {
	}

	public boolean shouldExclude(Enchantment other) {
		for (Supplier<Enchantment> e : exclude)
			if (e.get() == other) return true;
		return false;
	}

}
