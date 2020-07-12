package xyz.crystillizedprison.maxrankup;

import me.clip.ezrankspro.EZAPI;
import me.clip.ezrankspro.EZRanksPro;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.crystillizedprison.maxrankup.commands.MaxRankup;

public final class MaxRankUp extends JavaPlugin {

    public static Economy getEcon() {
        return econ;
    }

    public static Permission getPerms() {
        return perms;
    }


    private static Economy econ = null;
    private static Permission perms = null;

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("Maxrankup").setExecutor(new MaxRankup(this));
        setupEconomy();
        setupPermissions();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
