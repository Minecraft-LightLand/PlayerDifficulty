package dev.xkmc.playerdifficulty.init.loot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PDLootModifier extends LootModifier {

	public final LootTable table;

	public PDLootModifier(LootTable table, LootItemCondition... conditionsIn) {
		super(conditionsIn);
		this.table = table;
	}

	@NotNull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> list, LootContext context) {
		list.addAll(table.getRandomItems(context));
		return list;
	}

	protected LootItemCondition[] getConditions() {
		return conditions;
	}
}
