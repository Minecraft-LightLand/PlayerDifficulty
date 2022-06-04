package dev.xkmc.playerdifficulty.content.enchantments.tool;

import dev.xkmc.playerdifficulty.content.enchantments.core.EnchConfig;

public class Robust extends ToolEnchant implements DurabilityEnchantment {

	public Robust(EnchConfig config) {
		super(config);
	}

	@Override
	public double durabilityFactor(int lv, double damage) {
		return 1 / Math.sqrt(damage);
	}
}
