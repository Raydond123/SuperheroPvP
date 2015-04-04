package me.raydond123.superheropvp.utils;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class KitGUI implements Listener{

    SuperheroPvP plugin;

    public KitGUI(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    public Inventory inv;

    public void setupGUI() {

        inv = Bukkit.createInventory(null, 18, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Character Selector");

        ItemStack spiderMan = new ItemStack(Material.WEB);
        ItemMeta spiderMeta = spiderMan.getItemMeta();
        spiderMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "SpiderMan");
        spiderMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Web Shot",
                ChatColor.BLUE + " - Web Glide",
                ChatColor.BLUE + " - Web Blast",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + " - Speed",
                ChatColor.BLUE + " - Strength,",
                ChatColor.BLUE + " - Jump Boost"));
        spiderMan.setItemMeta(spiderMeta);
        inv.setItem(1, spiderMan);

        ItemStack batMan = new ItemStack(Material.NETHER_STAR);
        ItemMeta batMeta = batMan.getItemMeta();
        batMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "BatMan");
        batMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Batterang",
                ChatColor.BLUE + " - Grapple Hook",
                ChatColor.BLUE + " - Bat Bomb",
                ChatColor.BLUE + " - Bat Glider",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + " - Speed",
                ChatColor.BLUE + " - Strength,",
                ChatColor.BLUE + " - Jump Boost"));
        batMan.setItemMeta(batMeta);
        inv.setItem(3, batMan);

        ItemStack hulk = new ItemStack(Material.SLIME_BLOCK);
        ItemMeta hulkMeta = hulk.getItemMeta();
        hulkMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Hulk");
        hulkMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Hulk Smash",
                ChatColor.BLUE + " - Hulk Clap",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + " - Strength",
                ChatColor.BLUE + " - Jump Boost"));
        hulk.setItemMeta(hulkMeta);
        inv.setItem(5, hulk);

        ItemStack iceMan = new ItemStack(Material.ICE);
        ItemMeta iceMeta = iceMan.getItemMeta();
        iceMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "IceMan");
        iceMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Ice Shooter",
                ChatColor.BLUE + " - Freeze",
                ChatColor.BLUE + " - Ice Bash",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + " - Speed",
                ChatColor.BLUE + " - Strength,"));
        iceMan.setItemMeta(iceMeta);
        inv.setItem(7, iceMan);

        ItemStack deathStroke = new ItemStack(Material.BOW);
        ItemMeta deathMeta = deathStroke.getItemMeta();
        deathMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Death Stroke");
        deathMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Throwing Knife",
                ChatColor.BLUE + " - Sniper",
                ChatColor.BLUE + " - Pistol",
                ChatColor.BLUE + " - Invisibility Cloak",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + " - Speed"));
        deathStroke.setItemMeta(deathMeta);
        inv.setItem(10, deathStroke);
        
        ItemStack jokerMan = new ItemStack(Material.IRON_SWORD);
        ItemMeta jokerMeta = jokerMan.getItemMeta();
        jokerMeta.setDisplayName(ChatColor.DARK_GREEN+ "" + ChatColor.BOLD + "Joker");
        jokerMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Gun",
                ChatColor.BLUE + " - Bomb",
                ChatColor.BLUE + " - Choke",
                ChatColor.BLUE + " - Poison Dart",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + " - Speed"));
        jokerMan.setItemMeta(jokerMeta);
        inv.setItem(12, jokerMan);

        ItemStack loki = new ItemStack(Material.STICK);
        ItemMeta lokiMeta = loki.getItemMeta();
        lokiMeta.setDisplayName(ChatColor.DARK_PURPLE + "Loki");
        lokiMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Force Field",
                ChatColor.BLUE + " - Power Blast",
                ChatColor.BLUE + " - Super Strength",
                ChatColor.BLUE + " - Lava Blast",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + "No Fall Damage",
                ChatColor.BLUE + "Extra Health",
                ChatColor.BLUE + "Speed"));
        loki.setItemMeta(lokiMeta);
        inv.setItem(14, loki);

        ItemStack bizarro = new ItemStack(Material.BEDROCK);
        ItemMeta bizarroMeta = bizarro.getItemMeta();
        bizarroMeta.setDisplayName(ChatColor.DARK_PURPLE + "Bizarro");
        bizarroMeta.setLore(Arrays.asList("",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Abilities:",
                ChatColor.BLUE + " - Flame Breath",
                ChatColor.BLUE + " - Freeze Vision",
                ChatColor.BLUE + " - Fast Healing",
                ChatColor.BLUE + " - X-Ray Blast",
                ChatColor.BLUE + " - Shield",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Perks:",
                ChatColor.BLUE + "Speed"));
        bizarro.setItemMeta(bizarroMeta);
        inv.setItem(16, bizarro);

    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        ItemStack clicked = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(clicked.getType() == Material.COMPASS && clicked.hasItemMeta()) {

                player.openInventory(inv);

            }

        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        final Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        Inventory inventory = e.getInventory();

        if(inventory.getName().equals(inv.getName())) {

            if(item.getType() == Material.WEB) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "Spiderman");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Spiderman");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("Spiderman")) {

                    plugin.kitLoader.loadKit(player, "Spiderman");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Spiderman");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);
                    
                }

            } else if(item.getType() == Material.NETHER_STAR) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "Batman");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Batman");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("Batman")) {

                    plugin.kitLoader.loadKit(player, "Batman");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Batman");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);

                }

            } else if(item.getType() == Material.SLIME_BLOCK) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "Hulk");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Hulk");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("Hulk")) {

                    plugin.kitLoader.loadKit(player, "Hulk");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Hulk");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);

                }

            } else if(item.getType() == Material.ICE) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "Iceman");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Iceman");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("IceMan")) {

                    plugin.kitLoader.loadKit(player, "IceMan");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "IceMan");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);

                }

            } else if(item.getType() == Material.IRON_SWORD) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "Joker");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Joker");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("Joker")) {

                    plugin.kitLoader.loadKit(player, "Joker");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Joker");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);

                }

            } else if(item.getType() == Material.STICK) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "Loki");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Loki");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("Loki")) {

                    plugin.kitLoader.loadKit(player, "Loki");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Loki");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);

                }

            } else if(item.getType() == Material.BOW) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "DeathStroke");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "DeathStroke");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("DeathStroke")) {

                    plugin.kitLoader.loadKit(player, "DeathStroke");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "DeathStroke");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);

                }

            } else if(item.getType() == Material.BEDROCK) {

                if(plugin.dbYaml.get("players." + player.getName()) == null) {

                    plugin.kitLoader.loadKit(player, "Bizzarro");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Bizzarro");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else if (plugin.dbYaml.get("players." + player.getName()).equals("Bizzarro")) {

                    plugin.kitLoader.loadKit(player, "Bizzarro");
                    e.setCancelled(true);
                    player.closeInventory();

                    plugin.dbYaml.set("players." + player.getName(), "Bizzarro");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            plugin.dbYaml.set("players." + player.getName(), null);

                        }

                    }, 20L * 86400);

                } else {

                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 0);

                }

            }

        }

    }

}
