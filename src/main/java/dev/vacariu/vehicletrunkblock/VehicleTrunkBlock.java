package dev.vacariu.vehicletrunkblock;

import es.pollitoyeye.vehicles.VehiclesMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class VehicleTrunkBlock extends JavaPlugin {

    public VehiclesMain vehiclesMain;
    public List<String> blacklistedItemNames = new ArrayList<>();
    public List<Material> blacklistedItemMaterial = new ArrayList<>();
    public String errorMessage;
    @Deprecated
    @Override
    public void onEnable() {
        saveDefaultConfig();
        vehiclesMain = VehiclesMain.getPlugin();
        FileConfiguration cfg = getConfig();
        errorMessage = cfg.getString("errorMessage");
        blacklistedItemNames.addAll(cfg.getStringList("blacklistedItemNames"));
        for (String mat : cfg.getStringList("blacklistedItemMaterial")) {
            try {
                blacklistedItemMaterial.add(Material.valueOf(mat));
            } catch (Exception ex) {
                getLogger().info(ChatColor.translateAlternateColorCodes('&', "&c[VehicleTrunkBlock] Wrong item type, skipping..."));
            }
        }
        getServer().getPluginManager().registerEvents(new EventHandler(this), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
