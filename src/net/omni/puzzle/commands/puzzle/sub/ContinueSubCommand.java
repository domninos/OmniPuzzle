package net.omni.puzzle.commands.puzzle.sub;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.commands.SubCommand;
import net.omni.puzzle.puzzles.PuzzleLevel;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ContinueSubCommand extends SubCommand {
    public ContinueSubCommand(PuzzlePlugin plugin) {
        super(plugin, true);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length != 2)
            return false;

        Player player = Bukkit.getPlayer(args[1]);

        if (player == null) {
            plugin.sendMessage(sender, "&cPlayer not found.");
            return true;
        }

        PuzzleLevel level = plugin.getPuzzleHandler().getLevel(player);

        if (level == null) {
            plugin.sendMessage(sender, "&c" + player.getName() + " has not yet begun their journey.");
            plugin.sendMessage(player, plugin.getMessagesUtil().getErrorContinue());
            return true;
        }

        level.teleport(player);
        plugin.sendMessage(player, "&aYou have been teleported to your last checkpoint.");
        return true;
    }

    @Override
    public String getCommand() {
        return "continue";
    }

    @Override
    public String getUsage() {
        return "/puzzle continue <player>";
    }

    @Override
    public String getPermission() {
        return "puzzlegame.admin";
    }
}
