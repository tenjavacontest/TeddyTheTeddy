package me.teddytheteddy.tenjava.dec.themeone.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author TeddyTheTeddy
 */
public class RequestPoem implements Listener {

    @EventHandler
    public void onBookshelfClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.BOOKSHELF)) {
                if (event.getPlayer().isSneaking()) {
                    ItemStack bookToGive = new ItemStack(Material.WRITTEN_BOOK, 1);
                    ItemMeta bookToGiveIM = bookToGive.getItemMeta();
                    BookMeta bookToGiveBM = (BookMeta) bookToGiveIM;
                    bookToGiveBM.setTitle("Test Book");
                    bookToGiveBM.setAuthor("Jim Bob Jimmy");
                    bookToGiveBM.addPage("Test 1", "Test 2");
                    bookToGive.setItemMeta(bookToGiveBM);

                    event.getPlayer().getInventory().addItem(bookToGive);
                    event.getPlayer().updateInventory();
                }
            } else {
            }
        } else {
        }
    }

}
