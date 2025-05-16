package ariss.nedoextras.persistence;

import ariss.nedoextras.Nedoextras;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;

public class ExtrasState extends PersistentState {
    public HashMap<String, ExtrasData> players = new HashMap<>();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound playersNbt = new NbtCompound();
        players.forEach((name, playerData) -> {
            NbtCompound playerNbt = new NbtCompound();
            playerNbt.putLong("lastCombatLog", playerData.lastCombatLog);
            playersNbt.putBoolean("firstJoin", playerData.firstJoin);

            playersNbt.put(name, playerNbt);
        });
        nbt.put("players", playersNbt);

        return nbt;
    }

    public static ExtrasState createFromNbt(NbtCompound tag) {
        ExtrasState state = new ExtrasState();

        NbtCompound playersNbt = tag.getCompound("players");
        playersNbt.getKeys().forEach(key -> {
            ExtrasData playerData = new ExtrasData();
            playerData.lastCombatLog = playersNbt.getCompound(key).getLong("lastCombatLog");
            playerData.firstJoin = playersNbt.getCompound(key).getBoolean("firstJoin");

            state.players.put(key, playerData);
        });

        return state;
    }

    public static ExtrasState createNew() {
        ExtrasState state = new ExtrasState();
        state.players = new HashMap<>();
        return state;
    }

    public static ExtrasState getServerState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
        ExtrasState state = persistentStateManager.getOrCreate(ExtrasState::createFromNbt, ExtrasState::createNew,Nedoextras.MOD_ID);
        state.markDirty();

        return state;
    }

    public static ExtrasData getPlayerState(LivingEntity player) {
        ExtrasState serverState = getServerState(player.getWorld().getServer());

        return serverState.players.computeIfAbsent(player.getName().getString(), name -> new ExtrasData());
    }

    public static void markDirty(MinecraftServer server) {
        getServerState(server).markDirty();
    }
}