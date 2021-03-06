package com.bgmp.medievo.commands.chat;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.queues.replyqueue;

public class message implements CommandExecutor {

    private final main plugin;

    public message(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("message")) {
                Player msgsender = (Player) sender;

                if (args.length > 1) {

                    Player msgreceiver = Bukkit.getServer().getPlayer(args[0]);

                    if(msgreceiver.isOnline()) { // if msgreceiver is online

                        StringBuilder builder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i] + " ");
                        }

                        String message = builder.toString();

                        msgreceiver.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET
                                + "" + ChatColor.GRAY + "] From " + ChatColor.RESET +
                                msgsender.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);

                        msgsender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET + "" + ChatColor.GRAY + "] To " + ChatColor.RESET +
                                msgreceiver.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);

                        // Map msgreceiver to msgsender in the reply queue, so that receiver may send a message to msgsender in the future
                            replyqueue.put(msgreceiver, msgsender); // Map msgreceiver to its msgsender
                    } else {
                        msgsender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Nobody to send a message to!");
                    }

                } else {
                    msgsender.sendMessage(ChatColor.RED + "Command Syntax: /message {player} {message}");
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(noconsole);
        }

        return true;
    }
}