package net.omni.puzzle.commands;

import net.omni.puzzle.PuzzlePlugin;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    protected final PuzzlePlugin plugin;
    private final boolean consoleFriendly;

    public SubCommand(PuzzlePlugin plugin, boolean consoleFriendly) {
        this.plugin = plugin;
        this.consoleFriendly = consoleFriendly;
    }

    public void sendMessage(CommandSender sender, String message) {
        plugin.sendMessage(sender, message);
    }

    public void sendUsage(CommandSender sender) {
        sendMessage(sender, "&cUsage: " + getUsage());
    }

    public boolean isConsoleFriendly() {
        return consoleFriendly;
    }

    public abstract boolean execute(CommandSender sender, String label, String[] args);

    public abstract String getCommand();

    public abstract String getUsage();

    public abstract String getPermission();
}