package dev.xkmc.playerdifficulty.compat.champions;

import dev.xkmc.l2library.repack.registrate.providers.loot.RegistrateLootTableProvider;
import dev.xkmc.l2library.util.LootTableTemplate;
import dev.xkmc.playerdifficulty.init.PDRegistry;
import dev.xkmc.playerdifficulty.init.loot.PDGLMProvider;
import dev.xkmc.playerdifficulty.init.loot.PDLootModifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import top.theillusivec4.champions.api.IAffix;
import top.theillusivec4.champions.common.affix.*;
import top.theillusivec4.champions.common.loot.LootItemChampionPropertyCondition;

public class ChampionLootGen {

	public static void genLoot(RegistrateLootTableProvider pvd) {
		Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation("champions:champion_properties"), LootItemChampionPropertyCondition.INSTANCE);
		pvd.addLootAction(RegistrateLootTableProvider.LootType.ENTITY, (e) -> {

			e.add(new ResourceLocation("champions:champion_loot"),
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(PDRegistry.UP.get(), 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(() -> ChampionDataGenHelper.getTierAtLeast(1))
											.when(LootTableTemplate.chance(0.1f))))
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(PDRegistry.DOWN.get(), 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(() -> ChampionDataGenHelper.getTierAtLeast(2))
											.when(LootTableTemplate.chance(0.2f))))

							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(Items.BOOK, 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(() -> ChampionDataGenHelper.getTierExact(1))
											.apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20)))))
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(Items.BOOK, 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(() -> ChampionDataGenHelper.getTierExact(2))
											.apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(40)))))
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(Items.BOOK, 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(() -> ChampionDataGenHelper.getTierExact(3))
											.apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(60)))))
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(Items.BOOK, 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(() -> ChampionDataGenHelper.getTierExact(4))
											.apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(80)))))
			);
		});
	}

	private static void bindAffix(PDGLMProvider pvd, Item item, int count, IAffix affix) {
		String name = affix.getIdentifier() + "_drops_" + item.getRegistryName().getPath();
		pvd.add(name, PDRegistry.SER.get(), new PDLootModifier(
				LootTable.lootTable().withPool(LootTableTemplate.getPool(1, 0)
						.add(LootTableTemplate.getItem(item, count))).build(),
				LootItemKilledByPlayerCondition.killedByPlayer().build(),
				ChampionDataGenHelper.getAffix(affix)));
	}

	public static void genGLM(PDGLMProvider pvd) {
		bindAffix(pvd, Items.LAPIS_LAZULI, 16, new AdaptableAffix());
		bindAffix(pvd, Items.BLUE_ICE, 16, new ArcticAffix());
		bindAffix(pvd, Items.SCUTE, 4, new DampeningAffix());
		bindAffix(pvd, Items.GLOWSTONE_DUST, 16, new DesecratingAffix());
		bindAffix(pvd, Items.BLAZE_ROD, 4, new EnkindlingAffix());
		bindAffix(pvd, Items.REDSTONE, 16, new HastyAffix());
		bindAffix(pvd, Items.INFESTED_STONE, 4, new InfestedAffix());
		bindAffix(pvd, Items.QUARTZ, 16, new KnockingAffix());
		bindAffix(pvd, Items.GHAST_TEAR, 4, new LivelyAffix());
		bindAffix(pvd, Items.LODESTONE, 1, new MagneticAffix());
		bindAffix(pvd, Items.MAGMA_CREAM, 16, new MoltenAffix());
		bindAffix(pvd, Items.COBWEB, 4, new ParalyzingAffix());
		bindAffix(pvd, Items.NETHER_WART, 4, new PlaguedAffix());
		bindAffix(pvd, Items.PRISMARINE_SHARD, 16, new ReflectiveAffix());
		bindAffix(pvd, Items.COPPER_INGOT, 16, new ShieldingAffix());
		bindAffix(pvd, Items.AMETHYST_SHARD, 16, new WoundingAffix());
	}
}
