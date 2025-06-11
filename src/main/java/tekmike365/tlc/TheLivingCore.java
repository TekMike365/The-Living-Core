package tekmike365.tlc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
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
			dispatcher.register(CommandManager.literal("tlc").requires(source -> source.hasPermissionLevel(1))
								.then(CommandManager.literal("vivere")
								    .then(CommandManager.argument("block", BlockPosArgumentType.blockPos())
									.executes(TheLivingCore::executeVivereCommand)))
								.then(CommandManager.literal("morior")
									.then(CommandManager.argument("block", BlockPosArgumentType.blockPos())
									.executes(TheLivingCore::executeMoriorCommand))));
		});

		ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
			// TODO: Load
		});

		ServerChunkEvents.CHUNK_UNLOAD.register((world, chunk) -> {
			// TODO: unload
		});
	}

	public static boolean makeLiving(ServerWorld world, BlockPos blockPos) {
		// TODO: implement
		return false;
	}

	public static boolean makeDead(ServerWorld world, BlockPos blockPos) {
		// TODO: implement
		return false;
	}

	private static int executeVivereCommand(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();
		BlockPos blockPos = BlockPosArgumentType.getBlockPos(context, "block");

		boolean result = makeLiving(source.getWorld(), blockPos);
		if (result) {
			source.sendFeedback(() -> Text.literal("Made the block living at %d, %d, %d"
					.formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ())), true);
		} else {
			source.sendFeedback(() -> Text.literal("Couldn't make the block living at %d, %d, %d"
					.formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ())), true);
		}

		return 0;
	}

	private static int executeMoriorCommand(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();
		BlockPos blockPos = BlockPosArgumentType.getBlockPos(context, "block");

		boolean result = makeDead(source.getWorld(), blockPos);
		if (result) {
			source.sendFeedback(() -> Text.literal("Made the block dead at %d, %d, %d"
					.formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ())), true);
		} else {
			source.sendFeedback(() -> Text.literal("Couldn't make the block dead at %d, %d, %d"
					.formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ())), true);
		}

		return 0;
	}
}