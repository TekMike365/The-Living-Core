package tekmike365.tlc;

import net.fabricmc.api.ModInitializer;
import tekmike365.tlc.core.TheCore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheLivingCore implements ModInitializer {
	public static final String MOD_ID = "tlc";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		TheCore.initialize();
	}

}