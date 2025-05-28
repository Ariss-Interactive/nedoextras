package ariss.nedoextras.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderChestInventory.class)
public class EnderChestMixin {
    @Inject(method = "canPlayerUse", at = @At("HEAD"), cancellable = true)
    void canUse(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}