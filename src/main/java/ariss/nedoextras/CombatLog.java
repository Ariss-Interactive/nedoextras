package ariss.nedoextras;

import ariss.nedoextras.persistence.ExtrasData;
import ariss.nedoextras.persistence.ExtrasState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.time.Instant;

public class CombatLog {
    private CombatLog() {} // utility class
    public static void CheckDamage(Entity ent) {
        if (!(ent instanceof PlayerEntity plr))
            return;

        ExtrasData state = ExtrasState.getPlayerState(plr);
        state.lastCombatLog = Instant.now().getEpochSecond();
        ExtrasState.markDirty(plr.getServer());
    }
}
