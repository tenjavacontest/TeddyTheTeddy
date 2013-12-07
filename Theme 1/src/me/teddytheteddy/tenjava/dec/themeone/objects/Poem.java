package me.teddytheteddy.tenjava.dec.themeone.objects;


/**
 *
 * @author TeddyTheTeddy
 */
public class Poem {
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
    
    public String getText(){
        return this.path;
    }
    
    public boolean getEnabled(){
        return this.enabled;
    }
}
