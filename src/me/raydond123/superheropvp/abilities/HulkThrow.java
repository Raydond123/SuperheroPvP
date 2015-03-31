package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import me.raydond123.superheropvp.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class HulkThrow implements Listener {

    SuperheroPvP plugin;

    public HulkThrow(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();
        Entity entity = e.getRightClicked();

        if (!cooldown.contains(player.getName())) {

            if (held.getType() == Material.STICK && held.hasItemMeta()) {

                if(entity instanceof Player) {

                    player.sendMessage(ChatColor.BLUE + "You used the Hulk Throw ability!");

                    cooldown.add(player.getName());

                    Player otherPlayer = (Player) entity;

                    double x = otherPlayer.getLocation().getX()
                            - player.getLocation().getX();
                    double y = otherPlayer.getLocation().getY()
                            - player.getLocation().getY();
                    double z = otherPlayer.getLocation().getZ()
                            - player.getLocation().getZ();
                    Vector throwVelocity = new Vector(x * 1.5,
                            y + 1, z * 1.5);
                    otherPlayer.setVelocity(throwVelocity);

                    ParticleEffect.EXPLOSION_HUGE.display(0, 0, 0, 0, 1, player.getLocation(), 1000);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Hulk Throw ability is now ready to use!");

                        }

                    }, 20L * plugin.getConfig().getInt("hulkthrow-cooldown"));

                }

            }

        } else {

            player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

        }

    }

}
