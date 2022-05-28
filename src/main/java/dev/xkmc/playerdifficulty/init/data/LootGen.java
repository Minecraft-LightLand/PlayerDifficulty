package dev.xkmc.playerdifficulty.init.data;

import dev.xkmc.l2library.repack.registrate.providers.loot.RegistrateLootTableProvider;
import dev.xkmc.l2library.util.LootTableTemplate;
import dev.xkmc.playerdifficulty.compat.champions.ChampionDataGen;
import dev.xkmc.playerdifficulty.init.PDItems;
import dev.xkmc.playerdifficulty.init.PlayerDifficulty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import top.theillusivec4.champions.api.IAffix;
import top.theillusivec4.champions.common.affix.*;
import top.theillusivec4.champions.common.loot.LootItemChampionPropertyCondition;

public class LootGen {

	public static void genLoot(RegistrateLootTableProvider pvd) {
		Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation("champions:champion_properties"), LootItemChampionPropertyCondition.INSTANCE);
		pvd.addLootAction(RegistrateLootTableProvider.LootType.ENTITY, (e) -> {

			e.add(new ResourceLocation(PlayerDifficulty.MODID, "difficulty"),
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(PDItems.UP.get(), 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(()->ChampionDataGen.getTierAtLeast(1))
											.when(LootTableTemplate.chance(0.01f))))
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(PDItems.DOWN.get(), 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(()->ChampionDataGen.getTierAtLeast(2))
											.when(LootTableTemplate.chance(0.01f)))));

			e.add(new ResourceLocation(PlayerDifficulty.MODID, "champion_affix_loot"),
					LootTable.lootTable()
							.withPool(bindAffix(Items.LAPIS_LAZULI, 16, new AdaptableAffix()))
							.withPool(bindAffix(Items.BLUE_ICE, 16, new ArcticAffix()))
							.withPool(bindAffix(Items.SCUTE, 4, new DampeningAffix()))
							.withPool(bindAffix(Items.GLOWSTONE_DUST, 16, new DesecratingAffix()))
							.withPool(bindAffix(Items.BLAZE_ROD, 4, new EnkindlingAffix()))
							.withPool(bindAffix(Items.REDSTONE, 16, new HastyAffix()))
							.withPool(bindAffix(Items.INFESTED_STONE, 4, new InfestedAffix()))
							.withPool(bindAffix(Items.QUARTZ, 16, new KnockingAffix()))
							.withPool(bindAffix(Items.GHAST_TEAR, 4, new LivelyAffix()))
							.withPool(bindAffix(Items.LODESTONE, 1, new MagneticAffix()))
							.withPool(bindAffix(Items.MAGMA_CREAM, 16, new MoltenAffix()))
							.withPool(bindAffix(Items.COBWEB, 4, new ParalyzingAffix()))
							.withPool(bindAffix(Items.NETHER_WART, 4, new PlaguedAffix()))
							.withPool(bindAffix(Items.PRISMARINE_SHARD, 16, new ReflectiveAffix()))
							.withPool(bindAffix(Items.COPPER_INGOT, 16, new ShieldingAffix()))
							.withPool(bindAffix(Items.AMETHYST_SHARD, 16, new WoundingAffix())));
		});
	}

	private static LootPool.Builder bindAffix(Item item, int count, IAffix affix) {
		return LootTableTemplate.getPool(1, 0)
				.add(LootTableTemplate.getItem(item, count)
						.when(() -> ChampionDataGen.getAffix(affix)));
	}

}
