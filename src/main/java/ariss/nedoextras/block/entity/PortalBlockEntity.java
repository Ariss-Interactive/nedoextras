package ariss.nedoextras.block.entity;

import ariss.nedoextras.RegistryStuff;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class PortalBlockEntity extends BlockEntity {
    public PortalBlockEntity(BlockPos pos, BlockState state) {
        super(RegistryStuff.PORTAL_BE, pos, state);
    }
}
