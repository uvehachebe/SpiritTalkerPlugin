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

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getIngameName() {
        return ingameName;
    }

    public void setIngameName(String ingameName) {
        this.ingameName = ingameName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
