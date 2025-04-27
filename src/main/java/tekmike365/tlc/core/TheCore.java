package tekmike365.tlc.core;

import com.mojang.brigadier.Command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TheCore {

    private static final Command<ServerCommandSource> COMMAND = context -> {
        ServerCommandSource source = context.getSource();

        ServerPlayerEntity player = source.getPlayer();
        player.sendMessage(Text.literal("YEEEEEEEEET"));

        return 0;
    };

    private static final Command<ServerCommandSource> COMMAND_2 = context -> {
        ServerCommandSource source = context.getSource();

        ServerPlayerEntity player = source.getPlayer();
        player.sendMessage(Text.literal("YEEEEEEEEET x2"));

        return 0;
    };

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("test_command").requires(source -> source.hasPermissionLevel(1)).executes(COMMAND)
                    .then(CommandManager.literal("cmd2").executes(COMMAND_2)));
        });
    }
}
