package net.omni.puzzle.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.puzzles.PuzzleLevel;
import org.bukkit.entity.Player;

public class PuzzlePlaceholder extends PlaceholderExpansion {
    private final PuzzlePlugin plugin;

    public PuzzlePlaceholder(PuzzlePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "puzzle";
    }

    @Override
    public String getAuthor() {
        return "omni";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null)
            return "0";

        if (params.equals("current")) {
            PuzzleLevel level = plugin.getPuzzleHandler().getLevel(player);

            if (level == null)
                return "0";
            else
                return String.valueOf(level.getLevel());
        }

        return null;
    }
}
