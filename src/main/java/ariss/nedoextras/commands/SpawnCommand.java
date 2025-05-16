package ariss.nedoextras.commands;

import ariss.nedoextras.Nedoextras;
import ariss.nedoextras.persistence.ExtrasState;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.time.Instant;

public class SpawnCommand {
    private SpawnCommand() {
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        long lastHit = ExtrasState.getPlayerState(player).lastCombatLog;
        int hitDifference = (int) (Instant.now().getEpochSecond() - lastHit);

        if (hitDifference <= 30) {
            player.sendMessage(Text.translatable("nedoextras.command.spawn.pvp", 30 - hitDifference));
            return 1;
        }

        if (player.getWorld().getRegistryKey() != Nedoextras.spawnKey) {
            var serverWorld = context.getSource().getServer().getWorld(Nedoextras.spawnKey);
            if (serverWorld == null) {
                return -1;
            }

            FabricDimensions.teleport(player, serverWorld, Nedoextras.spawnTarget);
        }

        player.teleport(Nedoextras.spawnTarget.position.x, Nedoextras.spawnTarget.position.y, Nedoextras.spawnTarget.position.z);
        return 1;
    }
}
