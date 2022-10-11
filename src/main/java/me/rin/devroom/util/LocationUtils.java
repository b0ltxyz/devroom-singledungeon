package me.rin.devroom.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtils {

    public static String serialize(Location location) {
        String world = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        return world + ":" + x + ":" + y + ":" + z;
    }

    public static Location deserialize(String locationText) {
        String[] splitLoc = locationText.split(":");

        World world = Bukkit.getWorld(splitLoc[0]);
        double x = Double.parseDouble(splitLoc[1]);
        double y = Double.parseDouble(splitLoc[2]);
        double z = Double.parseDouble(splitLoc[3]);
        return new Location(world, x, y, z);
    }
}