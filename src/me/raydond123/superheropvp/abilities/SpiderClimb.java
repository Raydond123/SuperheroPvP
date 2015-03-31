package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class SpiderClimb implements Listener {

    SuperheroPvP plugin;

    public SpiderClimb(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();

        Location eyeLocation = player.getEyeLocation();
        Vector vec = player.getLocation().getDirection();
        Location frontLocation = eyeLocation.add(vec);
        Location bottomLocation = frontLocation.add(0,-1,0);

        if(plugin.kits.get(player) != null) {

            String kit = plugin.kits.get(player);

            if(kit == "Spiderman") {

                if(vec.getY() > -0.65) {

                    if (frontLocation.getBlock().getType() != Material.AIR && bottomLocation.getBlock().getType() != Material.AIR) {

                        if (frontLocation.getBlock().getType().isSolid() && bottomLocation.getBlock().getType().isSolid()) {

                            Vector current = player.getVelocity();
                            Vector vector = current.setY(0.5);

                            player.setVelocity(vector);

                        }

                    }

                }

            }

        }

    }

}
