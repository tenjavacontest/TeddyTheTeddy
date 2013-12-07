package me.teddytheteddy.tenjava.dec.themeone.events;

import java.util.ArrayList;
import java.util.List;
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

    public RequestPoem(Main pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onBookshelfClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.BOOKSHELF)) {
                if (event.getPlayer().hasPermission("poetry.get")) {
                    if (!event.getPlayer().isSneaking()) {
                        Random r = new Random();
                        int toPick = r.nextInt(Main.Poems.size());
                        Poem poemToUse = Main.Poems.get(toPick);

                        List<String> FinalText = new ArrayList<String>();

                        if (poemToUse.getText().size() >= 9) {
                            StringBuilder textSB = new StringBuilder();

                            int lines = 0;
                            for (String s : poemToUse.getText()) {
                                textSB.append(s + "\n");
                                lines++;
                                if (lines == 9) {
                                    FinalText.add(textSB.toString());
                                    lines = 0;
                                    textSB = new StringBuilder();
                                }
                            }
                            FinalText.add(textSB.toString());
                        } else {
                            StringBuilder textSB = new StringBuilder();
                            for (String s : poemToUse.getText()) {
                                textSB.append(s + "\n");
                            }
                            FinalText.add(textSB.toString());
                        }

                        String[] text = new String[FinalText.size()];
                        int itteration = 0;
                        for (String s : FinalText) {
                            text[itteration] = s;
                            itteration++;
                        }

                        ItemStack bookToGive = new ItemStack(Material.WRITTEN_BOOK, 1);
                        ItemMeta bookToGiveIM = bookToGive.getItemMeta();
                        BookMeta bookToGiveBM = (BookMeta) bookToGiveIM;
                        bookToGiveBM.setTitle(poemToUse.getTitle());
                        bookToGiveBM.setAuthor(poemToUse.getAuthor());
                        bookToGiveBM.addPage(text);
                        bookToGive.setItemMeta(bookToGiveBM);

                        event.getPlayer().getInventory().addItem(bookToGive);
                        event.getPlayer().updateInventory();
                    }
                }

            } else {
            }
        } else {
        }
    }

}
