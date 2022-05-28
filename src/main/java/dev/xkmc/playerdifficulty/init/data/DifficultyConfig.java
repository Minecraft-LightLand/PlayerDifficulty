package dev.xkmc.playerdifficulty.init.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class DifficultyConfig {

	public static class Common {

		public final ForgeConfigSpec.IntValue maxDifficulty;

		public final ForgeConfigSpec.DoubleValue enchantLevelFactor;
		public final ForgeConfigSpec.DoubleValue armorChanceFactor;
		public final ForgeConfigSpec.DoubleValue weaponChanceFactor;
		public final ForgeConfigSpec.DoubleValue dropChance;


		public final ForgeConfigSpec.BooleanValue championEnable;
		public final ForgeConfigSpec.IntValue championsTierFactor;
		public final ForgeConfigSpec.IntValue championsTierSeparation;

		public final ForgeConfigSpec.BooleanValue apotheosisEnable;
		public final ForgeConfigSpec.IntValue apotheosisTierFactor;
		public final ForgeConfigSpec.IntValue apotheosisTierSeparation;
		public final ForgeConfigSpec.DoubleValue apotheosisTierChance;

		public final ForgeConfigSpec.DoubleValue scalingHealthFactor;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("difficulty");
			maxDifficulty = builder.comment("Maximum Difficulty")
					.worldRestart()
					.defineInRange("maxDifficulty", 100, 5, 10000);
			builder.pop();

			builder.push("equipment");
			enchantLevelFactor = builder.comment("Enchantment level factor")
					.worldRestart()
					.defineInRange("enchantLevelFactor", 0.6,0.001,  1);
			armorChanceFactor = builder.comment("armor spawn chance factor")
					.worldRestart()
					.defineInRange("armorChanceFactor", 0.02,0.001,  1);
			weaponChanceFactor = builder.comment("weapon spawn chance factor")
					.worldRestart()
					.defineInRange("weaponChanceFactor", 0.04,0.001,  1);
			dropChance = builder.comment("equipment drop chance")
					.worldRestart()
					.defineInRange("dropChance", 0.085,0.085,  1);
			builder.pop();

			builder.push("champion");
			championEnable = builder.comment("enable champion module in general")
					.worldRestart()
					.define("championEnable", true);
			championsTierFactor = builder.comment("How many difficulties to match one tier")
					.worldRestart()
					.defineInRange("championsTierFactor", 10, 1, 100);
			championsTierSeparation = builder.comment("To what power does tier spawn chance to differ")
					.worldRestart()
					.defineInRange("championsTierSeparation", 2, 1, 10);
			builder.pop();

			builder.push("apotheosis");
			apotheosisEnable = builder.comment("enable apotheosis module in general (note: apotheosis deadly module is not completed yet)")
					.worldRestart()
					.define("apotheosisEnable", false);
			apotheosisTierFactor = builder.comment("How many difficulties to match one tier")
					.worldRestart()
					.defineInRange("apotheosisTierFactor", 10, 1, 100);
			apotheosisTierSeparation = builder.comment("To what power does tier spawn chance to differ")
					.worldRestart()
					.defineInRange("apotheosisTierSeparation", 2, 1, 10);
			apotheosisTierChance = builder.comment("Successive chance to generate next tier item")
					.worldRestart()
					.defineInRange("apotheosisTierChance", 0.2, 0.01, 0.9);
			builder.pop();

			builder.push("scalingHealth");
			scalingHealthFactor = builder.comment("ScalingHealth difficulty to PlayerDifficulty difficilty ratio")
					.worldRestart()
					.defineInRange("scalingHealthFactor", 1, 0.001, 1000);
			builder.pop();

		}

	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	/**
	 * Registers any relevant listeners for config
	 */
	public static void init() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DifficultyConfig.COMMON_SPEC);
	}


}
