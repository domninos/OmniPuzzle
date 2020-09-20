package net.omni.puzzle.puzzles;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PuzzleHandler {
    private final Set<PuzzleLevel> levels = new HashSet<>();
    private final PuzzlePlugin plugin;

    public PuzzleHandler(PuzzlePlugin plugin) {
        this.plugin = plugin;
    }

    public void load() {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("locations");

        if (section == null) {
            plugin.sendConsole("&cNo locations found.");
            return;
        }

        flush();

        for (String key : section.getKeys(false)) {
            if (key == null)
                continue;

            int level;

            try {
                level = Integer.parseInt(key);
            } catch (NumberFormatException e) {
                plugin.sendConsole("&cLevel not recognized.");
                continue;
            }

            String worldString = section.getString(key + ".world");

            if (worldString == null) {
                plugin.sendConsole("&cWorld of " + key + " not found");
                continue;
            }

            World world = Bukkit.getWorld(worldString);

            if (world == null) {
                plugin.sendConsole("&cWorld of " + key + " not found.");
                continue;
            }

            double x = section.getDouble(key + ".x");
            double y = section.getDouble(key + ".y");
            double z = section.getDouble(key + ".z");
            float yaw = (float) section.getDouble(key + ".yaw");
            float pitch = (float) section.getDouble(key + ".pitch");

            PuzzleLocation puzzleLocation = new PuzzleLocation(world, x, y, z, yaw, pitch);

            levels.add(new PuzzleLevel(level, puzzleLocation));
            plugin.sendConsole("&aLoaded puzzle level " + level);
        }

        plugin.sendConsole("&aSuccessfully loaded puzzle locations.");
    }

    public void setLevel(Player player, int level) {
        if (player == null || level <= 0)
            return;

        PuzzleLevel puzzle = getLevel(level);
        PuzzleLocation puzzleLocation = LocationUtil.getPuzzleLocation(player);

        if (puzzle != null) {
            puzzle.setPuzzleLocation(puzzleLocation);
            plugin.sendMessage(player, plugin.getMessagesUtil().getErrorSetLvl());
        } else
            levels.add(new PuzzleLevel(level, puzzleLocation));

        String path = "locations." + level + ".";

        plugin.getConfig().set(path + "world", puzzleLocation.getWorld().getName());
        plugin.getConfig().set(path + "x", puzzleLocation.getX());
        plugin.getConfig().set(path + "y", puzzleLocation.getY());
        plugin.getConfig().set(path + "z", puzzleLocation.getZ());
        plugin.getConfig().set(path + "yaw", puzzleLocation.getYaw());
        plugin.getConfig().set(path + "pitch", puzzleLocation.getPitch());
        plugin.saveConfig();

        plugin.sendMessage(player, "&aSuccessfully set puzzle location. (" + puzzleLocation + ")");
    }

    public PuzzleLevel getLevel(int level) {
        return levels.stream().filter(puzzle -> puzzle.getLevel() == level).findFirst().orElse(null);
    }

    public PuzzleLevel getLevel(Player player) {
        if (player == null)
            return null;

        int level = plugin.getPlayers().getInt("players." + player.getName());

        if (level <= 0)
            return null;

        return getLevel(level);
    }

    public void flush() {
        levels.clear();
    }
}
