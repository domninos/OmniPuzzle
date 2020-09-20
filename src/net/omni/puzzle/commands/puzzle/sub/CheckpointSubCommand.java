package net.omni.puzzle.commands.puzzle.sub;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.commands.SubCommand;
import net.omni.puzzle.puzzles.PuzzleLevel;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckpointSubCommand extends SubCommand {
    public CheckpointSubCommand(PuzzlePlugin plugin) {
        super(plugin, true);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length != 3)
            return false;

        Player player = Bukkit.getPlayer(args[1]);

        if (player == null) {
            plugin.sendMessage(sender, "&cPlayer not found.");
            return true;
        }
        int level;

        try {
            level = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            plugin.sendMessage(sender, "&cLevel not found.");
            return true;
        }


        PuzzleLevel puzzle = plugin.getPuzzleHandler().getLevel(level);

        if (puzzle == null) {
            plugin.sendMessage(player, "&cSomething went wrong loading levels. (Error code: 1)");
            plugin.sendMessage(sender, "&cLevel not found.");
            plugin.sendConsole("&cLevel not found. (Error code: 1)");
            return true;
        }

        PuzzleLevel previousLevel = plugin.getPuzzleHandler().getLevel(player);

        if (previousLevel != null) {
            // if given level is greater than previous level
            if (level <= previousLevel.getLevel()) {
                plugin.sendMessage(player, plugin.getMessagesUtil().getErrorCheckpoint());
                plugin.sendMessage(sender, "&c" + player.getName() + " already completed this level.");
                return true;
            }
        }

        plugin.getPlayers().set("players." + player.getName(), level);
        plugin.sendMessage(sender, plugin.getMessagesUtil().getCheckpoint(player.getName(), String.valueOf(level)));
        return true;
    }

    @Override
    public String getCommand() {
        return "checkpoint";
    }

    @Override
    public String getUsage() {
        return "/puzzle checkpoint <player> <level>";
    }

    @Override
    public String getPermission() {
        return "puzzlegame.checkpoint";
    }
}
