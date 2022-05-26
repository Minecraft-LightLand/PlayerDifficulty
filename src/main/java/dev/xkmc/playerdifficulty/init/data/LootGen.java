package dev.xkmc.playerdifficulty.init.data;

import dev.xkmc.l2library.repack.registrate.providers.loot.RegistrateLootTableProvider;
import dev.xkmc.l2library.util.LootTableTemplate;
import dev.xkmc.playerdifficulty.init.PDItems;
import dev.xkmc.playerdifficulty.init.PlayerDifficulty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;

public class LootGen {

	public static void genLoot(RegistrateLootTableProvider pvd) {
		pvd.addLootAction(RegistrateLootTableProvider.LootType.ENTITY, (e) -> {

			e.add(new ResourceLocation(PlayerDifficulty.MODID, "difficulty"),
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(PDItems.UP.get(), 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(LootTableTemplate.chance(0.01f))))
							.withPool(LootTableTemplate.getPool(1, 0)
									.add(LootTableTemplate.getItem(PDItems.DOWN.get(), 1)
											.when(LootItemKilledByPlayerCondition.killedByPlayer())
											.when(LootTableTemplate.chance(0.01f)))));
		});
	}

}
