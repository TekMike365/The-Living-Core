package tekmike365.tlc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import com.mojang.brigadier.context.CommandContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheLivingCore implements ModInitializer {
	public static final String MOD_ID = "tlc";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("vivere")
					.requires(source -> source.hasPermissionLevel(1))
					.then(CommandManager.argument("block", BlockPosArgumentType.blockPos())
							.executes(TheLivingCore::executeVivereCommand)));
			dispatcher.register(CommandManager.literal("morior")
					.requires(source -> source.hasPermissionLevel(1))
					.then(CommandManager.argument("block", BlockPosArgumentType.blockPos())
							.executes(TheLivingCore::executeMoriorCommand)));
		});
	}

	private static int executeVivereCommand(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();

		BlockPos blockPos = BlockPosArgumentType.getBlockPos(context, "block");
		source.sendFeedback(() -> Text.literal("block: %s".formatted(blockPos.toString())), true);

		return 0;
	}

	private static int executeMoriorCommand(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();

		BlockPos blockPos = BlockPosArgumentType.getBlockPos(context, "block");
		source.sendFeedback(() -> Text.literal("block: %s".formatted(blockPos.toString())), true);

		return 0;
	}
}