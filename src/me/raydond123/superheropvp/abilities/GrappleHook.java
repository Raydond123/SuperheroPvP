package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.snowgears.grapplinghook.api.HookAPI;
import org.bukkit.inventory.ItemStack;

public class GrappleHook implements Listener {

    SuperheroPvP plugin;

    public GrappleHook(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    public void givePlayer(Player player) {

        ItemStack hook = HookAPI.createGrapplingHook(1000);
        player.getInventory().setItem(1, hook);

    }

}
