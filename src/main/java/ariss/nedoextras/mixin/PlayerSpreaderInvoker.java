package ariss.nedoextras.mixin;


import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.SpreadPlayersCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;

@Mixin({SpreadPlayersCommand.class})
public interface PlayerSpreaderInvoker {
    @Invoker("makePiles")
    static SpreadPlayersCommand.Pile[] makePiles(Random random, int count, double minX, double minZ, double maxX, double maxZ) {
        throw new AssertionError();
    }
    @Invoker("spread")
    static void spread(Vec2f center, double spreadDistance, ServerWorld world, Random random, double minX, double minZ, double maxX, double maxZ, int maxY, SpreadPlayersCommand.Pile[] piles, boolean respectTeams) throws CommandSyntaxException {
        throw new AssertionError();
    }

    @Invoker("getMinDistance")
    static double getMinDistance(Collection<? extends Entity> entities, ServerWorld world, SpreadPlayersCommand.Pile[] piles, int maxY, boolean respectTeams) {
        throw new AssertionError();
    }
}