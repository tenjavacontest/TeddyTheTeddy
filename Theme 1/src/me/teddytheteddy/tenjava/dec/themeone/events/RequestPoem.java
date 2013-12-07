package me.teddytheteddy.tenjava.dec.themeone.events;

import java.util.Random;
import me.teddytheteddy.tenjava.dec.themeone.Main;
import me.teddytheteddy.tenjava.dec.themeone.objects.Poem;
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

    public Main pl;
    
    public RequestPoem(Main pl){
        this.pl = pl;
    }

    @EventHandler
    public void onBookshelfClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.BOOKSHELF)) {
                if (!event.getPlayer().isSneaking()) {
                    Random r = new Random();
                    int toPick = r.nextInt(Main.Poems.size());
                    Poem poemToUse = Main.Poems.get(toPick);
                    
                    //String[] text = (String[]) poemToUse.getText().toArray();
                    
                    ItemStack bookToGive = new ItemStack(Material.WRITTEN_BOOK, 1);
                    ItemMeta bookToGiveIM = bookToGive.getItemMeta();
                    BookMeta bookToGiveBM = (BookMeta) bookToGiveIM;
                    bookToGiveBM.setTitle(poemToUse.getTitle());
                    bookToGiveBM.setAuthor(poemToUse.getAuthor());
                    bookToGiveBM.addPage("Hello World");
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
