package dev.xkmc.playerdifficulty.init.loot;

import dev.xkmc.playerdifficulty.compat.CompatManager;
import dev.xkmc.playerdifficulty.init.PlayerDifficulty;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class PDGLMProvider extends GlobalLootModifierProvider {

	public PDGLMProvider(DataGenerator gen) {
		super(gen, PlayerDifficulty.MODID);
	}

	@Override
	protected void start() {
		CompatManager.onGLMGen(this);
	}
}
