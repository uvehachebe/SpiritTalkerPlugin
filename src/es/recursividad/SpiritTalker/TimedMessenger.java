package es.recursividad.SpiritTalker;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TimedMessenger extends BukkitRunnable {
    
    private final JavaPlugin plugin;
    private String message;
    
    public TimedMessenger (JavaPlugin plugin, String message) {
        this.plugin = plugin;
        this.message = message;
    }

    @Override
    public void run() {
        plugin.getServer().broadcastMessage(message);
    }
    
}
