package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import me.raydond123.superheropvp.utils.CustomEntityFirework;
import me.raydond123.superheropvp.utils.ParticleEffect;
import net.minecraft.server.v1_8_R1.AxisAlignedBB;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.*;

public class FreezeVision implements Listener {

    SuperheroPvP plugin;

    public FreezeVision(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(held.getType() == Material.DIAMOND && held.hasItemMeta()) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Freeze Vision ability!");



                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Freeze Vision ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("freezevision-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    @SuppressWarnings("deprecation")
    public void shoot(Player player, Double damage, int range, double size, int amount) {

        ArrayList<Player> list = getEntitiesLookingAt(player.getEyeLocation(), range, 0, player);

        BlockIterator bIterator = new BlockIterator(player, range);
        Block block = null;

        while(bIterator.hasNext()) {

            block = bIterator.next();

            if(block.getType() != Material.AIR) {

                Location hitBlock = block.getLocation().add(0.5, 0.5, 0.5);
                double distanceFromBlock = hitBlock.distanceSquared(player.getLocation());

                for(Player entity : list.toArray(new Player[list.size()])) {

                    if(player.getLocation().distanceSquared(entity.getLocation()) > distanceFromBlock) {

                        list.remove(entity);

                    }

                }

                break;

            }

        }

        FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withFade(Color.RED).withColor(Color.ORANGE).withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build();

        if(list.isEmpty()) {

            Block targetBlock = null;
            int counter = 0;

            for(Block block1 : player.getLineOfSight(null, range)) {

                if(counter != range) {

                    if (block1.getType() != Material.AIR) {

                        targetBlock = block1;
                        break;

                    }

                } else {

                    targetBlock = block1;
                    break;

                }

                counter++;

            }

            Location location = player.getLocation();
            Location target = targetBlock.getLocation();
            Vector link = target.add(0.5, 0.5, 0.5).toVector().subtract(location.add(0, 1, 0).toVector());
            float length = (float) link.length();
            link.normalize();

            double ratio = 0.5;
            double particles = length * 1.5;
            Vector v = link.multiply(ratio);
            Location loc = location.clone().subtract(v);
            for (int i = 0; i < particles; i++) {
                loc.add(v);
                ParticleEffect.FIREWORKS_SPARK.display(0.5F, 0.5F, 0.5F, 0.5F, 10, loc, 1000);
            }

            FireworkEffect blueEffect = FireworkEffect.builder().trail(true).flicker(false).withFade(Color.BLUE).withColor(Color.AQUA).withColor(Color.AQUA).with(FireworkEffect.Type.BALL).build();
            CustomEntityFirework.spawn(targetBlock.getLocation().add(0.5, 0.5, 0.5), blueEffect);

        } else {

            Player lastPlayer = list.get(list.size() - 1);
            Vector line = lastPlayer.getLocation().add(0, 1, 0).toVector().subtract(player.getLocation().toVector());
            float length = (float) line.length();
            line.normalize();

            double ratio = 0.5;
            Vector vector = line.multiply(ratio);
            Location loc = player.getLocation().add(0, 1, 0).clone().subtract(vector);

            for (int i = 0; i < length; i++) {

                if(loc.equals(lastPlayer.getLocation().add(0, 1, 0))) {

                    break;

                } else {

                    loc.add(vector);
                    ParticleEffect.FIREWORKS_SPARK.display(0.5F, 0.5F, 0.5F, 0.5F, 10, loc, 1000);

                }

            }

            for(final Player p : list) {

                final int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

                    @Override
                    public void run() {

                        p.setVelocity(new Vector(0, 0, 0));

                        Location loc1 = p.getLocation();
                        Location loc2 = p.getLocation().add(0, 1, 0);

                        p.getWorld().playEffect(loc1, Effect.STEP_SOUND, 5);
                        p.getWorld().playEffect(loc2, Effect.STEP_SOUND, 5);

                    }

                }, 0L, 1L);

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    @Override
                    public void run() {

                        Bukkit.getScheduler().cancelTask(task);

                    }

                }, 20L * plugin.getConfig().getInt("freezevision-time"));

            }

        }

    }

    private ArrayList<Player> getEntitiesLookingAt(Location eye, int distance, double border, Player... ignore) {

        ArrayList<Player> entities = new ArrayList<Player>();

        for(Player entity : getEntitiesInLine(eye, distance, ignore)) {

            if(isLookingAt(entity, eye)) {
                entities.add(entity);
            }

        }

        return entities;
    }

    public  boolean isLookingAt(Entity entity, Location eye) {
        double yaw = eye.getYaw() > 0 ? eye.getYaw() : 360 - Math.abs(eye.getYaw()); // remove negative degrees
        yaw += 90; // rotate +90 degrees
        if (yaw > 360)
            yaw -= 360;
        yaw  = (yaw  * Math.PI) / 180;
        double pitch  = ((eye.getPitch() + 90)  * Math.PI) / 180;

        AxisAlignedBB box = ((CraftLivingEntity) entity).getHandle().getBoundingBox();

        // two dimensional coordinates that correspond to the corners of the bounding box on the horizontal plane (x, z)
        double[][] hCoords = new double[][] {
                {box.a, box.c}, {box.d, box.c}, {box.a, box.f}, {box.d, box.f}
        };

        Double[] thetas = new Double[4];

        // convert to theta in polar coordinates
        for (int t = 0; t < 4; t++)
            thetas[t] = toPolar(hCoords[t][0] - eye.getX(), hCoords[t][1] - eye.getZ());

        // Calculate the required range for this entity
        Range<Double, Double> yawRange = getLargestArcRange(thetas);

        // Doing this for pitch is a bit more difficult, we need to use all corners of the bounding box in 3D space
        double[][] corners = new double[][] {
                {box.a, box.b, box.c}, {box.d, box.b, box.c}, {box.a, box.b, box.f}, {box.d, box.b, box.f},
                {box.a, box.e, box.c}, {box.d, box.e, box.c}, {box.a, box.e, box.f}, {box.d, box.e, box.f},
        };
        Double[] phis = new Double[8];
        for (int t = 0; t < 8; t++) {
            double xo = corners[t][0] - eye.getX();
            double yo = corners[t][1] - eye.getY();
            double zo = corners[t][2] - eye.getZ();
            // convert to phi angle in spherical coordinates
            phis[t] = Math.acos(yo / Math.sqrt((xo * xo) + (yo * yo) + (zo * zo)));
        }

        Range<Double, Double> pitchRange = getLargestArcRange(phis);

        return inside(yaw, yawRange.getLowest(), yawRange.getHighest()) && inside(pitch, pitchRange.getLowest(), pitchRange.getHighest());
    }
    // Sorts combinations of angles and returns the combination of angles with the largest distance between them
    public  Range<Double, Double> getLargestArcRange(Double[] angles) {
        Set<Set<Double>> combinations = getCombinationsFor(Arrays.asList(angles), 2);
        Range<Double, Double> largestRange = null;
        Double largest = null;
        for (Set<Double> combo : combinations) {
            Double[] array = combo.toArray(new Double[2]);
            double arc = distance(array[0], array[1]);

            if (largest == null || arc > largest) {
                largest = arc;
                largestRange = new Range<Double, Double>(array[0], array[1]);
            }
        }
        if (largestRange != null && largestRange.getLowest() > largestRange.getHighest())
            largestRange = new Range<Double, Double>(largestRange.getHighest(), largestRange.getLowest());
        return largestRange;
    }

    // Found from stackoverflow somewhere, because I'm lazy. I rewrote it to use generics, it's useful for sorting.
    public  <T> Set<Set<T>> getCombinationsFor(List<T> group, int k) {

        Set<Set<T>> allCombos = new HashSet<Set<T>>();
        if (k == 0) {
            allCombos.add(new HashSet<T>());
            return allCombos;
        }
        if (k > group.size())
            return allCombos;

        List<T> groupWithoutX = new ArrayList<T>(group);
        T x = groupWithoutX.remove(groupWithoutX.size()-1);

        Set<Set<T>> combosWithoutX = getCombinationsFor(groupWithoutX, k);
        Set<Set<T>> combosWithX = getCombinationsFor(groupWithoutX, k-1);
        for (Set<T> combo : combosWithX)
            combo.add(x);
        allCombos.addAll(combosWithoutX);
        allCombos.addAll(combosWithX);
        return allCombos;
    }

    // distance between two angles, in radians
    private  double distance(double r1, double r2) {
        double d = Math.abs(r2 - r1);
        if (d <= Math.PI)
            return d;
        else return 2 * Math.PI - d;
    }

    // [0, 2pi)
    private  double toPolar(double x, double y) {
        double theta = Math.atan2(y, x);
        if (theta < 0)
            return 2 * Math.PI + theta;
        else return theta;
    }

    private  boolean inside(double theta, double small, double large) {
        // If the range includes 0 rad
        if (large - small > Math.PI)
            return theta > large || theta < small;
        else return theta < large && theta > small;
    }

    private  class Range<L extends Number, H extends Number> {
        L lowest;
        H highest;
        Range(L lowest, H highest) {
            this.lowest = lowest;
            this.highest = highest;
        }
        public L getLowest() {
            return lowest;
        }
        public H getHighest() {
            return highest;
        }
        @SuppressWarnings("unused")
        public void setKey(L lowest) {
            this.lowest = lowest;
        }
        @SuppressWarnings("unused")
        public void setValue(H highest) {
            this.highest = highest;
        }
    }

    public  List<Player> getEntitiesInLine(Location origin,
                                           double length, Player... ignore) {
        ArrayList<Chunk> chunks = new ArrayList<Chunk>();

        BlockIterator iterator = new BlockIterator(origin, 0,
                (int) Math.ceil(length));
        while (iterator.hasNext()) {
            Chunk chunk = iterator.next().getChunk();
            if (!chunks.contains(chunk))
                chunks.add(chunk);
        }

        ArrayList<Player> entities = new ArrayList<Player>();

        for (Chunk chunk : chunks) {
            for (Entity e : chunk.getEntities()) {
                if (e instanceof Player
                        && (ignore.length == 0 || !Arrays.asList(ignore)
                        .contains(e)))
                    entities.add((Player) e);
            }
        }

        return entities;
    }

}
