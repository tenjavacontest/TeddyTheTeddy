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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author <b>TeddyTheTeddy</b>
 *
 */
public class Main extends JavaPlugin {

    //Variables
    public File configFile;
    public FileConfiguration config = null;
    public static ArrayList<Poem> Poems = new ArrayList<Poem>();

    //Class Instances/ Referances
    private ConfigManager cm = new ConfigManager();

    /**
     * Executed when plugin is enabled.<br />
     * - Checks the config<br />
     * - Enables all events
     */
    @Override
    public void onEnable() {
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
            //Haiku 1
            cm.set(config, "poems.1.author", "Tirzah Goodwin");
            cm.set(config, "poems.1.title", "Evening");
            cm.set(config, "poems.1.path", "Poems/Haiku-Evening.txt");
            cm.set(config, "poems.1.enabled", true);
            //Haiku 2
            cm.set(config, "poems.2.author", "Maggie Holt");
            cm.set(config, "poems.2.title", "Death");
            cm.set(config, "poems.2.path", "Poems/Haiku-Death.txt");
            cm.set(config, "poems.2.enabled", true);
        }

        this.saveConfig();

        //Loading Poems
        cm.getPoems(config, this);

        //Registering Event
        Bukkit.getPluginManager().registerEvents(new RequestPoem(this), this);
    }

    /**
     * Executed when the plugin is disabled.<br />
     * - Nothing
     */
    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            String prefix = "☺7[☺6Poetry☺7]☺r ";
            if (cmd.getName().equalsIgnoreCase("poetry")) {
                Player p = (Player) sender;
                if (p.hasPermission("poetry.admin")) {
                    if (args.length == 0) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('☺', prefix + "☺fWelcome to Poetry! This plugin was originally developed as a competition entry for ☺6ten.java☺f! Anyway, your not really here for a small history lesson so without any more reading, lets go!"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('☺', prefix + "☺fJust start by doing ☺7/☺6poetry help☺f and you shall begin your journey!"));
                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("help")) {
                            ItemStack bookToGive = new ItemStack(Material.WRITTEN_BOOK, 1);
                            ItemMeta bookToGiveIM = bookToGive.getItemMeta();
                            BookMeta bookToGiveBM = (BookMeta) bookToGiveIM;
                            bookToGiveBM.setTitle(ChatColor.translateAlternateColorCodes('☺', prefix + "Help Guide"));
                            bookToGiveBM.setAuthor(ChatColor.translateAlternateColorCodes('☺', "☺6Teddy☺7The☺6Teddy"));
                            bookToGiveBM.addPage(ChatColor.translateAlternateColorCodes('☺', "☺7Welcome to ☺6Poetry☺7!\n\n☺7This book is your essential guide to the ☺6Poetry☺7 plugin. It will tell you everything you need to know, to be able use the plugin to it's best potential.\n\n\n\n\n\n☺0Please Turn Over"), 
                                    ChatColor.translateAlternateColorCodes('☺', "☺6Commands\n\n☺7We use a minimal amount of commands to make using the plugin as easy as possible. But here is a list of the commands with what they do.\n☺7/☺6poetry help\n  ☺7Gives you this book\n☺7/☺6poetry reload\n  ☺7Reloads all poems"),
                                    ChatColor.translateAlternateColorCodes('☺', "☺6Configuration Part 1\n\n☺7We try to make our configuration as simple as possible, so all you really need to do is put a poem in to the plugin's Poems folder as a '☺6.txt☺7' file and then fill out some simple configuration options on the next page."),
                                    ChatColor.translateAlternateColorCodes('☺', "☺6Configuration Part 2\n\n☺7In the plugin's config.yml you will need to fill out:\n☺0poems:\n  '1':\n   author: Someone\n   title: Poem title\n   path: Poems/poem.txt\n   enabled: true\n☺7Replacing different variables when needed."),
                                    ChatColor.translateAlternateColorCodes('☺', "☺4WARNINGS\n\n☺7Do not have more than one poem with the same key, for example you cant have two poems called '1'"));
                            bookToGive.setItemMeta(bookToGiveBM);

                            p.getInventory().addItem(bookToGive);
                            p.updateInventory();
                        }else if(args[0].equalsIgnoreCase("reload")){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('☺', prefix + "☺fReloading all poems!"));
                            Main.Poems.clear();
                            cm.getPoems(config, this);
                        }else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('☺', prefix + "☺fWelcome to Poetry! This plugin was originally developed as a competition entry for ☺6ten.java☺f! Anyway, your not really here for a small history lesson so without any more reading, lets go!"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('☺', prefix + "☺fJust start by doing ☺7/☺6poetry help☺f and you shall begin your journey!"));
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('☺', prefix + "☺fYou do not have permission to run this command!"));
                }
            }
            return false;
        } else {

            return true;
        }
    }
}
