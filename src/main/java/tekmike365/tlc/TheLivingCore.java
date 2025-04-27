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

	private static TLCChunkPool tlcChunks = new TLCChunkPool();

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

		ServerChunkEvents.CHUNK_UNLOAD.register((world, chunk) -> {
			tlcChunks.removeChunk(new TLCChunk(world, chunk));
		});
	}

	public static boolean makeLiving(ServerWorld world, BlockPos blockPos) {
		// TODO: implement

		tlcChunks.addChunk(new TLCChunk(world, world.getWorldChunk(blockPos)));

		return false;
	}

	public static boolean makeDead(ServerWorld world, BlockPos blockPos) {
		// TODO: implement
		return false;
	}

	private static int executeVivereCommand(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();

		BlockPos blockPos = BlockPosArgumentType.getBlockPos(context, "block");
		source.sendFeedback(() -> Text.literal("Made the block living at %d, %d, %d"
				.formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ())), true);

		makeLiving(source.getWorld(), blockPos);

		return 0;
	}

	private static int executeMoriorCommand(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();

		BlockPos blockPos = BlockPosArgumentType.getBlockPos(context, "block");
		source.sendFeedback(() -> Text.literal("Made the block dead at %d, %d, %d"
				.formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ())), true);

		makeDead(source.getWorld(), blockPos);

		return 0;
	}
}