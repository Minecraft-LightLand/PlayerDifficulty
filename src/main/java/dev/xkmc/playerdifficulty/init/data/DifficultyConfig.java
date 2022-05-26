package dev.xkmc.playerdifficulty.init.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class DifficultyConfig {

	public static class Common {

		public final ForgeConfigSpec.IntValue championsTierFactor;
		public final ForgeConfigSpec.IntValue maxDifficulty;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("difficulty");
			championsTierFactor = builder.comment("How many difficulties to match one tier")
					.worldRestart()
					.defineInRange("championsTierFactor", 10, 1, 100);
			maxDifficulty = builder.comment("Maximum Difficulty")
					.worldRestart()
					.defineInRange("maxDifficulty", 100, 5, 10000);
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
