package net.omni.puzzle.puzzles;

import org.bukkit.entity.Player;

public class PuzzleLevel {
    private final int level;
    private PuzzleLocation puzzleLocation;

    public PuzzleLevel(int level, PuzzleLocation puzzleLocation) {
        this.level = level;
        this.puzzleLocation = puzzleLocation;
    }

    public void setPuzzleLocation(PuzzleLocation puzzleLocation) {
        this.puzzleLocation = puzzleLocation;
    }

    public void teleport(Player player) {
        puzzleLocation.teleport(player);
    }

    public int getLevel() {
        return level;
    }
}
