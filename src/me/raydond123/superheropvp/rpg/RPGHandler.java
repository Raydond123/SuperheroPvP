package me.raydond123.superheropvp.rpg;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class RPGHandler {

    SuperheroPvP plugin;

    public RPGHandler(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    public void save(Player player, YamlConfiguration yaml) {

        try {

            yaml.save(new File("/plugins/SuperheroPvP/users/" + player.getUniqueId() + ".yml"));

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    public File getInfo(Player player) {

        return new File("/plugins/SuperheroPvP/users/" + player.getUniqueId() + ".yml");

    }

}
