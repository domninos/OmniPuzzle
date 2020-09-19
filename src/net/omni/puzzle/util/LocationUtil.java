package net.omni.puzzle.util;

import net.omni.puzzle.puzzles.PuzzleLocation;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class LocationUtil {

    public static PuzzleLocation getPuzzleLocation(String string) {
        String[] split = string.split(",");

        if (split.length <= 3)
            return null;

        World world = Bukkit.getWorld(split[0]);

        Validate.notNull(world);

        double x;
        double y;
        double z;
        float yaw;
        float pitch;

        try {
            x = Double.parseDouble(split[1]);
            y = Double.parseDouble(split[2]);
            z = Double.parseDouble(split[3]);

            yaw = Float.parseFloat(split[4]);
            pitch = Float.parseFloat(split[5]);
        } catch (NumberFormatException e) {
            System.out.println("Something went wrong parsing puzzle location. " + e.getMessage());
            return null;
        }

        return new PuzzleLocation(world, x, y, z, yaw, pitch);
    }

    public static PuzzleLocation getPuzzleLocation(Player player) {
        if (player == null)
            return null;

        Location location = player.getLocation();

        return new PuzzleLocation(player.getWorld(), location.getX(), location.getY(), location.getZ(),
                location.getYaw(), location.getPitch());
    }
}
