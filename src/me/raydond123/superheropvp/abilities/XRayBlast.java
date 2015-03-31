package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class XRayBlast implements Listener {

    SuperheroPvP plugin;

    public XRayBlast(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(held.getType() == Material.FIREWORK_CHARGE && held.hasItemMeta()) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your X-Ray Blast ability!");

                    player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 3);

                    for(Player player1 : Bukkit.getOnlinePlayers()) {

                        if(player1 != player) {

                            if(player.getLocation().distanceSquared(player1.getLocation()) <= 16) {

                                double x = player1.getLocation().getX()
                                        - player.getLocation().getX();
                                double y = player1.getLocation().getY()
                                        - player.getLocation().getY();
                                double z = player1.getLocation().getZ()
                                        - player.getLocation().getZ();
                                Vector throwVelocity = new Vector(x * 1.5,
                                        y + 1, z * 1.5);
                                player1.setVelocity(throwVelocity);

                            }

                        }

                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your X-Ray Blast ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("xrayblast-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

}
