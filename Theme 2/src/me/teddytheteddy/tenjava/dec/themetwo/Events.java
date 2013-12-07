package me.teddytheteddy.tenjava.dec.themetwo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author TeddyTheTeddy
 */
public class Events implements Listener {

    /**
     * Hides any players who are Iron Man
     *
     * @param event
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (String pName : Main.IronMen.keySet()) {
            Player p = Bukkit.getPlayer(pName);
            event.getPlayer().hidePlayer(p);
        }
    }

    /**
     * Called when a player quits, to remove the golem
     *
     * @param event
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (Main.IronMen.containsKey(event.getPlayer().getName())) {
            Entity e = Main.IronMen.get(event.getPlayer().getName());
            e.remove();
            event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
            Main.IronMen.remove(event.getPlayer().getName());
        }
        if (Main.Morphing.containsKey(event.getPlayer().getName())) {
            Main.Morphing.remove(event.getPlayer().getName());
        }
    }

    /**
     * called when a player is kicked to remove the golem
     *
     * @param event
     */
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (Main.IronMen.containsKey(event.getPlayer().getName())) {
            Entity e = Main.IronMen.get(event.getPlayer().getName());
            e.remove();
            event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
            Main.IronMen.remove(event.getPlayer().getName());
        }
        if (Main.Morphing.containsKey(event.getPlayer().getName())) {
            Main.Morphing.remove(event.getPlayer().getName());
        }
    }

    /**
     * Means that players un morph after death
     *
     * @param event
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.showPlayer(event.getEntity());
        }
        String prefix = "&7[&cIron Man&7] &r";
        event.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&fYou are no longer &cIron Man&f because you died!"));
        event.getEntity().removePotionEffect(PotionEffectType.INVISIBILITY);
        Main.IronMen.get(event.getEntity().getName()).remove();
        Main.IronMen.remove(event.getEntity().getName());
        Main.Morphing.remove(event.getEntity().getName());
    }

    /**
     * Stops players from moving whilst morphing
     *
     * @param event
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location l1 = new Location(event.getFrom().getWorld(), event.getFrom().getX(), event.getFrom().getY(), event.getFrom().getZ());
        Location l2 = new Location(event.getFrom().getWorld(), event.getTo().getX(), event.getTo().getY(), event.getTo().getZ());
        if (Main.Morphing.containsKey(event.getPlayer().getName()) && !l1.equals(l2)) {
            event.getPlayer().teleport(Main.Morphing.get(event.getPlayer().getName()));
        }
        if (Main.IronMen.containsKey(event.getPlayer().getName())) {
            IronGolem g = (IronGolem) Main.IronMen.get(event.getPlayer().getName());
            g.setTarget(event.getPlayer());
            g.teleport(event.getPlayer().getLocation());
        }
    }

    /**
     * To stop suffocation damage... :/
     *
     * @param event
     */
    @EventHandler
    public void onGolemDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof IronGolem && !event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
            event.setCancelled(true);
        } else {
        }
    }

    /**
     * To stop owners from killing the golem Also means owner gets hurt and
     * killed
     *
     * @param event
     */
    @EventHandler
    public void onGolemHitByOwner(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof IronGolem) {
            Player d = (Player) event.getDamager();
            Entity e = event.getEntity();
            if (e.equals(Main.IronMen.get(d.getName()))) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof IronGolem) {
            for (String key : Main.IronMen.keySet()) {
                if (event.getEntity().equals(Main.IronMen.get(key))) {
                    Bukkit.getPlayer(key).damage(event.getDamage());
                }
            }

            event.setDamage(0);
        }
    }
}
