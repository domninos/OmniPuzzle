package net.omni.puzzle.commands.puzzle.sub;

import net.omni.puzzle.PuzzlePlugin;
import net.omni.puzzle.commands.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLvlSubCommand extends SubCommand {
    public SetLvlSubCommand(PuzzlePlugin plugin) {
        super(plugin, false);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length != 2)
            return false;

        Player player = (Player) sender;

        int level;

        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            plugin.sendMessage(sender, "&cInvalid number,");
            return true;
        }

        plugin.getPuzzleHandler().setLevel(player, level);
        plugin.sendMessage(player, plugin.getMessagesUtil().getSetLevel(level));
        return true;
    }

    @Override
    public String getCommand() {
        return "setlvl";
    }

    @Override
    public String getUsage() {
        return "/puzzle setlvl <level>";
    }

    @Override
    public String getPermission() {
        return "puzzlegame.setlvl";
    }
}
