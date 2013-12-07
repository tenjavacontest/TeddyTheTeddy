package me.teddytheteddy.tenjava.dec.themeone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.teddytheteddy.tenjava.dec.themeone.events.RequestPoem;
import me.teddytheteddy.tenjava.dec.themeone.managers.ConfigManager;
import me.teddytheteddy.tenjava.dec.themeone.managers.DefaultPoemManager;
import me.teddytheteddy.tenjava.dec.themeone.objects.Poem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author TeddyTheTeddy
 *
 */
public class Main extends JavaPlugin {

    //Variables
    public File configFile;
    public FileConfiguration config = null;
    public ArrayList<Poem> Poems = new ArrayList<Poem>();

    //Class Instances/ Referances
    private ConfigManager cm = new ConfigManager();
    private Util u = new Util();

    //onEnable
    @Override
    public void onEnable() {
        
        u.sys("Doing things with the config");
        //Configuration File
        configFile = new File(this.getDataFolder(), "config.yml");
        try {
            configFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        config = this.getConfig();

        //Copying Poems in .jar
        new DefaultPoemManager().movePoems(this.getDataFolder());
        
        if (!config.contains("poems")) {
            cm.set(config, "poems.1.author", "Tirzah Goodwin");
            cm.set(config, "poems.1.title", "Evening");
            cm.set(config, "poems.1.path", "Poems/Haiku-Evening.txt");
            cm.set(config, "poems.1.enabled", true);
        }
        
        this.saveConfig();

        //Loading Poems
        cm.getPoems(config, Poems);
        
        //Registering Event
        Bukkit.getPluginManager().registerEvents(new RequestPoem(), this);
    }

    //onDisable
    @Override
    public void onDisable() {
        
    }
}
