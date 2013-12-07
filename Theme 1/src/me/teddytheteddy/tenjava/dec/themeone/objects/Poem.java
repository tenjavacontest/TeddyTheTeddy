package me.teddytheteddy.tenjava.dec.themeone.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.teddytheteddy.tenjava.dec.themeone.Main;


/**
 *
 * @author TeddyTheTeddy
 */
public class Poem {
    private Main pl = new Main();
    
    private String id;
    private String author;
    private String title;
    private String path;
    private boolean enabled;
    
    public Poem(String id, String author, String title, String path, boolean enabled){
        this.id = id;
        this.author = author;
        this.title = title;
        this.path = path;
        this.enabled = enabled;
    }
    
    public String getID(){
        return this.id;
    }
    
    public String getAuthor(){
        return this.author;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public ArrayList<String> getText(){
        ArrayList<String> res = new ArrayList<String>();
        File text = new File(pl.getDataFolder(), this.path);
        try {
            FileReader txtReader = new FileReader(text);
            BufferedReader txtBr = new BufferedReader(txtReader);
            String line = null;
            try {
                while((line = txtBr.readLine()) != null){
                    res.add(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(Poem.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Poem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public boolean getEnabled(){
        return this.enabled;
    }
}
