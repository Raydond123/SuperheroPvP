package me.raydond123.superheropvp.listeners;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    SuperheroPvP plugin;

    public PlayerDeathListener(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = e.getEntity();

        plugin.kits.remove(player);

    }

}
