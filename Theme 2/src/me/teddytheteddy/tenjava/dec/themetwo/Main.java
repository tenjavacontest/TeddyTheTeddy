package me.teddytheteddy.tenjava.dec.themetwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
     * What happens on disable
     */
    @Override
    public void onDisable() {

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
                Player p = (Player) sender;

                String prefix = "&7[&cIron Man&7] &r";
                if (p.hasPermission("ironman.be")) {
                    if (!Main.IronMen.containsKey(p.getName())) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&fYou are now morphing into &cIron Man&f!"));
                        Main.Morphing.put(p.getName(), p.getLocation());
                        
                    } else {

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
}
