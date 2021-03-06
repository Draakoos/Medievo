package com.bgmp.medievo.commands.punishments;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.appeal;
import static com.bgmp.medievo.util.genericmessages.appealsmail;
import static com.bgmp.medievo.util.genericmessages.noperms;

public class kick implements CommandExecutor {

    private final main plugin;

    public kick(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kick") && sender.hasPermission("medievo.kick")) {
            Player kickedon = Bukkit.getServer().getPlayer(args[0]);
            if (args.length >= 2) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                String msg = ChatColor.GOLD + sb.toString();
                if (kickedon != null) {
                    if (sender instanceof Player) {
                        Player source = (Player) sender;
                        kickedon.kickPlayer(ChatColor.RED + "You have been kicked!" + "\n" + ChatColor.GOLD + "\n" + "» " + msg + "\n\n" + appeal + "\n" + appealsmail);
                        Bukkit.broadcastMessage(source.getDisplayName() + ChatColor.GOLD + " » " + "Kicked" + " » " + ChatColor.RESET + kickedon.getDisplayName() + ChatColor.GOLD + " » " + msg);
                    } else {
                        String console = ChatColor.GOLD + "❖" + ChatColor.DARK_AQUA + "Console";
                        kickedon.kickPlayer(ChatColor.RED + "You have been kicked!" + "\n" + ChatColor.GOLD + "\n" + "» " + msg + "\n\n" + appeal + "\n" + appealsmail);
                        Bukkit.broadcastMessage(console + ChatColor.GOLD + " » " + "Kicked" + " » " + ChatColor.RESET + kickedon.getDisplayName() + ChatColor.GOLD + " » " + msg);
                    }
                } else {
                    if (sender instanceof Player) {
                        Player source = (Player) sender;
                        Bukkit.broadcastMessage(source.getDisplayName() + ChatColor.GOLD + " » " + "Kicked" + " » " + ChatColor.DARK_AQUA + args[0] + ChatColor.GOLD + " » " + msg);
                    } else {
                        String console = ChatColor.GOLD + "❖" + ChatColor.DARK_AQUA + "Console";
                        Bukkit.broadcastMessage(console + ChatColor.GOLD + " » " + "Kicked" + " » " + ChatColor.DARK_AQUA + args[0] + ChatColor.GOLD + " » " + msg);
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Command syntax: /kick {player} {reason}");
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
