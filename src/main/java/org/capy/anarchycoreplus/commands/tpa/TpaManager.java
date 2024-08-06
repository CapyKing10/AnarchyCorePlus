package org.capy.anarchycoreplus.commands.tpa;

import org.bukkit.entity.Player;
import org.capy.anarchycoreplus.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TpaManager {

    private final Map<UUID, UUID> tpaRequests;

    public TpaManager() {
        this.tpaRequests = new HashMap<>();
    }

    public void sendTpaRequest(Player sender, Player target) {
        tpaRequests.put(target.getUniqueId(), sender.getUniqueId());
    }

    public UUID getRequestSender(UUID targetId) {
        return tpaRequests.get(targetId);
    }

    public void removeRequest(UUID targetId) {
        tpaRequests.remove(targetId);
    }

    public boolean hasRequest(UUID targetId) {
        return tpaRequests.containsKey(targetId);
    }

    public int getDelay() {
        return Utils.getConfig().getInt("tpa-command.delay");
    }
}
