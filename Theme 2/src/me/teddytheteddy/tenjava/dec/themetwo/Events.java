package me.teddytheteddy.tenjava.dec.themetwo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
     *
     * @param event
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(Main.IronMen.containsKey(event.getPlayer().getName())){
            Entity e = Main.IronMen.get(event.getPlayer().getName());
            e.remove();
            Main.IronMen.remove(event.getPlayer().getName());
        }
        if(Main.Morphing.containsKey(event.getPlayer().getName())){
            Main.Morphing.remove(event.getPlayer().getName());
        }
    }

    /**
     *
     * @param event
     */
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if(Main.IronMen.containsKey(event.getPlayer().getName())){
            Entity e = Main.IronMen.get(event.getPlayer().getName());
            e.remove();
            Main.IronMen.remove(event.getPlayer().getName());
        }
        if(Main.Morphing.containsKey(event.getPlayer().getName())){
            Main.Morphing.remove(event.getPlayer().getName());
        }
    }

    /**
     *
     * @param event
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        
    }

    /**
     * Stops players from moving whilst morphing
     *
     * @param event
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (Main.Morphing.containsKey(event.getPlayer().getName())) {
            event.getPlayer().teleport(Main.Morphing.get(event.getPlayer().getName()));
        } else {
        }
    }

}
