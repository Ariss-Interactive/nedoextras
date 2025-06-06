package ariss.nedoextras.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.Instant;

@Mixin(EndPortalBlock.class)
public class EndPortalMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    void collisionMixin(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (Instant.now().getEpochSecond() < 1750888800)
            ci.cancel();
    }
}
