package com.bgmp.medievo.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class queues {

    public static HashMap<String, String> invitequeue;
    public static HashMap<String, Boolean> confirmqueue;
    public static HashMap<Player, Player> replyqueue;
    public static HashMap<Player, Location> locationqueue;

    public static void loadqueues() {
         invitequeue = new HashMap<>();
         confirmqueue = new HashMap<>();
         replyqueue = new HashMap<>();
         locationqueue = new HashMap<>();
    }
}
