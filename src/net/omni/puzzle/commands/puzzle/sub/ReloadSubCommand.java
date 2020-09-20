package net.omni.puzzle.commands.puzzle.sub;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.commands.SubCommand;
import org.bukkit.command.CommandSender;

public class ReloadSubCommand extends SubCommand {
    public ReloadSubCommand(PuzzlePlugin plugin) {
        super(plugin, true);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length != 1)
            return false;

        plugin.getMessages().reload();
        plugin.getMessagesUtil().load();

        plugin.reloadConfig();
        plugin.getPuzzleHandler().load();

        plugin.getPlayers().reload();

        plugin.sendMessage(sender, "&aSuccessfully reloaded plugin files.");
        return true;
    }

    @Override
    public String getCommand() {
        return "reload";
    }

    @Override
    public String getUsage() {
        return "/puzzle reload";
    }

    @Override
    public String getPermission() {
        return "puzzle.reload";
    }
}
