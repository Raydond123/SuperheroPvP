package me.raydond123.superheropvp.abilities;

import de.slikey.effectlib.effect.ShieldEffect;
import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class ForceField implements Listener {

    SuperheroPvP plugin;

    public ForceField(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();
    HashSet<String> field = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.DROPPER && held.hasItemMeta()) {

            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());
                    field.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used ForceField!");

                    ShieldEffect shieldEffect = new ShieldEffect(plugin.effectManager);
                    shieldEffect.setEntity(player);
                    shieldEffect.sphere = true;
                    shieldEffect.iterations = 20 * plugin.getConfig().getInt("forcefield-duration");
                    shieldEffect.start();

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your ForceField ability is now replenished!");
                            field.remove(player.getName());

                        }

                    }, 20L * plugin.getConfig().getInt("forcefield-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is currently under a cooldown!");

                }

            }

        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();

        if(field.contains(player.getName())) {

            for(Player otherPlayer : player.getWorld().getPlayers()) {

                if(otherPlayer != player) {

                    if (player.getLocation().distanceSquared(otherPlayer.getLocation()) <= 2) {

                        double x = otherPlayer.getLocation().getX()
                                - player.getLocation().getX();
                        double y = otherPlayer.getLocation().getY()
                                - player.getLocation().getY();
                        double z = otherPlayer.getLocation().getZ()
                                - player.getLocation().getZ();
                        Vector throwVelocity = new Vector(x * 1.25,
                                y + 0.75, z * 1.25);
                        otherPlayer.setVelocity(throwVelocity);

                    }

                }

            }

        }

    }

}
