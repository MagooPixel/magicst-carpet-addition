package cn.magicst.mcaddition;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class Main {
	public static String MOD_ID = "pca";
	// still use pca here so protocol works
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static String MOD_VERSION;

	static {
		Optional<ModContainer> modContainerOptional = FabricLoader.getInstance().getModContainer(MOD_ID);
		modContainerOptional.ifPresent(modContainer -> MOD_VERSION = modContainer.getMetadata().getVersion().getFriendlyString());
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}

//public class Main implements ModInitializer {
//	// This logger is used to write text to the console and the log file.
//	// It is considered best practice to use your mod name as the logger's name.
//	// That way, it's clear which mod wrote info, warnings, and errors.
//	public static final Logger LOGGER = LoggerFactory.getLogger("Example Mod");
//
//	@Override
//	public void onInitialize(ModContainer mod) {
//		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
//	}
//}
