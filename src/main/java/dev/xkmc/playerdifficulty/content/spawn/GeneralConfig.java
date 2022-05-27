package dev.xkmc.playerdifficulty.content.spawn;

import dev.xkmc.l2library.network.BaseConfig;
import dev.xkmc.l2library.serial.SerialClass;
import dev.xkmc.playerdifficulty.network.NetworkManager;

import java.util.HashMap;

@SerialClass
public class GeneralConfig extends BaseConfig {

	public static GeneralConfig getInstance() {
		return (GeneralConfig) NetworkManager.getConfig("general");
	}

	@SerialClass.SerialField
	public float enchant_factor;

	@SerialClass.SerialField
	public float armor_chance;

	@SerialClass.SerialField()
	public HashMap<WeaponConfig.Type, Double> weapon_chance = new HashMap<>();

}
