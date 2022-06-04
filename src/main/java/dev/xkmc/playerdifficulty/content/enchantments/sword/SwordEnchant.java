package dev.xkmc.playerdifficulty.content.enchantments.sword;

import dev.xkmc.playerdifficulty.content.enchantments.core.BaseEnchantment;
import dev.xkmc.playerdifficulty.content.enchantments.core.ConflictGroup;
import dev.xkmc.playerdifficulty.content.enchantments.core.EnchConfig;
import dev.xkmc.playerdifficulty.content.enchantments.core.Type;
import dev.xkmc.playerdifficulty.events.AttackEventHandler;
import dev.xkmc.playerdifficulty.init.PDEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class SwordEnchant extends BaseEnchantment {

	public static final EnchConfig ANTI_MAGIC = new EnchConfig(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, ConflictGroup.PENETRATION, Type.ORANGE, 5);
	public static final EnchConfig SOUL_SLASH = new EnchConfig(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, ConflictGroup.PENETRATION, Type.ORANGE, 5);
	public static final EnchConfig STACK_DMG = new EnchConfig(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, ConflictGroup.STACKING, Type.ORANGE, 5);
	public static final EnchConfig TRACK_ENT = new EnchConfig(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, ConflictGroup.STACKING, Type.ORANGE, 5);
	public static final EnchConfig FRAGILE = new EnchConfig(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, ConflictGroup.NONE, Type.PURPLE, 5,
			() -> Enchantments.SHARPNESS, () -> Enchantments.UNBREAKING, PDEnchantments.ROBUST::get);

	public static final Random RANDOM = new Random();

	protected SwordEnchant(EnchConfig config) {
		super(config, EquipmentSlot.MAINHAND);
	}

	public void onTargetAttacked(int lv, LivingAttackEvent event, AttackEventHandler.AttackCache attackCache) {
	}

	public int getAdditionalDamage(int lv, LivingHurtEvent event, AttackEventHandler.AttackCache attackCache) {
		return 0;
	}

	public void onTargetHurt(int lv, LivingHurtEvent event, AttackEventHandler.AttackCache attackCache) {
	}

	public void onTargetDamage(int lv, LivingDamageEvent event, AttackEventHandler.AttackCache attackCache) {
	}

}
