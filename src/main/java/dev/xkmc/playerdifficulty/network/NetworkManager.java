package dev.xkmc.playerdifficulty.network;

import dev.xkmc.l2library.network.BaseConfig;
import dev.xkmc.l2library.network.PacketHandlerWithConfig;
import dev.xkmc.playerdifficulty.init.PlayerDifficulty;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.stream.Stream;

import static net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT;

public class NetworkManager {

	public static final PacketHandlerWithConfig HANDLER = new PacketHandlerWithConfig(
			new ResourceLocation(PlayerDifficulty.MODID, "main"), 1, "difficulty_config"
			);

	public static Stream<Map.Entry<String, BaseConfig>> getConfigs(String id) {
		return HANDLER.CONFIGS.entrySet().stream()
				.filter(e -> new ResourceLocation(e.getKey()).getPath().equals(id));
	}

	public static BaseConfig getConfig(String id) {
		return HANDLER.CONFIGS.get(id);
	}

	public static void register() {
	}

}
