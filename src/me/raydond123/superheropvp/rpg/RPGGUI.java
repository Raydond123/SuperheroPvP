package me.raydond123.superheropvp.rpg;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Raymond on 3/27/2015.
 */
public class RPGGUI implements CommandExecutor,Listener {

    SuperheroPvP plugin;

    public RPGGUI(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    Inventory inv;

    public void setupInv() {

        inv = Bukkit.createInventory(null, 45, ChatColor.RED + "" + ChatColor.BOLD + "Character Upgrades");

        ItemStack spiderMan = new ItemStack(Material.WEB);
        ItemMeta spiderMeta = spiderMan.getItemMeta();
        spiderMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "SpiderMan");
        spiderMan.setItemMeta(spiderMeta);
        inv.setItem(10, spiderMan);

        ItemStack batMan = new ItemStack(Material.NETHER_STAR);
        ItemMeta batMeta = batMan.getItemMeta();
        batMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "BatMan");
        batMan.setItemMeta(batMeta);
        inv.setItem(12, batMan);

        ItemStack hulk = new ItemStack(Material.SLIME_BLOCK);
        ItemMeta hulkMeta = hulk.getItemMeta();
        hulkMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Hulk");
        hulk.setItemMeta(hulkMeta);
        inv.setItem(14, hulk);

        ItemStack iceMan = new ItemStack(Material.ICE);
        ItemMeta iceMeta = iceMan.getItemMeta();
        iceMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "IceMan");
        iceMan.setItemMeta(iceMeta);
        inv.setItem(16, iceMan);

        ItemStack deathStroke = new ItemStack(Material.BOW);
        ItemMeta deathMeta = deathStroke.getItemMeta();
        deathMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Death Stroke");
        deathStroke.setItemMeta(deathMeta);
        inv.setItem(28, deathStroke);

        ItemStack jokerMan = new ItemStack(Material.IRON_SWORD);
        ItemMeta jokerMeta = jokerMan.getItemMeta();
        jokerMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Joker");
        jokerMan.setItemMeta(jokerMeta);
        inv.setItem(30, jokerMan);

        ItemStack loki = new ItemStack(Material.STICK);
        ItemMeta lokiMeta = loki.getItemMeta();
        lokiMeta.setDisplayName(ChatColor.DARK_PURPLE + "Loki");
        loki.setItemMeta(lokiMeta);
        inv.setItem(32, loki);

        ItemStack bizarro = new ItemStack(Material.BEDROCK);
        ItemMeta bizarroMeta = bizarro.getItemMeta();
        bizarroMeta.setDisplayName(ChatColor.DARK_PURPLE + "Bizarro");
        bizarro.setItemMeta(bizarroMeta);
        inv.setItem(34, bizarro);

    }

    public void open(Player player) {

        player.openInventory(inv);

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        Inventory inventory = e.getInventory();

        if(inventory.getName().equals(inv.getName())) {

            if(item.getType() == Material.WEB) {

                plugin.spiderman.open(player);
                e.setCancelled(true);

            } else if(item.getType() == Material.NETHER_STAR) {

                plugin.kitLoader.loadKit(player, "Batman");
                e.setCancelled(true);

            } else if(item.getType() == Material.SLIME_BLOCK) {

                plugin.kitLoader.loadKit(player, "Hulk");
                e.setCancelled(true);

            } else if(item.getType() == Material.ICE) {

                plugin.kitLoader.loadKit(player, "Iceman");
                e.setCancelled(true);

            } else if(item.getType() == Material.IRON_SWORD) {

                plugin.kitLoader.loadKit(player, "Joker");
                e.setCancelled(true);

            } else if(item.getType() == Material.STICK) {

                plugin.kitLoader.loadKit(player, "Loki");
                e.setCancelled(true);

            } else if(item.getType() == Material.BOW) {

                plugin.kitLoader.loadKit(player, "DeathStroke");
                e.setCancelled(true);

            } else if(item.getType() == Material.BEDROCK) {

                plugin.kitLoader.loadKit(player, "Bizzarro");
                e.setCancelled(true);

            }

        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            open(player);

        }

        return false;

    }
}
