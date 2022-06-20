package dev.xkmc.playerdifficulty.init;

import dev.xkmc.l2library.repack.registrate.util.entry.ItemEntry;
import dev.xkmc.l2library.repack.registrate.util.entry.RegistryEntry;
import dev.xkmc.playerdifficulty.content.item.LevelChangeItem;
import dev.xkmc.playerdifficulty.content.item.LevelCheckItem;
import dev.xkmc.playerdifficulty.init.loot.PDGLMSerializer;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static dev.xkmc.playerdifficulty.init.PlayerDifficulty.REGISTRATE;

@SuppressWarnings("unused")
public class PDRegistry {

	public static class Tab extends CreativeModeTab {

		public Tab(String id) {
			super(PlayerDifficulty.MODID + "." + id);
		}

		@NotNull
		@Override
		public ItemStack makeIcon() {
			return CHECK.asStack();
		}
	}


	public static final ItemEntry<LevelCheckItem> CHECK;
	public static final ItemEntry<LevelChangeItem> UP, DOWN;

	public static final Tab TAB_MAIN = new Tab("material");

	static {
		REGISTRATE.creativeModeTab(() -> TAB_MAIN);
		FoodProperties prop = new FoodProperties.Builder().nutrition(20).saturationMod(1.2f).alwaysEat().build();
		CHECK = REGISTRATE.item("check", LevelCheckItem::new).register();
		UP = REGISTRATE.item("up", p -> new LevelChangeItem(p.food(prop), 1)).register();
		DOWN = REGISTRATE.item("down", p -> new LevelChangeItem(p.food(prop), -1)).register();
	}

	public static final RegistryEntry<PDGLMSerializer> SER = REGISTRATE.simple("main", ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, PDGLMSerializer::new);

	public static void register() {

	}

}
