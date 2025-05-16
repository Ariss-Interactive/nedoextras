package ariss.nedoextras.mixin;

import ariss.nedoextras.Nedoextras;
import ariss.nedoextras.persistence.ExtrasState;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "onPlayerConnect", at = @At("TAIL"))
    void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        var state = ExtrasState.getPlayerState(player);

        if (!state.firstJoin || !player.server.isDedicated())
            return;

        state.firstJoin = false;
        ExtrasState.markDirty(player.getServer());

        FabricDimensions.teleport(player, player.getServer().getWorld(Nedoextras.spawnKey), Nedoextras.spawnTarget);
    }
}
