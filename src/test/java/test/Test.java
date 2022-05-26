package test;

import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.level.storage.loot.LootContext;
import top.theillusivec4.champions.common.loot.LootItemChampionPropertyCondition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		try {
			String str_affix = "top.theillusivec4.champions.common.loot.LootItemChampionPropertyCondition$AffixesPredicate";
			Class<?> cls = LootItemChampionPropertyCondition.class;
			Class<?> cls_affix = cls.getClassLoader().loadClass(str_affix);
			Field fld_affix = cls_affix.getDeclaredField("ANY");
			fld_affix.setAccessible(true);
			Object any = fld_affix.get(null);
			Constructor<?> cons = cls.getDeclaredConstructor(LootContext.EntityTarget.class, MinMaxBounds.Ints.class, cls_affix);
			cons.setAccessible(true);
			Constructor<?> cons_afx = cls_affix.getDeclaredConstructor(Set.class, MinMaxBounds.Ints.class, MinMaxBounds.Ints.class);
			cons_afx.setAccessible(true);
			Object ans = cons_afx.newInstance(Set.of("a"),MinMaxBounds.Ints.exactly(1), MinMaxBounds.Ints.exactly(1));
			System.out.println(ans);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
