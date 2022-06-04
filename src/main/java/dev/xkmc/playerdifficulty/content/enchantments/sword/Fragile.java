package dev.xkmc.playerdifficulty.content.enchantments.sword;

import dev.xkmc.playerdifficulty.content.enchantments.core.EnchConfig;
import dev.xkmc.playerdifficulty.content.enchantments.tool.DurabilityEnchantment;
import dev.xkmc.playerdifficulty.events.AttackEventHandler;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.function.Supplier;

public class Fragile extends SwordEnchant implements DurabilityEnchantment {

	public static final Supplier<Double> PERCENT_PER_LEVEL = () -> 0.1;
	public static final Supplier<Double> DAMAGE_FACTOR = () -> 0.5;

	public Fragile(EnchConfig config) {
		super(config);
	}

	@Override
	public int getAdditionalDamage(int lv, LivingHurtEvent event, AttackEventHandler.AttackCache attackCache) {
		return (int) (event.getAmount() * PERCENT_PER_LEVEL.get() * lv);
	}

	@Override
	public double durabilityFactor(int lv, double damage) {
		return 1 + lv * DAMAGE_FACTOR.get();
	}
}
