package me.teddytheteddy.tenjava.dec.themeone.managers;

import java.util.ArrayList;
import me.teddytheteddy.tenjava.dec.themeone.Main;
import me.teddytheteddy.tenjava.dec.themeone.objects.Poem;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author TeddyTheTeddy
 */
public class ConfigManager {
    
    //Set config item
    //String
    public void set(FileConfiguration config,String path, String res){
        config.set(path, res);
    }
    //Integer
    public void set(FileConfiguration config,String path, Integer res){
        config.set(path, res);
    }
    //Boolean
    public void set(FileConfiguration config,String path, boolean res){
        config.set(path, res);
    }
    
    //Fetch config item
    //String
    public String getString(FileConfiguration config, String path){
        String res;
        res = config.getString(path);
        return res;
    }
    //Integer
    public Integer getInt(FileConfiguration config, String path){
        Integer res;
        res = config.getInt(path);
        return res;
    }
    //Boolean
    public boolean getBoolean(FileConfiguration config, String path){
        boolean res;
        res = config.getBoolean(path);
        return res;
    }
    //Geting the poems
    public void getPoems(FileConfiguration config){
        for(String key : config.getConfigurationSection("poems").getKeys(false)){
            if(this.getBoolean(config, "poems." + key + ".enabled")){
                Main.Poems.add(new Poem(key, getString(config, "poems." + key + ".author"), getString(config, "poems." + key + ".title"), getString(config, "poems" + key + ".path"), true));
            }
        }
    }
}
