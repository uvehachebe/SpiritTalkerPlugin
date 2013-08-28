package es.recursividad.SpiritTalker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

public class SpiritPluginLogic extends JavaPlugin implements Listener {
    /*  Listener classes  */
    //public final ChatListener chatListener = new ChatListener();
    private static ArrayList<Hero> players = new ArrayList<Hero>();
    
    private static int spiritState = 0;
    private String nameToBeSet = "";
    
    @Override
    public void onEnable(){
        /*  Register events */
        getServer().getPluginManager().registerEvents(this, this);
        
        // Save a copy of the default config.yml if one is not there
        this.saveDefaultConfig();
    }
 
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    }
    
    @EventHandler
    public void playerLoggedIn (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String ingameName = "";
        int id = event.getPlayer().getEntityId();
        
        // Check if the data about the player is in the config.yml
        String configSaved = this.getConfig().getString(name);
        if (configSaved == null) {
            ingameName = (players.size() == 0) ? "???" : "????";
        } else {
            ingameName = configSaved;
        }
        
        // Guardar usuario en el archivo de configuracion
        this.getConfig().set(name, ingameName);
        this.saveConfig();
        
        // Cambiar el nombre del usuario
        Bukkit.getServer().dispatchCommand(event.getPlayer(),"nick " + event.getPlayer().getName() + " " + ingameName);
        players.add(new Hero(id, name, ingameName, player));
    }
    
    @EventHandler
    public void blockBreaked (BlockBreakEvent event) {
        Bukkit.broadcastMessage(event.getPlayer().getItemInHand().toString());
        event.setCancelled(true);
    }
    
    @EventHandler
    public void playerTalked (AsyncPlayerChatEvent event) {
        switch (spiritState) {
            case 0:
            case 1:
            case 2:
                break;
            case 3: /* A la espera de establecer nombres */
                event.setCancelled(true);
                Bukkit.broadcastMessage("<" + event.getPlayer().getDisplayName() + "> " + event.getMessage());
                
                /* Si en la frase se ha dicho "llamo" o "soy" */
                if (event.getMessage().toLowerCase().contains("llamo") || event.getMessage().toLowerCase().contains("soy")) {
                    boolean siguiente = false;
                    boolean encontrado = false;
                    for (String s : event.getMessage().split(" ")) {
                        if (siguiente && !encontrado) {
                            nameToBeSet = s;
                            encontrado = true;
                        }
                        
                        if (s.equalsIgnoreCase("llamo") || s.equalsIgnoreCase("soy")) {
                            siguiente = true;
                        }
                    }
                    Bukkit.broadcastMessage("<" + Spirit.NAME + "> " + "¿Así que eres " + nameToBeSet + "?");
                }
                else {
                    Bukkit.broadcastMessage("<" + Spirit.NAME + "> " + "Perdona, hace siglos que no hablaba con nadie y no estoy muy acostumbrado a vuestra jerga");
                    Bukkit.broadcastMessage("<" + Spirit.NAME + "> " + "¿Te importaria repetirlo de otra forma?");
                }
                spiritState++;
                break;
            case 4:
                /* A la espera de confirmacion del primer nombre */
                if (event.getMessage().contains("si") || event.getMessage().contains("SI") || event.getMessage().contains("Si") || event.getMessage().contains("sí") || event.getMessage().contains("Sí") || event.getMessage().contains("SÍ")) {
                    Bukkit.broadcastMessage("<" + Spirit.NAME + "> ¡" + nameToBeSet + "! un nombre precioso");
                    Bukkit.getConsoleSender().sendMessage("nick dos " + nameToBeSet);
                    nameToBeSet = "";
                    spiritState++;
                } else {
                    Bukkit.broadcastMessage("<&3" + Spirit.NAME + "&f> Perdona, te entendi mal, ¿entonces cual es tu nombre?");
                    spiritState = 3;
                }
                break;
        }
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (cmd.getName().equalsIgnoreCase("spirit")) {
		switch (spiritState) {
                    case 0:
                        ArrayList<String> messages = new ArrayList<String>();
                        messages.add("uno");
                        messages.add("dos");
                        messages.add("tres");
                        messages.add("cuatro");
                        timedMessages(messages, 25);
                        //Bukkit.broadcastMessage("<...> ¿Hola?");
                        //spiritState++;
                        break;
                    case 1:
                        Bukkit.broadcastMessage("<...> ¿Alguien puede oirme?");
                        spiritState++;
                        break;
                    case 2:
                        Bukkit.broadcastMessage("<...> UAAAAAAAAAAAAAAAAAH");
                        Bukkit.broadcastMessage("<...> Si son dos niños");
                        Bukkit.broadcastMessage("<...> Hacia siglos que no venia nadie por aqui");
                        Bukkit.broadcastMessage("<...> Por cierto, yo me llamo " + Spirit.NAME + ", ¿y vosotros?");
                        spiritState++;
                        break;
                        
                }
		return true;
	}
	return false;
    }
    
    public void timedMessages (ArrayList<String> messages, long timeout) {
        long delay = 0;
        for (String message : messages) {
            new TimedMessenger(this, message).runTaskLater(this, delay);
            delay += timeout;
        }
    }
}

/**
 * CODIGOS PARA FORMATEAR LOS MENSAJES
 * 
 *      COLORES
 * 
 * §0	Black	
 * §1	Dark Blue
 * §2	Dark Green
 * §3	Dark Aqua
 * §4	Dark Red
 * §5	Purple
 * §6	Gold
 * §7	Gray
 * §8	Dark Gray
 * §9	Blue
 * §a	Green
 * §b	Aqua
 * §c	Red
 * §d	Light Purple
 * §e	Yellow
 * §f	White
 * 
 * 
 *      FORMATO DE TEXTO
 * 
 * §k	Obfuscated
 * §l	Bold
 * §m	Strikethrough
 * §n	Underline
 * §o	Italic
 * §r	Reset
 */
