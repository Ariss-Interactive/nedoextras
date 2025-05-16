package ariss.nedoextras;

import ariss.nedoextras.block.PortalBlock;
import ariss.nedoextras.block.entity.PortalBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class RegistryStuff {
    private RegistryStuff() {}

    // Blocks
    public static final Block PORTAL_BLOCK = register(new PortalBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).noCollision().luminance((state) -> 15).strength(-1.0F, 3600000.0F).dropsNothing().pistonBehavior(PistonBehavior.BLOCK)), "portal");
    public static final BlockEntityType<PortalBlockEntity> PORTAL_BE = register("portal", FabricBlockEntityTypeBuilder.create(PortalBlockEntity::new, PORTAL_BLOCK).build());

    // Sounds
    public static final SoundEvent BWOINK = register("bwoink");

    public static Block register(Block block, String name) {
        Identifier id = new Identifier(Nedoextras.MOD_ID, name);

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static SoundEvent register(String id) {
        var identifier = new Identifier(Nedoextras.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Nedoextras.MOD_ID, path), blockEntityType);
    }

    public static void init() {}
}