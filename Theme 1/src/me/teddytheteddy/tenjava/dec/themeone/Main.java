package me.teddytheteddy.tenjava.dec.themeone;

import java.io.File;
import me.teddytheteddy.tenjava.dec.themeone.managers.ConfigManager;
import me.teddytheteddy.tenjava.dec.themeone.managers.DefaultPoemManager;
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
    
    //Class Instances/ Referances
    private ConfigManager cm = new ConfigManager();
    
    //onEnable
    @Override
    public void onEnable(){
        
        //Configuration File
        configFile = new File(this.getDataFolder(), "config.yml");
        configFile.mkdir();
        
        config = this.getConfig();
        
        //Copying Poems in .jar
        new DefaultPoemManager().movePoems(this.getDataFolder());
        
        if(!config.contains("poems")){
            cm.set(config, "poems.1.author", "Tirzah Goodwin");
            cm.set(config, "poems.1.title", "Evening");
            cm.set(config, "poems.1.path", "Poems/Haiku-Evening.txt");
            cm.set(config, "poems.1.enabled", true);
        }
        
        this.saveConfig();
        
        //Loading Poems
        
        //
        
    }
    
    //onDisable
    @Override
    public void onDisable(){
        
    }
}
