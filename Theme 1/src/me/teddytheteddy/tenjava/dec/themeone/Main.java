package me.teddytheteddy.tenjava.dec.themeone;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author TeddyTheTeddy
 * 
 */
public class Main extends JavaPlugin{
    //Variables
    public File configFile;
    public FileConfiguration config = null;
    
    //onEnable
    @Override
    public void onEnable(){
        
        //Configuration File
        configFile = new File(this.getDataFolder(), "config.yml");
        configFile.mkdir();
        
        config = this.getConfig();
        this.saveConfig();
        
    }
    
    //onDisable
    @Override
    public void onDisable(){
        
    }
}
