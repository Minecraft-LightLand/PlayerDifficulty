package dev.xkmc.playerdifficulty.content.capability;

import dev.xkmc.l2library.capability.player.PlayerCapabilityHolder;
import dev.xkmc.l2library.capability.player.PlayerCapabilityNetworkHandler;
import dev.xkmc.l2library.capability.player.PlayerCapabilityTemplate;
import dev.xkmc.l2library.serial.SerialClass;
import dev.xkmc.l2library.util.ServerOnly;
import dev.xkmc.playerdifficulty.init.PlayerDifficulty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

@SerialClass
public class PlayerLevel extends PlayerCapabilityTemplate<PlayerLevel> {

	public static Capability<PlayerLevel> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	public static PlayerCapabilityHolder<PlayerLevel> HOLDER = new PlayerCapabilityHolder<>(
			new ResourceLocation(PlayerDifficulty.MODID, "player_data"), CAPABILITY,
			PlayerLevel.class, PlayerLevel::new, PlayerCapabilityNetworkHandler::new
	);

	public static PlayerLevel get(Player player) {
		return HOLDER.get(player);
	}

	public static void register() {

	}

	@SerialClass.SerialField
	public int difficulty;

	@ServerOnly
	public void syncToClient() {
		HOLDER.network.toClientSyncAll((ServerPlayer) player);
	}

	public float getChampionChance(int tier, float chance) {
		if (true) return 1;// TODO test
		float level = (difficulty - tier) * 2;
		if (level >= 0)
			return 1 - (float) Math.pow(1 - chance, level);
		return (float) Math.pow(chance, -level);
	}
}
