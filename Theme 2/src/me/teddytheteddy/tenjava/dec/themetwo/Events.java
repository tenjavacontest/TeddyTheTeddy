package me.teddytheteddy.tenjava.dec.themetwo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author TeddyTheTeddy
 */
public class Events implements Listener{
    
    /**
     * 
     */
    @EventHandler
    public void onPlayerJoin(){
        
    }
    
    /**
     * 
     */
    @EventHandler
    public void onPlayerQuit(){
        
    }
    
    /**
     * 
     */
    @EventHandler
    public void onPlayerKick(){
       
    }
    
    /**
     * 
     */
    @EventHandler
    public void onPlayerDeath(){
        
    }
    
    /**
     * Stops players from moving whilst morphing
     * @param event
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if(Main.Morphing.containsKey(event.getPlayer().getName())){
            event.getPlayer().teleport(Main.Morphing.get(event.getPlayer().getName()));
        }
    }
    
}
