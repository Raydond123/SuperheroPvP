package me.raydond123.superheropvp.abilities;


import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;

public class SuperStrength implements Listener {

    SuperheroPvP plugin;

    public SuperStrength(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.SUGAR && held.hasItemMeta()) {

            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Super Strength Ability!");

                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * plugin.getConfig().getInt("superstrength-strength-duration"), 2));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * plugin.getConfig().getInt("superstrength-speed-duration"), 2));

                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                                @Override
                                public void run() {

                                    cooldown.remove(player.getName());
                                    player.sendMessage(ChatColor.BLUE + "Your Super Strength ability is ready to use again!");

                                }

                            }, 20L * plugin.getConfig().getInt("superstrength-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

}
