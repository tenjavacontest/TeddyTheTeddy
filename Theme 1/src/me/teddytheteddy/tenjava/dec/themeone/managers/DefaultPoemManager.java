package me.teddytheteddy.tenjava.dec.themeone.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.teddytheteddy.tenjava.dec.themeone.Main;
import net.minecraft.util.org.apache.commons.io.IOUtils;

/**
 *
 * @author TeddyTheTeddy
 */
public class DefaultPoemManager {

    public void movePoems(File df) {
        try {
            File pf = new File(df.getPath(), "Poems/");
            pf.mkdirs();

            File p1f = new File(df.getPath(), "Poems/Haiku-Evening.txt");
            File p2f = new File(df.getPath(), "Poems/Haiku-Death.txt");
            if (!p1f.exists() || !p2f.exists()) {
                try {
                    p1f.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(DefaultPoemManager.class.getName()).log(Level.SEVERE, null, ex);
                }

                OutputStream o1 = new FileOutputStream(df.getPath() + "/Poems/Haiku-Evening.txt");
                InputStream p1 = Main.class.getResourceAsStream("/Haiku-Evening.txt");
                OutputStream o2 = new FileOutputStream(df.getPath() + "/Poems/Haiku-Death.txt");
                InputStream p2 = Main.class.getResourceAsStream("/Haiku-Death.txt");
                try {
                    IOUtils.copy(p1, o1);
                    IOUtils.copy(p2, o2);
                } catch (IOException ex) {
                    Logger.getLogger(DefaultPoemManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DefaultPoemManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
