package me.raydond123.superheropvp;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import me.raydond123.superheropvp.listeners.PlayerDeathListener;
import me.raydond123.superheropvp.rpg.RPGGUI;
import me.raydond123.superheropvp.rpg.RPGHandler;
import me.raydond123.superheropvp.rpg.characters.Spiderman;
import me.raydond123.superheropvp.utils.KitLoader;
import me.raydond123.superheropvp.commands.KitCommand;
import me.raydond123.superheropvp.listeners.PlayerJoinListener;
import me.raydond123.superheropvp.utils.FireGun;
import me.raydond123.superheropvp.utils.KitGUI;
import me.raydond123.superheropvp.abilities.*;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_8_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public class SuperheroPvP extends JavaPlugin {

    public Logger logger = Logger.getLogger("Minecraft");

    public FireGun fireGun;

    public EffectManager effectManager;

    public static Economy economy = null;

    KitCommand kitCommand;
    PlayerDeathListener playerDeathListener;
    PlayerJoinListener playerJoinListener;
    BatBomb batBomb;
    BatGlide batGlide;
    Batterang batterang;
    HulkSmash hulkSmash;
    HulkClap hulkClap;
    HulkSuction hulkSuction;
    HulkThrow hulkThrow;
    WebShot webShot;
    IceShooter iceShooter;
    Freeze freeze;
    SpiderClimb spiderClimb;
    SpiderSurround spiderSurround;
    Gun gun;
    Choke choke;
    PoisonDart poisonDart;
    SuperStrength superStrength;
    PowerBlast powerBlast;
    ForceField forceField;
    ThrowingKnife throwingKnife;
    InvisibilityCloak invisibilityCloak;
    Sniper sniper;
    Pistol pistol;
    FreezeVision freezeVision;
    FlameBreath flameBreath;
    FastHealing fastHealing;
    Shield shield;
    IceBash iceBash;
    XRayBlast xRayBlast;
    LavaBlast lavaBlast;
    IcePath icePath;
    public Vec3D vec3D;
    public GrappleHook grappleHook;
    public KitLoader kitLoader;
    public KitGUI kitGUI;

    public Spiderman spiderman;

    RPGGUI rpggui;
    public RPGHandler rpgHandler;

    public void onEnable() {

        saveDefaultConfig();
        saveConfig();

        logger.info("[SuperHeroPvP] Plugin created by: Raydond123 (J4va)");
        logger.info("[SuperHeroPvP] The plugin has been enabled!");

        vec3D = new Vec3D(0, 0, 0);

        fireGun = new FireGun(this);

        EffectLib lib = EffectLib.instance();
        effectManager = new EffectManager(lib);

        playerDeathListener = new PlayerDeathListener(this);
        playerJoinListener = new PlayerJoinListener(this);
        hulkClap = new HulkClap(this);
        hulkSmash = new HulkSmash(this);
        hulkSuction = new HulkSuction(this);
        hulkThrow = new HulkThrow(this);
        webShot = new WebShot(this);
        freeze = new Freeze(this);
        spiderSurround = new SpiderSurround(this);
        iceShooter = new IceShooter(this);
        batBomb = new BatBomb(this);
        lavaBlast = new LavaBlast(this);
        batGlide = new BatGlide(this);
        batterang = new Batterang(this);
        spiderClimb = new SpiderClimb(this);
        iceBash = new IceBash(this);
        gun = new Gun(this);
        choke = new Choke(this);
        superStrength = new SuperStrength(this);
        powerBlast = new PowerBlast(this);
        forceField = new ForceField(this);
        poisonDart = new PoisonDart(this);
        throwingKnife = new ThrowingKnife(this);
        grappleHook = new GrappleHook(this);
        sniper = new Sniper(this);
        pistol = new Pistol(this);
        freezeVision = new FreezeVision(this);
        flameBreath = new FlameBreath(this);
        fastHealing = new FastHealing(this);
        shield = new Shield(this);
        xRayBlast = new XRayBlast(this);
        invisibilityCloak = new InvisibilityCloak(this);
        kitCommand = new KitCommand(this);
        kitLoader = new KitLoader(this);
        kitGUI = new KitGUI(this);
        icePath = new IcePath(this);

        spiderman = new Spiderman(this);

        rpggui = new RPGGUI(this);
        rpgHandler = new RPGHandler(this);

        rpggui.setupInv();

        kitGUI.setupGUI();

        Bukkit.getPluginManager().registerEvents(playerDeathListener, this);
        Bukkit.getPluginManager().registerEvents(playerJoinListener, this);
        Bukkit.getPluginManager().registerEvents(hulkSmash, this);
        Bukkit.getPluginManager().registerEvents(hulkSuction, this);
        Bukkit.getPluginManager().registerEvents(hulkThrow, this);
        Bukkit.getPluginManager().registerEvents(spiderSurround, this);
        Bukkit.getPluginManager().registerEvents(hulkClap, this);
        Bukkit.getPluginManager().registerEvents(webShot, this);
        Bukkit.getPluginManager().registerEvents(iceShooter, this);
        Bukkit.getPluginManager().registerEvents(batBomb, this);
        Bukkit.getPluginManager().registerEvents(batGlide, this);
        Bukkit.getPluginManager().registerEvents(freeze, this);
        Bukkit.getPluginManager().registerEvents(gun, this);
        Bukkit.getPluginManager().registerEvents(choke, this);
        Bukkit.getPluginManager().registerEvents(superStrength, this);
        Bukkit.getPluginManager().registerEvents(powerBlast, this);
        Bukkit.getPluginManager().registerEvents(forceField, this);
        Bukkit.getPluginManager().registerEvents(poisonDart, this);
        Bukkit.getPluginManager().registerEvents(throwingKnife, this);
        Bukkit.getPluginManager().registerEvents(iceBash, this);
        Bukkit.getPluginManager().registerEvents(sniper, this);
        Bukkit.getPluginManager().registerEvents(invisibilityCloak, this);
        Bukkit.getPluginManager().registerEvents(spiderClimb, this);
        Bukkit.getPluginManager().registerEvents(freezeVision, this);
        Bukkit.getPluginManager().registerEvents(flameBreath, this);
        Bukkit.getPluginManager().registerEvents(fastHealing, this);
        Bukkit.getPluginManager().registerEvents(lavaBlast, this);
        Bukkit.getPluginManager().registerEvents(shield, this);
        Bukkit.getPluginManager().registerEvents(xRayBlast, this);
        Bukkit.getPluginManager().registerEvents(batterang, this);
        Bukkit.getPluginManager().registerEvents(grappleHook, this);
        Bukkit.getPluginManager().registerEvents(pistol, this);
        Bukkit.getPluginManager().registerEvents(icePath, this);

        Bukkit.getPluginManager().registerEvents(kitGUI, this);
        Bukkit.getPluginManager().registerEvents(rpggui, this);
        Bukkit.getPluginManager().registerEvents(spiderman, this);

        getCommand("character").setExecutor(kitCommand);
        getCommand("characters").setExecutor(kitCommand);
        getCommand("upgrade").setExecutor(rpggui);

        iceShooter.startClock();
        poisonDart.startClock();
        gun.startClock();
        pistol.startClock();
        sniper.startClock();

        setupEconomy();

        setUserData();

        saveDb();

    }

    File dbFile = new File("/plugins/SuperheroPvP/database.yml");
    public YamlConfiguration dbYaml = YamlConfiguration.loadConfiguration(dbFile);

    public void saveDb() {

        try {

            dbYaml.save(dbFile);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void onDisable() {

        logger.info("[SuperHeroPvP] The plugin has been disabled!");

        effectManager.dispose();

    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public HashMap<Player, String> kits = new HashMap<Player, String>();

    public Economy getEconomy() {

        return economy;

    }

    public void setUserData() {

        File userFiles;

        try{

            userFiles = new File("/plugins/SuperheroPvP/users");

            if(!userFiles.exists()) {

                userFiles.mkdirs();

            }

        } catch(Exception exception) {

            exception.printStackTrace();

        }

    }

}
