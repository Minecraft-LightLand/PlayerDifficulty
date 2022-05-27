package dev.xkmc.playerdifficulty.content.spawn;

import dev.xkmc.l2library.network.BaseConfig;
import dev.xkmc.l2library.serial.SerialClass;
import dev.xkmc.playerdifficulty.network.NetworkManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SerialClass
public class WeaponConfig extends BaseConfig {

	public static WeaponConfig cache = null;

	public static WeaponConfig getInstance() {
		if (cache != null) return cache;
		List<WeaponConfig> configs = NetworkManager.getConfigs("weapon").map(e -> (WeaponConfig) e.getValue()).toList();
		HashMap<Type, ArrayList<Entry>> map = BaseConfig.collectMap(configs, e -> e.items);
		HashMap<EntityType<?>, Type> types = BaseConfig.collectMap(configs, e -> e.types);
		WeaponConfig ans = new WeaponConfig();
		ans.items = map;
		ans.types = types;
		cache = ans;
		return ans;

	}

	public Item getItem(Type type, float level, Random r) {
		List<Entry> list = new ArrayList<>();
		int sum = 0;
		for (Entry entry : items.get(type)) {
			if (entry.level <= level) {
				list.add(entry);
				sum += entry.weight;
			}
		}
		if (sum == 0) {
			return null;
		}
		int rand = r.nextInt(sum);
		for (Entry entry : list) {
			if (rand < entry.weight) {
				return entry.item;
			}
			rand -= entry.weight;
		}
		return null;
	}

	public ItemStack getItemStack(Type type, float level, int enchant, Random r) {
		Item item = getItem(type, level, r);
		if (item == null) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = item.getDefaultInstance();
		EnchantmentHelper.enchantItem(r, stack, enchant, true);
		return stack;
	}

	private Type getType(Mob entity) {
		return types.get(entity.getType());
	}

	public void fillEntity(Mob entity, float level, Random r) {
		GeneralConfig gen = GeneralConfig.getInstance();
		EquipmentSlot slot = EquipmentSlot.MAINHAND;
		if (!entity.getItemBySlot(slot).isEmpty())
			return;
		Type type = getType(entity);
		if (type == null) {
			return;
		}
		if (r.nextDouble() > gen.weapon_chance.get(type) * level)
			return;
		ItemStack stack = getItemStack(type, level, (int) (level * gen.enchant_factor), r);
		entity.setItemSlot(slot, stack);
		entity.setDropChance(slot, 1);

	}

	@SerialClass.SerialField
	public HashMap<Type, ArrayList<Entry>> items = new HashMap<>();

	@SerialClass.SerialField
	public HashMap<EntityType<?>, Type> types = new HashMap<>();

	public void addItem(Type type, Item item, float level, int weight) {
		items.computeIfAbsent(type, k -> new ArrayList<>()).add(new Entry(item, level, weight));
	}

	public void addItems(Type type, float level, int weight, Item... items) {
		for (Item i : items) {
			addItem(type, i, level, weight);
		}
	}

	@SerialClass
	public static class Entry {

		@SerialClass.SerialField
		public float level;

		@SerialClass.SerialField
		public int weight;

		@SerialClass.SerialField
		public Item item;

		@Deprecated
		public Entry() {

		}

		public Entry(Item item, float level, int weight) {
			this.item = item;
			this.level = level;
			this.weight = weight;
		}

	}

	public enum Type {
		MEELEE, BOW, TRIDENT, CROSSBOW
	}

}
