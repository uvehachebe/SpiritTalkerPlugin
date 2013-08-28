package es.recursividad.SpiritTalker;

import org.bukkit.entity.Player;

public class Hero {
    private int id;
    private String playerName;
    private String ingameName;
    private Player player;
    
    public Hero (int id, String playerName, String ingameName, Player player) {
        this.id = id;
        this.playerName = playerName;
        this.ingameName = ingameName;
        this.player = player;
    }
}
