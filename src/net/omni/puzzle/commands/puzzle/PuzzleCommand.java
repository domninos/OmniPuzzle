package net.omni.puzzle.commands.puzzle;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.commands.MainCommand;
import net.omni.puzzle.commands.puzzle.sub.CheckpointSubCommand;
import net.omni.puzzle.commands.puzzle.sub.ContinueSubCommand;
import net.omni.puzzle.commands.puzzle.sub.ExitSubCommand;
import net.omni.puzzle.commands.puzzle.sub.SetLvlSubCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class PuzzleCommand extends MainCommand {
    public PuzzleCommand(PuzzlePlugin plugin) {
        super(plugin, Arrays.asList(
                new SetLvlSubCommand(plugin), new CheckpointSubCommand(plugin),
                new ContinueSubCommand(plugin), new ExitSubCommand(plugin)
        ), false);
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        plugin.sendMessage(sender, getHelp(sender));
    }

    @Override
    public String getMainCommand() {
        return "puzzle";
    }

    @Override
    public String getHelp(CommandSender sender) {
        return "&l&fPuzzle Help\n" +
                "&e/puzzle setlvl <level> &7» Sets level location for the puzzle.\n" +
                "&e/puzzle checkpoint <player> <level> &7» Saves the checkpoint of a player.\n" +
                "&e/puzzle continue <player> &7» Lets the player continue his puzzle map.\n" +
                "&e/puzzle exit <player> &7» Spawns the player to the world spawn.";
    }

    @Override
    public String getPermission() {
        return "puzzlegame.use";
    }
}
