package dev.xkmc.playerdifficulty.init.loot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Deserializers;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class PDGLMSerializer extends GlobalLootModifierSerializer<PDLootModifier> {

	private static final Gson SERIALIZER = Deserializers.createLootTableSerializer().create();

	@Override
	public PDLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
		return new PDLootModifier(SERIALIZER.fromJson(object.get("table"), LootTable.class), conditions);
	}

	@Override
	public JsonObject write(PDLootModifier instance) {
		JsonObject json = this.makeConditions(instance.getConditions());
		json.add("table", SERIALIZER.toJsonTree(instance.table));
		return json;
	}

}
