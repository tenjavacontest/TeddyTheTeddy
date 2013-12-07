package me.teddytheteddy.tenjava.dec.themetwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author TeddyTheTeddy
 *
 */
public class Main extends JavaPlugin {

    //Variables
    public static Map<String, Entity> IronMen = new HashMap<String, Entity>();
    public static Map<String, Location> Morphing = new HashMap<String, Location>();

    /**
     * What happens on enable
     */
    @Override
    public void onEnable() {

        //Registering Events
        Bukkit.getPluginManager().registerEvents(new Events(), this);

    }

    /**
     * What happens on disable - Kills all Iron golem
     */
    @Override
    public void onDisable() {
        for (Entity e : Main.IronMen.values()) {
            e.remove();
        }
    }

    /**
     * What happens on command
     *
     * @param sender
     * @param cmd
     * @param commandLabel
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("ironman")) {
                final Player p = (Player) sender;

                String prefix = "&7[&cIron Man&7] &r";
                if (p.hasPermission("ironman.be")) {
                    if (!Main.IronMen.containsKey(p.getName())) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&fYou are now morphing into &cIron Man&f!"));
                        Main.Morphing.put(p.getName(), p.getLocation());

                        morphEffects(p);

                        int loop = 0;
                        while (loop != 5) {
                            final float f1 = 6 - loop;
                            final int loopAmount = loop;
                            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {

                                @Override
                                public void run() {
                                    morphEffects(p);
                                    p.playSound(p.getLocation(), Sound.EXPLODE, f1, 10F);
                                }

                            }, 20 * loop);
                            loop++;
                        }

                        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {

                            @Override
                            public void run() {
                                morphEffects(p);
                                p.playSound(p.getLocation(), Sound.EXPLODE, 100F, 10F);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 95);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 95);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 95);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 95);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 95);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 152);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 152);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 152);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 152);
                                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 152);

                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.9F, 0.9F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.8F, 0.8F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.7F, 0.7F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.6F, 0.6F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.5F, 0.5F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.4F, 0.4F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.3F, 0.3F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.2F, 0.2F);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.1F, 0.1F);

                                morphPlayer(p);
                            }

                        }, 20 * 5);
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&fYou are now morphing into &cYourself&f!"));
                        Main.Morphing.put(p.getName(), p.getLocation());

                        unMorphPlayer(p);
                    }

                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&fYou do not have permission to run this command"));
                }
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Morphs a player into Iron Man
     *
     * @param p
     */
    private void morphPlayer(Player p) {
        IronGolem golem = (IronGolem) Bukkit.getWorld(p.getLocation().getWorld().getName()).spawnEntity(p.getLocation(), EntityType.IRON_GOLEM);
        golem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cIron Man"));
        golem.setCustomNameVisible(true);
        golem.setPlayerCreated(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, true));
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.hidePlayer(p);
        }
        Main.Morphing.remove(p.getName());
        Main.IronMen.put(p.getName(), golem);
        golem.setTarget(p);
    }

    /**
     * un morphs a player from Iron Man
     *
     * @param p
     */
    private void unMorphPlayer(Player p) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.showPlayer(p);
        }
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        Main.IronMen.get(p.getName()).remove();
        Main.IronMen.remove(p.getName());
        Main.Morphing.remove(p.getName());
    }

    private void morphEffects(Player p) {
        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);

        p.playEffect(p.getLocation(), Effect.EXTINGUISH, 1);
        p.playEffect(p.getLocation(), Effect.EXTINGUISH, 1);
        p.playEffect(p.getLocation(), Effect.EXTINGUISH, 1);
        p.playEffect(p.getLocation(), Effect.EXTINGUISH, 1);
        p.playEffect(p.getLocation(), Effect.EXTINGUISH, 1);
    }
}
