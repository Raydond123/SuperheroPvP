package me.raydond123.superheropvp.rpg.characters;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import java.io.File;
import java.util.HashMap;

public class Spiderman implements Listener {

    SuperheroPvP plugin;

    public Spiderman(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashMap<String, Inventory> data = new HashMap<String, Inventory>();

    public void open(Player player) {

        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Spiderman Upgrades");

        ItemStack web = new ItemStack(Material.WEB);
        ItemMeta webMeta = web.getItemMeta();
        webMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Web Shot");
        web.setItemMeta(webMeta);
        inv.setItem(46, web);

        ItemStack surround = new ItemStack(Material.CAULDRON_ITEM);
        ItemMeta surroundMeta = surround.getItemMeta();
        surroundMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Web Surround");
        surround.setItemMeta(surroundMeta);
        inv.setItem(49, surround);

        ItemStack blast = new ItemStack(Material.BLAZE_ROD);
        ItemMeta blastMeta = blast.getItemMeta();
        blastMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Web Blast");
        blast.setItemMeta(blastMeta);
        inv.setItem(52, blast);

        File file = plugin.rpgHandler.getInfo(player);
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        int webShot = yaml.getInt("spiderman.web-shot");
        for(int i = 0; i < webShot; i++) {

            int slots = 9 * i;
            int slot = 37 - slots;

            Wool wool = new Wool(DyeColor.LIME);
            ItemStack stack = wool.toItemStack(1);
            inv.setItem(slot, stack);

        }

        int webSurround = yaml.getInt("spiderman.web-surround");
        for(int i = 0; i < webSurround; i++) {

            int slots = 9 * i;
            int slot = 40 - slots;

            Wool wool = new Wool(DyeColor.LIME);
            ItemStack stack = wool.toItemStack(1);
            inv.setItem(slot, stack);

        }

        int webBlast = yaml.getInt("spiderman.web-blast");
        for(int i = 0; i < webBlast; i++) {

            int slots = 9 * i;
            int slot = 37 - slots;

            Wool wool = new Wool(DyeColor.LIME);
            ItemStack stack = wool.toItemStack(1);
            inv.setItem(slot, stack);

        }

        player.openInventory(inv);
        data.put(player.getName(), inv);

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack clicked = e.getCursor();

        if(inv.equals(data.get(player.getName()))) {

            if(clicked.getItemMeta().getDisplayName().contains("Web Shot")) {

                if(plugin.getEconomy().getBalance(player) >= plugin.getConfig().getInt("spiderman.web-shot.price")) {

                    File file = plugin.rpgHandler.getInfo(player);
                    YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

                    int level = yaml.getInt("spiderman.web-shot");

                    if(level < 4) {

                        yaml.set("spiderman.web-shot", level + 1);
                        int webShot = level + 1;
                        for(int i = 0; i < webShot; i++) {

                            int slots = 9 * i;
                            int slot = 37 - slots;

                            Wool wool = new Wool(DyeColor.LIME);
                            ItemStack stack = wool.toItemStack(1);
                            inv.setItem(slot, stack);

                        }
                        plugin.getEconomy().withdrawPlayer(player, plugin.getConfig().getInt("spiderman.web-shot.price"));

                    } else {

                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "This ability is already it's highest level!");

                    }

                } else {

                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You do not have enough money!");

                }

            } else if (clicked.getItemMeta().getDisplayName().contains("Web Surround")) {

                if(plugin.getEconomy().getBalance(player) >= plugin.getConfig().getInt("spiderman.web-surround.price")) {

                    File file = plugin.rpgHandler.getInfo(player);
                    YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

                    int level = yaml.getInt("spiderman.web-surround");

                    if(level < 4) {

                        yaml.set("spiderman.web-surround", level + 1);
                        int webSurround = level + 1;
                        for(int i = 0; i < webSurround; i++) {

                            int slots = 9 * i;
                            int slot = 40 - slots;

                            Wool wool = new Wool(DyeColor.LIME);
                            ItemStack stack = wool.toItemStack(1);
                            inv.setItem(slot, stack);

                        }
                        plugin.getEconomy().withdrawPlayer(player, plugin.getConfig().getInt("spiderman.web-surround.price"));

                    } else {

                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "This ability is already it's highest level!");

                    }

                } else {

                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You do not have enough money!");

                }

            } else if (clicked.getItemMeta().getDisplayName().contains("Web Blast")) {

                if(plugin.getEconomy().getBalance(player) >= plugin.getConfig().getInt("spiderman.web-blast.price")) {

                    File file = plugin.rpgHandler.getInfo(player);
                    YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

                    int level = yaml.getInt("spiderman.web-blast");

                    if(level < 4) {

                        yaml.set("spiderman.web-blast", level + 1);
                        int webBlast = level + 1;
                        for(int i = 0; i < webBlast; i++) {

                            int slots = 9 * i;
                            int slot = 40 - slots;

                            Wool wool = new Wool(DyeColor.LIME);
                            ItemStack stack = wool.toItemStack(1);
                            inv.setItem(slot, stack);

                        }
                        plugin.getEconomy().withdrawPlayer(player, plugin.getConfig().getInt("spiderman.web-blast.price"));

                    } else {

                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "This ability is already it's highest level!");

                    }

                } else {

                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You do not have enough money!");

                }

            }

            e.setCancelled(true);

        }

    }

}
