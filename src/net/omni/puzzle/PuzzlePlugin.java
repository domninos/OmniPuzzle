package net.omni.puzzle;

import net.omni.puzzle.commands.puzzle.PuzzleCommand;
import net.omni.puzzle.config.PuzzleConfig;
import net.omni.puzzle.puzzles.PuzzleHandler;
import net.omni.puzzle.util.MessagesUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzlePlugin extends JavaPlugin {

    // TODO error messages

    private PuzzleConfig messages;
    private PuzzleConfig players;

    private MessagesUtil messagesUtil;

    private PuzzleHandler puzzleHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.messages = new PuzzleConfig(this, "messages");
        this.messagesUtil = new MessagesUtil(this);

        messagesUtil.load();

        this.players = new PuzzleConfig(this, "players.yml");

        this.puzzleHandler = new PuzzleHandler(this);
        puzzleHandler.load();

        new PuzzleCommand(this).register();

        sendConsole("&aSuccessfully enabled OmniPuzzle v-" + getDescription().getVersion());
    }

    @Override
    public void onDisable() {

        puzzleHandler.flush();

        sendConsole("&aSuccessfully disabled OmniPuzzle");
    }

    public void sendConsole(String message) {
        sendMessage(Bukkit.getConsoleSender(), message);
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(translate(getMessagesUtil().getPrefix() + " " + message));
    }

    public String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public PuzzleHandler getPuzzleHandler() {
        return puzzleHandler;
    }

    public PuzzleConfig getMessages() {
        return messages;
    }

    public MessagesUtil getMessagesUtil() {
        return messagesUtil;
    }

    public PuzzleConfig getPlayers() {
        return players;
    }
}
