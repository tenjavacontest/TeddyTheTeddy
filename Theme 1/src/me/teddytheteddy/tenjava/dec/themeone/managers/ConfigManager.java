package me.teddytheteddy.tenjava.dec.themeone.managers;

import me.teddytheteddy.tenjava.dec.themeone.Main;
import me.teddytheteddy.tenjava.dec.themeone.objects.Poem;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author TeddyTheTeddy
 */
public class ConfigManager {

    //Set config item
    /**
     * Sets a String in the config
     *
     * @param config
     * @param path
     * @param res
     */
    public void set(FileConfiguration config, String path, String res) {
        config.set(path, res);
    }

    /**
     * Sets a integer in the config
     *
     * @param config
     * @param path
     * @param res
     */
    public void set(FileConfiguration config, String path, Integer res) {
        config.set(path, res);
    }

    /**
     * Sets a boolean in the config
     *
     * @param config
     * @param path
     * @param res
     */
    public void set(FileConfiguration config, String path, boolean res) {
        config.set(path, res);
    }

    //Fetch config item
    /**
     * Fetches a string from the config
     *
     * @param config
     * @param path
     * @return String
     */
    public String getString(FileConfiguration config, String path) {
        String res;
        res = config.getString(path);
        return res;
    }

    /**
     * Gets an integer from the config
     *
     * @param config
     * @param path
     * @return Integer
     */
    public Integer getInt(FileConfiguration config, String path) {
        Integer res;
        res = config.getInt(path);
        return res;
    }

    /**
     * Gets a boolean from the config
     *
     * @param config
     * @param path
     * @return boolean
     */
    public boolean getBoolean(FileConfiguration config, String path) {
        boolean res;
        res = config.getBoolean(path);
        return res;
    }

    /**
     * Fetches the poems
     *
     * @param config
     * @param pl
     */
    public void getPoems(FileConfiguration config, Main pl) {
        for (String key : config.getConfigurationSection("poems").getKeys(false)) {
            if (this.getBoolean(config, "poems." + key + ".enabled")) {
                Main.Poems.add(new Poem(key, getString(config, "poems." + key + ".author"), getString(config, "poems." + key + ".title"), getString(config, "poems." + key + ".path"), true, pl));
            }
        }
    }
}
