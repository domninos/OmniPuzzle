package net.omni.puzzle.puzzles;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PuzzleLocation {
    private final Location location;
    private final World world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public PuzzleLocation(World world, double x, double y, double z, float yaw, float pitch) {
        Validate.notNull(world);

        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;

        this.location = new Location(world, x, y, z, yaw, pitch);
    }

    public void teleport(Player player) {
        if (player == null)
            return;

        Validate.notNull(world);

        player.teleport(location);
    }

    public Location getLocation() {
        return location;
    }

    public World getWorld() {
        return world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public String toString() {
        return world.getName() + "," + x + "," + y + "," + z + "," + yaw + "," + pitch;
    }
}

