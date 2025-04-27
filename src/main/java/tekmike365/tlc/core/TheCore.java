package tekmike365.tlc.core;

import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class TheCore {

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("vivere")
                    .requires(source -> source.hasPermissionLevel(1))
                    .then(CommandManager.argument("block", BlockPosArgumentType.blockPos())
                            .executes(TheCore::executeVivereCommand)));
            dispatcher.register(CommandManager.literal("morior")
                    .requires(source -> source.hasPermissionLevel(1))
                    .then(CommandManager.argument("block", BlockPosArgumentType.blockPos())
                            .executes(TheCore::executeMoriorCommand)));
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
