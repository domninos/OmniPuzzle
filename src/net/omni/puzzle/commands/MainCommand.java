package net.omni.puzzle.commands;

import net.omni.puzzle.PuzzlePlugin;
import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MainCommand implements CommandExecutor {
    protected final PuzzlePlugin plugin;

    private final List<SubCommand> subCommands;
    private final List<String> subs;
    private final boolean consoleFriendly;

    public MainCommand(PuzzlePlugin plugin, List<SubCommand> subCommands, boolean consoleFriendly) {
        this.plugin = plugin;

        this.subCommands = subCommands;
        this.consoleFriendly = consoleFriendly;

        if (!subCommands.isEmpty()) {
            subCommands.forEach(subCommand -> {
                Validate.notNull(subCommand.getCommand());
                Validate.notNull(subCommand.getUsage());

                if (subCommand.isConsoleFriendly() && !consoleFriendly)
                    plugin.sendConsole("&cMain command needs to be console friendly to make " +
                            "sub commands be console friendly.");
            });

            subs = subCommands.stream().map(SubCommand::getCommand).map(String::toLowerCase).
                    collect(Collectors.toList());
        } else
            subs = new ArrayList<>();
    }

    public abstract void execute(CommandSender sender, String label, String[] args);

    public abstract String getMainCommand();

    public abstract String getHelp(CommandSender sender);

    public abstract String getPermission();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            if (!consoleFriendly) {
                sendMessage(sender, "&cOnly players can use this command.");
                return true;
            }
        }

        if (getPermission() != null)
            if (!sender.hasPermission(getPermission())) {
                sendMessage(sender, "&cYou lack the permission " + getPermission() + "!");
                return true;
            }

        if (args.length == 0) {
            execute(sender, s, args);
            return true;
        }

        if (subs.isEmpty())
            return true;

        if (!subs.contains(args[0].toLowerCase())) {
            plugin.sendMessage(sender, getHelp(sender));
            return true;
        }

        for (SubCommand sub : subCommands) {
            if (args[0].equalsIgnoreCase(sub.getCommand())) {
                if (sub.isConsoleFriendly() && !consoleFriendly) {
                    plugin.sendConsole("&c/" + getMainCommand()
                            + " needs to be console friendly to make sub commands be console friendly.");
                    continue;
                }

                if (!(sender instanceof Player)) { // sender is not player {
                    if (!sub.isConsoleFriendly()) { // sub commmand is not console friendly
                        sendMessage(sender, "&cOnly players can use this command.");
                        continue;
                    }
                } else { // sender is player
                    if (sub.getPermission() != null)
                        if (!sender.hasPermission(sub.getPermission())) {
                            sendMessage(sender, "&cYou lack the permission " + sub.getPermission() + "!");
                            continue;
                        }
                }

                try {
                    if (!sub.execute(sender, s, args))
                        sendMessage(sender, "&cUsage: " + sub.getUsage());
                } catch (Exception exc) {
                    sendMessage(sender, "Could not execute " + sub.getUsage());
                }
            }
        }

        return true;
    }

    public void register() {
        PluginCommand pluginCommand = plugin.getCommand(getMainCommand());

        if (pluginCommand != null)
            pluginCommand.setExecutor(this);
    }

    public void sendMessage(CommandSender sender, String text) {
        plugin.sendMessage(sender, text);
    }
}

