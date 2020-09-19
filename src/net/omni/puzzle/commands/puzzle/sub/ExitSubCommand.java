package net.omni.puzzle.commands.puzzle.sub;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.commands.SubCommand;
import net.omni.puzzle.puzzles.PuzzleLevel;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExitSubCommand extends SubCommand {
    public ExitSubCommand(PuzzlePlugin plugin) {
        super(plugin, false);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length != 1)
            return false;

        Player player = (Player) sender;

        PuzzleLevel level = plugin.getPuzzleHandler().getLevel(player);

        if (level == null) {
            plugin.sendMessage(player, plugin.getMessagesUtil().getErrorExit());
            return true;
        }

        player.teleport(player.getWorld().getSpawnLocation());
        plugin.sendMessage(player, "&aYou have been teleported to spawn.");
        return true;
    }

    @Override
    public String getCommand() {
        return "exit";
    }

    @Override
    public String getUsage() {
        return "/puzzle exit";
    }

    @Override
    public String getPermission() {
        return null;
    }
}
