package ariss.nedoextras;

import ariss.nedoextras.commands.CommandRegistrar;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.joml.Vector2f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nedoextras implements ModInitializer {
    public static final RegistryKey<World> spawnKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier("nedoextras", "spawn"));
    public static final TeleportTarget spawnTarget = new TeleportTarget(new Vec3d(0d, 0d, 0d), Vec3d.ZERO, 0.0f, 90.0f);
    public static final int spreadRadius = 2000;
    public static final Vec2f spreadCenter = new Vec2f(0.0f, 0.0f);
    public static final String MOD_ID = "nedoextras";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess, registrationEnvironment) -> {
            CommandRegistrar.registerCommands(dispatcher);
        });

        RegistryStuff.init();
    }
}