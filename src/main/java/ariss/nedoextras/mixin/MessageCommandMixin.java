package ariss.nedoextras.mixin;

import ariss.nedoextras.Nedoextras;
import ariss.nedoextras.RegistryStuff;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.MessageCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Collection;
import java.util.Iterator;

@Mixin(MessageCommand.class)
public class MessageCommandMixin {
    @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;sendChatMessage(Lnet/minecraft/network/message/SentMessage;ZLnet/minecraft/network/message/MessageType$Parameters;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void PlayBwoink(ServerCommandSource source, Collection<ServerPlayerEntity> targets, SignedMessage message, CallbackInfo ci, MessageType.Parameters parameters, SentMessage sentMessage, boolean bl, Iterator var6, ServerPlayerEntity serverPlayerEntity, MessageType.Parameters parameters2, boolean bl2) {
        if (!source.hasPermissionLevel(2))
            return;

        Nedoextras.LOGGER.info("{} is getting BWOINKED", serverPlayerEntity.getName());
        serverPlayerEntity.networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(RegistryStuff.BWOINK), SoundCategory.NEUTRAL, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), 1.0f, 1.0f, source.getWorld().getRandom().nextLong()));
    }
}
