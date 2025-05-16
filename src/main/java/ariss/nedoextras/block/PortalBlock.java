package ariss.nedoextras.block;

import ariss.nedoextras.Nedoextras;
import ariss.nedoextras.block.entity.PortalBlockEntity;
import ariss.nedoextras.mixin.PlayerSpreaderInvoker;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.SpreadPlayersCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;

public class PortalBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0F, 6.0F, 0.0F, 16.0F, 12.0F, 16.0F);

    public PortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PortalBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world instanceof ServerWorld && entity.canUsePortals() && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset(-pos.getX(), -pos.getY(), -pos.getZ())), state.getOutlineShape(world, pos), BooleanBiFunction.AND)) {
            if (!(entity instanceof ServerPlayerEntity player))
                return;

            if (player.getSpawnPointPosition() == null) {
                try {
                    Random randomsource = Random.create();
                    double d0 = (Nedoextras.spreadCenter.x - Nedoextras.spreadRadius);
                    double d1 = (Nedoextras.spreadCenter.y - Nedoextras.spreadRadius);
                    double d2 = (Nedoextras.spreadCenter.x + Nedoextras.spreadRadius);
                    double d3 = (Nedoextras.spreadCenter.y + Nedoextras.spreadRadius);

                    Collection<Entity> pTargets = new ArrayList<>();
                    pTargets.add(player);

                    SpreadPlayersCommand.Pile[] piles = PlayerSpreaderInvoker.makePiles(randomsource, pTargets.size(), d0, d1, d2, d3);
                    PlayerSpreaderInvoker.spread(Nedoextras.spreadCenter, Nedoextras.spreadRadius, world.getServer().getWorld(World.OVERWORLD), randomsource, d0, d1, d2, d3, 500, piles, false);
                    @SuppressWarnings("unused") double dist = PlayerSpreaderInvoker.getMinDistance(pTargets, world.getServer().getWorld(World.OVERWORLD), piles, 500, false);
                } catch (CommandSyntaxException e) {
                    Nedoextras.LOGGER.error("Failed to teleport player: ", e);
                }
            } else {
                FabricDimensions.teleport(player, player.getServer().getWorld(player.getSpawnPointDimension()), new TeleportTarget(player.getSpawnPointPosition().toCenterPos(), Vec3d.ZERO, 0.0f, 0.0f));
            }
        }
    }
}