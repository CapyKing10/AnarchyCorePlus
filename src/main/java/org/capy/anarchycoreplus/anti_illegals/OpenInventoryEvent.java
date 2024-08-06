package org.capy.anarchycoreplus.anti_illegals;

import org.capy.anarchycoreplus.utils.IllegalUtils;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import static org.capy.anarchycoreplus.utils.Utils.*;

public class OpenInventoryEvent implements Listener {

    @EventHandler
    public void onInventoryEvent(InventoryOpenEvent e){
        if (e.getInventory().getType() != InventoryType.HOPPER) {
            return;
        }

        if (e.getPlayer().isOp() || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }

        for (ItemStack it : e.getPlayer().getInventory().getContents()) {
            if (it == null) {
                return;
            }

            if (!IllegalUtils.isIllegal(it)) {
                return;
            }

            for (ItemStack illegal : e.getPlayer().getInventory().getContents()) {
                if (illegal == null) {
                    return;
                }

                illegal.setAmount(O);
            }
        }
    }

}
