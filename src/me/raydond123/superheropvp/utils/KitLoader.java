package me.raydond123.superheropvp.utils;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class KitLoader {

    SuperheroPvP plugin;

    public KitLoader(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    public void loadKit(Player player, String kit) {

        if(kit.equals("Batman")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.fromRGB(0,0,0));
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateMeta.setColor(Color.fromRGB(0,0,0));
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            leggingMeta.setColor(Color.fromRGB(0,0,0));
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            bootsMeta.setColor(Color.fromRGB(0,0,0));
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            plugin.grappleHook.givePlayer(player);

            ItemStack batBomb = new ItemStack(Material.TNT, 1);
            ItemMeta batMeta = batBomb.getItemMeta();
            batMeta.setDisplayName(ChatColor.RED + "Bat Bomb");
            batMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this to throw out a",
                    ChatColor.LIGHT_PURPLE + "bat bomb that detonates on impact!"));
            batBomb.setItemMeta(batMeta);
            player.getInventory().setItem(2, batBomb);

            ItemStack batGlide = new ItemStack(Material.FEATHER, 1);
            ItemMeta glideMeta = batGlide.getItemMeta();
            glideMeta.setDisplayName(ChatColor.AQUA + "Bat Glide");
            glideMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this to glide forward!"));
            batGlide.setItemMeta(glideMeta);
            player.getInventory().setItem(3, batGlide);

            ItemStack batterang = new ItemStack(Material.ARROW, 1);
            ItemMeta metaBat = batterang.getItemMeta();
            metaBat.setDisplayName(ChatColor.YELLOW + "Batterang");
            metaBat.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this to throw a batterang!"));
            batterang.setItemMeta(metaBat);
            player.getInventory().setItem(4, batterang);

            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000000, 1));

        } else if (kit.equals("Hulk")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.GREEN);
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            helmMeta.setColor(Color.GREEN);
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            helmMeta.setColor(Color.GREEN);
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            helmMeta.setColor(Color.GREEN);
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            ItemStack smash = new ItemStack(Material.ANVIL, 1);
            ItemMeta smashMeta = smash.getItemMeta();
            smashMeta.setDisplayName(ChatColor.GREEN + "Hulk Smash");
            smashMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Hulk Smash ability!"));
            smash.setItemMeta(smashMeta);
            player.getInventory().setItem(1, smash);

            ItemStack clap = new ItemStack(Material.BRICK, 1);
            ItemMeta clapMeta = clap.getItemMeta();
            clapMeta.setDisplayName(ChatColor.BLUE + "Hulk Clap");
            clapMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Hulk Clap ability!"));
            clap.setItemMeta(clapMeta);
            player.getInventory().setItem(2, clap);

            ItemStack suction = new ItemStack(Material.HOPPER, 1);
            ItemMeta suctionMeta = suction.getItemMeta();
            suctionMeta.setDisplayName(ChatColor.AQUA + "Hulk Suction");
            suctionMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Hulk Suction ability!"));
            suction.setItemMeta(suctionMeta);
            player.getInventory().setItem(3, suction);

            ItemStack hulkThrow = new ItemStack(Material.STICK, 1);
            ItemMeta throwMeta = hulkThrow.getItemMeta();
            throwMeta.setDisplayName(ChatColor.YELLOW + "Hulk Throw");
            throwMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Hulk Throw ability!"));
            hulkThrow.setItemMeta(throwMeta);
            player.getInventory().setItem(4, hulkThrow);

            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000000, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000000, 1));

        } else if (kit.equals("Spiderman")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.RED);
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateMeta.setColor(Color.BLUE);
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            leggingMeta.setColor(Color.RED);
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            bootsMeta.setColor(Color.BLUE);
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            plugin.grappleHook.givePlayer(player);

            ItemStack web = new ItemStack(Material.WEB, 1);
            ItemMeta webMeta = web.getItemMeta();
            webMeta.setDisplayName(ChatColor.RED + "Web Shot");
            webMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Web Shot ability!"));
            web.setItemMeta(webMeta);
            player.getInventory().setItem(2, web);

            ItemStack surround = new ItemStack(Material.CAULDRON_ITEM, 1);
            ItemMeta surroundMeta = surround.getItemMeta();
            surroundMeta.setDisplayName(ChatColor.GREEN + "Web Surround");
            surroundMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Web Surround ability!"));
            surround.setItemMeta(surroundMeta);
            player.getInventory().setItem(3, surround);

            ItemStack blast = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta blastMeta = blast.getItemMeta();
            blastMeta.setDisplayName(ChatColor.GREEN + "Web Blast");
            blastMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Web Blast ability!"));
            blast.setItemMeta(blastMeta);
            player.getInventory().setItem(4, blast);

            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000000, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000000, 1));

        } else if (kit.equals("Iceman")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.AQUA);
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateMeta.setColor(Color.AQUA);
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            leggingMeta.setColor(Color.AQUA);
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            bootsMeta.setColor(Color.AQUA);
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            ItemStack shooter = new ItemStack(Material.ICE, 1);
            ItemMeta shooterMeta = shooter.getItemMeta();
            shooterMeta.setDisplayName(ChatColor.AQUA + "Ice Shooter");
            shooterMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Ice Shooter ability!"));
            shooter.setItemMeta(shooterMeta);
            player.getInventory().setItem(1, shooter);

            ItemStack freeze = new ItemStack(Material.SNOW_BLOCK, 1);
            ItemMeta freezeMeta = freeze.getItemMeta();
            freezeMeta.setDisplayName(ChatColor.AQUA + "Freeze");
            freezeMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Freeze ability!"));
            freeze.setItemMeta(freezeMeta);
            player.getInventory().setItem(2, freeze);

            ItemStack bash = new ItemStack(Material.PACKED_ICE, 1);
            ItemMeta bashMeta = bash.getItemMeta();
            bashMeta.setDisplayName(ChatColor.AQUA + "Ice Bash");
            bashMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to use",
                    ChatColor.LIGHT_PURPLE + "your Ice Bash ability!"));
            bash.setItemMeta(bashMeta);
            player.getInventory().setItem(3, bash);

            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000000, 1));

        } else if(kit.equalsIgnoreCase("Joker")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.GREEN);
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateMeta.setColor(Color.BLACK);
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            leggingMeta.setColor(Color.GREEN);
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            bootsMeta.setColor(Color.BLACK);
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            ItemStack shooter = new ItemStack(Material.IRON_BARDING, 1);
            ItemMeta shooterMeta = shooter.getItemMeta();
            shooterMeta.setDisplayName(ChatColor.AQUA + "Gun");
            shooterMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "shoot this gun!"));
            shooter.setItemMeta(shooterMeta);
            player.getInventory().setItem(1, shooter);

            ItemStack bomb = new ItemStack(Material.TNT, 1);
            ItemMeta batMeta = bomb.getItemMeta();
            batMeta.setDisplayName(ChatColor.RED + "Bomb");
            batMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this to throw out a",
                    ChatColor.LIGHT_PURPLE + "bomb that detonates on impact!"));
            bomb.setItemMeta(batMeta);
            player.getInventory().setItem(2, bomb);

            ItemStack choke = new ItemStack(Material.LEASH, 1);
            ItemMeta chokeMeta = choke.getItemMeta();
            chokeMeta.setDisplayName(ChatColor.GREEN + "Choke");
            chokeMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item on a",
                    ChatColor.LIGHT_PURPLE + "player to choke them!"));
            choke.setItemMeta(chokeMeta);
            player.getInventory().setItem(3, choke);

            ItemStack dart = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta dartMeta = dart.getItemMeta();
            dartMeta.setDisplayName(ChatColor.DARK_AQUA + "Poison Dart");
            dartMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "shoot a poison dart!"));
            dart.setItemMeta(dartMeta);
            player.getInventory().setItem(4, dart);

            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 1));

        } else if(kit.equalsIgnoreCase("Loki")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.GREEN);
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateMeta.setColor(Color.GREEN);
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            leggingMeta.setColor(Color.GREEN);
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            bootsMeta.setColor(Color.GREEN);
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            ItemStack forceField = new ItemStack(Material.DROPPER, 1);
            ItemMeta forceMeta = forceField.getItemMeta();
            forceMeta.setDisplayName(ChatColor.BLUE + "Force Field");
            forceMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "create a Force Field around you!"));
            forceField.setItemMeta(forceMeta);
            player.getInventory().setItem(1, forceField);

            ItemStack powerBlast = new ItemStack(Material.GLOWSTONE_DUST, 1);
            ItemMeta powerMeta = powerBlast.getItemMeta();
            powerMeta.setDisplayName(ChatColor.RED + "Power Blast");
            powerMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "launch a Power Blast that surrounds you!"));
            powerBlast.setItemMeta(powerMeta);
            player.getInventory().setItem(2, powerBlast);

            ItemStack superStrength = new ItemStack(Material.SUGAR, 1);
            ItemMeta superMeta = superStrength.getItemMeta();
            superMeta.setDisplayName(ChatColor.GREEN + "Super Strength");
            superMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right clicking this item",
                    ChatColor.LIGHT_PURPLE + "will give you super strength!"));
            superStrength.setItemMeta(superMeta);
            player.getInventory().setItem(3, superStrength);

            ItemStack lavaBlast = new ItemStack(Material.LAVA_BUCKET, 1);
            ItemMeta lavaMeta = lavaBlast.getItemMeta();
            lavaMeta.setDisplayName(ChatColor.DARK_RED + "Lava Blast");
            lavaMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "surround yourself in lava!"));
            lavaBlast.setItemMeta(lavaMeta);
            player.getInventory().setItem(4, lavaBlast);

        } else if(kit.equalsIgnoreCase("DeathStroke")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.GREEN);
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateMeta.setColor(Color.BLACK);
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            leggingMeta.setColor(Color.BLACK);
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            bootsMeta.setColor(Color.GREEN);
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            ItemStack knife = new ItemStack(Material.GOLD_SWORD, 1);
            ItemMeta knifeMeta = knife.getItemMeta();
            knifeMeta.setDisplayName(ChatColor.BLUE + "Throwing Knife");
            knifeMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "throw a knife!"));
            knife.setItemMeta(knifeMeta);
            player.getInventory().setItem(1, knife);

            ItemStack sniper = new ItemStack(Material.WOOD_HOE, 1);
            ItemMeta sniperMeta = sniper.getItemMeta();
            sniperMeta.setDisplayName(ChatColor.GREEN + "Sniper");
            sniperMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "shoot your sniper!"));
            sniper.setItemMeta(sniperMeta);
            player.getInventory().setItem(2, sniper);

            ItemStack pistol = new ItemStack(Material.IRON_HOE, 1);
            ItemMeta pistolMeta = pistol.getItemMeta();
            pistolMeta.setDisplayName(ChatColor.BLUE + "Pistol");
            pistolMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "shoot your pistol!"));
            pistol.setItemMeta(pistolMeta);
            player.getInventory().setItem(3, pistol);

            ItemStack cloak = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
            ItemMeta cloakMeta = cloak.getItemMeta();
            cloakMeta.setDisplayName(ChatColor.BLUE + "Invisibility Cloak");
            cloakMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "put on an invisibility cloak!"));
            cloak.setItemMeta(cloakMeta);
            player.getInventory().setItem(4, cloak);

        } else if (kit.equalsIgnoreCase("Bizzarro")) {

            plugin.kits.put(player, kit);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
            helmMeta.setColor(Color.YELLOW);
            helm.setItemMeta(helmMeta);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateMeta.setColor(Color.BLACK);
            chestplate.setItemMeta(chestplateMeta);
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingMeta = (LeatherArmorMeta) legging.getItemMeta();
            leggingMeta.setColor(Color.YELLOW);
            legging.setItemMeta(leggingMeta);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            bootsMeta.setColor(Color.GREEN);
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(legging);
            player.getInventory().setBoots(boots);

            ItemStack flame = new ItemStack(Material.FIREBALL, 1);
            ItemMeta flameMeta = flame.getItemMeta();
            flameMeta.setDisplayName(ChatColor.BLUE + "Flame Breath");
            flameMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "breathe out flames!"));
            flame.setItemMeta(flameMeta);
            player.getInventory().setItem(1, flame);

            ItemStack freezeVision = new ItemStack(Material.DIAMOND, 1);
            ItemMeta freezeVisionMeta = freezeVision.getItemMeta();
            freezeVisionMeta.setDisplayName(ChatColor.BLUE + "Freeze Vision");
            freezeVisionMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "freeze a player!"));
            freezeVision.setItemMeta(freezeVisionMeta);
            player.getInventory().setItem(2, freezeVision);

            ItemStack healing = new ItemStack(Material.REDSTONE, 1);
            ItemMeta healingMeta = healing.getItemMeta();
            healingMeta.setDisplayName(ChatColor.BLUE + "Fast Healing");
            healingMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "heal 2.5 hearts!"));
            healing.setItemMeta(healingMeta);
            player.getInventory().setItem(3, healing);

            ItemStack xRay = new ItemStack(Material.FIREWORK_CHARGE, 1);
            ItemMeta xRayMeta = xRay.getItemMeta();
            xRayMeta.setDisplayName(ChatColor.BLUE + "X-Ray Blast");
            xRayMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "use X-Ray Blast!"));
            xRay.setItemMeta(xRayMeta);
            player.getInventory().setItem(4, xRay);
            
            ItemStack shield = new ItemStack(Material.BEDROCK, 1);
            ItemMeta shieldMeta = shield.getItemMeta();
            shieldMeta.setDisplayName(ChatColor.BLUE + "Shield");
            shieldMeta.setLore(Arrays.asList(
                    ChatColor.LIGHT_PURPLE + "Right click this item to",
                    ChatColor.LIGHT_PURPLE + "put up a shield!"));
            shield.setItemMeta(shieldMeta);
            player.getInventory().setItem(5, shield);
            
        }

    }

}
