package org.herreurobeat.noPhantomsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {

    String supportedVersion = "1.16 - 1.19";
    String pluginVersion = "1.0";

    @Override
    public void onEnable() {
        System.out.println("noPhantomsPlugin for version " + supportedVersion + " enabled!");

        //Register events so we can actually listen for creature spawn events
        getServer().getPluginManager().registerEvents(this, this);
    }

    //Handle command execution
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { //handle calling command methods

        //Since this is a small plugin I will handle every command in this file
        switch (cmd.getName()) { //handle execution of each command
            case "nophantomsinfo":
                noPhantomsInfoCommand(sender, args);
                break;
            default:
                break;
        }

        return false;
    }


    /**
     * Handles the nophantomsinfo command
     * @param sender The player who used the command
     * @param args The arguments the user provided
     */
    public void noPhantomsInfoCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player) { //check if sender is a player
            Player p = (Player) sender; //convert to player
            if (!p.getWorld().getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK))
                return; //check gamerule and stop if send command feedback is false
        }

        sender.sendMessage("noPhantomsPlugin by 3urobeat v" + pluginVersion + " \n----\nPrevents Phantoms from spawning.\n----\nhttps://github.com/HerrEurobeat/noPhantomsPlugin");
    }


    //Catch creature spawn event and block it if it is a phantom
    @EventHandler
    public void spawnEvent(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType() == EntityType.PHANTOM) {
            event.setCancelled(true);
        }
    }
}