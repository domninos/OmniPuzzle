package net.omni.puzzle.commands.puzzle;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.commands.MainCommand;
import net.omni.puzzle.commands.puzzle.sub.*;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class PuzzleCommand extends MainCommand {
    public PuzzleCommand(PuzzlePlugin plugin) {
        super(plugin, Arrays.asList(
                new SetLvlSubCommand(plugin), new CheckpointSubCommand(plugin),
                new ContinueSubCommand(plugin), new ExitSubCommand(plugin),
                new ReloadSubCommand(plugin)
        ), true);
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
                "&e/puzzle exit &7» Teleports you to the world spawn.\n" +
                "&e/puzzle reload &7» Reloads plugin files.";
    }

    @Override
    public String getPermission() {
        return "puzzlegame.use";
    }
}
