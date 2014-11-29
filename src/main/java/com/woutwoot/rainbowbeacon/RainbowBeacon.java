package com.woutwoot.rainbowbeacon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wout on 29/11/2014.
 */
public class RainbowBeacon extends JavaPlugin implements CommandExecutor, Listener {

    private List<RainbowBlock> rainbowBlocks = new ArrayList<>();
    private List<Creator> creators = new ArrayList<>();
    private static RainbowBeacon instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("rbeacon").setExecutor(this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new RainbowTask(), 20L, 40L);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p;
        if (sender instanceof Player) {
            p = (Player) sender;
        } else {
            sender.sendMessage("You have to be a player.");
            return false;
        }

        if (cmd.getName().equals("rbeacon")) {
            if (sender.hasPermission("rainbowbeacon.use")) {
                if (toggleCreator(p)) {
                    sender.sendMessage("You can now right click any block to add or remove it from the rainbowblocks.");
                } else {
                    sender.sendMessage("Stopped selection.");
                }
            } else {
                sender.sendMessage("No permissions. You need rainbowbeacon.use");
            }
        }
        return false;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (creators.contains(event.getPlayer())) {
                toggleBlock(event.getClickedBlock().getLocation());
                event.getPlayer().sendMessage(ChatColor.AQUA + "Done!");
            }
        }
    }

    private void toggleBlock(Location location) {
        if (this.rainbowBlocks.contains(location)) {
            removeBlock(location);
        } else {
            addBlock(location);
        }
    }

    public void addBlock(Location l) {
        this.rainbowBlocks.add(new RainbowBlock(l));
    }

    public void removeBlock(Location l) {
        this.rainbowBlocks.remove(l);
    }

    public boolean toggleCreator(Player player) {
        if (this.creators.contains(player)) {
            this.creators.remove(player);
            return false;
        } else {
            this.creators.add(new Creator(player));
            return true;
        }
    }

    public static RainbowBeacon getInstance() {
        return instance;
    }

    public List<RainbowBlock> getRainbowBlocks() {
        return rainbowBlocks;
    }
}
