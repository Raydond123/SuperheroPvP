package me.raydond123.superheropvp.listeners;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoinListener implements Listener {

    SuperheroPvP plugin;

    public PlayerJoinListener(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        plugin.kits.remove(player);

        File file = plugin.rpgHandler.getInfo(player);

        if(!file.exists()) {

            Bukkit.broadcastMessage("test");

            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

            yaml.set("spiderman.web-shot", 1);
            yaml.set("spiderman.web-surround", 1);
            yaml.set("spiderman.web-blast", 1);

            try {

                yaml.save(file);

            } catch(Exception exception) {

                exception.printStackTrace();

            }

        }

    }

}
