package me.reed.miniboosters.commands;

import me.reed.miniboosters.MiniBoosters;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExpAlert implements CommandExecutor {
    private final MiniBoosters plugin;

    public ExpAlert(MiniBoosters plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player)  {
            Player player = (Player) sender;
            if (player.isOp() || player.hasPermission("miniboosters.command.expalerts")) {
                String statusMessage;
                ChatColor statusColor;
                if (plugin.playerExpAlertEnabled(player.getUniqueId())) { // set false
                    plugin.setPlayerExpAlertEnabled(player.getUniqueId(), false);
                    statusMessage = "inactive";
                    statusColor = ChatColor.RED;
                } else { // set true
                    plugin.setPlayerExpAlertEnabled(player.getUniqueId(), true);
                    statusMessage = "active";
                    statusColor = ChatColor.GREEN;
                }
                player.sendMessage(ChatColor.DARK_AQUA + "Exp pickup alerts now " + statusColor + statusMessage + "!");
            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command! " + ChatColor.DARK_RED + "miniboosters.command.expalerts");
            }
        } else {
            plugin.getLogger().info("You must be in game to activate exp pickup alerts!");
        }
        return true;
    }
}

