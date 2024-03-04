package dev.vacariu.vehicletrunkblock;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
public class EventHandler implements Listener {

    private final VehicleTrunkBlock pl;

    public EventHandler(VehicleTrunkBlock pl) {
        this.pl = pl;
    }

    @Deprecated
    @org.bukkit.event.EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player) || event.getCurrentItem() == null) {
            return;
        }
        if (pl.vehiclesMain.getPlayerVehicle(player) != null) {
            if (pl.blacklistedItemMaterial.contains(event.getCurrentItem().getType())) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.errorMessage));
                event.setCancelled(true);
            } else {
                for (String itemName : pl.blacklistedItemNames) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains(itemName)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.errorMessage));
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}
