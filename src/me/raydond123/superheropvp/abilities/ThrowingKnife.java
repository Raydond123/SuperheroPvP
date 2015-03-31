package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ThrowingKnife implements Listener {

    SuperheroPvP plugin;

    public ThrowingKnife(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashMap<String, Integer> data = new HashMap<String, Integer>();

    @EventHandler
    public void onShoot(PlayerInteractEvent e) {

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (e.getPlayer().getItemInHand().getType() == Material.GOLD_SWORD) {

                final Player player = e.getPlayer();

                player.sendMessage(ChatColor.BLUE + "You used Throwing Knife!");

                final Entity drop = player.getWorld().dropItemNaturally(player.getEyeLocation(), new ItemStack(Material.GOLD_SWORD));
                drop.setVelocity(player.getLocation().getDirection());

                player.setItemInHand(null);
                player.updateInventory();

                final int checker = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

                    @Override
                    public void run() {

                        List<Entity> close = drop.getNearbyEntities(1, 1, 1);
                        for(Entity e : close) {

                            if(e instanceof Player) {

                                Player otherPlayer = (Player) e;

                                if(otherPlayer != player) {

                                    drop.remove();

                                    ItemStack knife = new ItemStack(Material.GOLD_SWORD, 1);
                                    ItemMeta knifeMeta = knife.getItemMeta();
                                    knifeMeta.setDisplayName(ChatColor.BLUE + "Throwing Knife");
                                    knifeMeta.setLore(Arrays.asList(
                                            ChatColor.LIGHT_PURPLE + "Right click this item to",
                                            ChatColor.LIGHT_PURPLE + "throw a knife!"));
                                    knife.setItemMeta(knifeMeta);
                                    player.getInventory().setItem(1, knife);

                                }

                            }

                        }

                    }

                }, 0L, 1L);

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    @Override
                    public void run() {

                        if(drop != null) {

                            drop.remove();

                            ItemStack knife = new ItemStack(Material.GOLD_SWORD, 1);
                            ItemMeta knifeMeta = knife.getItemMeta();
                            knifeMeta.setDisplayName(ChatColor.BLUE + "Throwing Knife");
                            knifeMeta.setLore(Arrays.asList(
                                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                                    ChatColor.LIGHT_PURPLE + "throw a knife!"));
                            knife.setItemMeta(knifeMeta);
                            player.getInventory().setItem(1, knife);

                            if(checker != 0) {

                                Bukkit.getScheduler().cancelTask(checker);

                            }

                        }

                    }

                }, 20L * plugin.getConfig().getInt("throwingknife-return-delay"));

            }

        }

    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {

        if (data.containsKey(e.getPlayer().getName())) {

            if (e.getItem().getType() == EntityType.DROPPED_ITEM) {

                Item item = e.getItem();

                if (item.getItemStack().getType() == Material.GOLD_SWORD) {

                    if(item.getEntityId() != data.get(e.getPlayer().getName())) {

                        e.setCancelled(true);

                    }

                }

            }

        }

    }

}
