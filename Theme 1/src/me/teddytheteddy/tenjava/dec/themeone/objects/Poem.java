package me.teddytheteddy.tenjava.dec.themeone.objects;

import java.util.ArrayList;

/**
 *
 * @author TeddyTheTeddy
 */
public class Poem {
    private int id;
    private String title;
    private ArrayList<String> text;
    private boolean enabled;
    
    public Poem(int id, String title, ArrayList<String> text, boolean enabled){
        this.id = id;
        this.title = title;
        this.text = text;
        this.enabled = enabled;
    }
    
    public int getID(){
        return this.id;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public ArrayList<String> getText(){
        return this.text;
    }
    
    public boolean getEnabled(){
        return this.enabled;
    }
}
