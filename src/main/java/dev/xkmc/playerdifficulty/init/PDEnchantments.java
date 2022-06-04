package dev.xkmc.playerdifficulty.init;

import dev.xkmc.l2library.repack.registrate.util.entry.RegistryEntry;
import dev.xkmc.playerdifficulty.content.enchantments.core.BaseEnchantment;
import dev.xkmc.playerdifficulty.content.enchantments.sword.*;
import dev.xkmc.playerdifficulty.content.enchantments.tool.Reach;
import dev.xkmc.playerdifficulty.content.enchantments.tool.Remnant;
import dev.xkmc.playerdifficulty.content.enchantments.tool.Robust;
import dev.xkmc.playerdifficulty.content.enchantments.tool.ToolEnchant;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Supplier;

import static dev.xkmc.playerdifficulty.init.PlayerDifficulty.REGISTRATE;

public class PDEnchantments {

	public static final RegistryEntry<AntiMagic> ANTI_MAGIC = reg("anti_magic", () -> new AntiMagic(SwordEnchant.ANTI_MAGIC));
	public static final RegistryEntry<SoulSlash> SOUL_SLASH = reg("soul_slash", () -> new SoulSlash(SwordEnchant.SOUL_SLASH));
	public static final RegistryEntry<StackingDamage> STACK_DMG = reg("stacking_damage", () -> new StackingDamage(SwordEnchant.STACK_DMG));
	public static final RegistryEntry<TracingDamage> TRACK_ENT = reg("tracing_damage", () -> new TracingDamage(SwordEnchant.TRACK_ENT));
	public static final RegistryEntry<Fragile> FRAGILE = reg("fragile", () -> new Fragile(SwordEnchant.FRAGILE));
	public static final RegistryEntry<LightSwing> LIGHT_SWING = reg("light_swing", () -> new LightSwing(SwordEnchant.LIGHT_SWING));
	public static final RegistryEntry<HeavySwing> HEAVY_SWING = reg("heavy_swing", () -> new HeavySwing(SwordEnchant.HEAVY_SWING));

	public static final RegistryEntry<Remnant> REMNANT = reg("remnant", () -> new Remnant(ToolEnchant.REMNANT));
	public static final RegistryEntry<Robust> ROBUST = reg("robust", () -> new Robust(ToolEnchant.ROBUST));
	public static final RegistryEntry<Reach> REACH = reg("reach", () -> new Reach(ToolEnchant.REACH));

	public static void register() {

	}

	private static <T extends BaseEnchantment> RegistryEntry<T> reg(String id, Supplier<T> sup) {
		return REGISTRATE.enchantment(id, EnchantmentCategory.BREAKABLE, (a, b, c) -> sup.get()).defaultLang().register();

	}

}
