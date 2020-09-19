package net.omni.puzzle.util;

import net.omni.puzzle.PuzzlePlugin;

public class MessagesUtil {
    private final PuzzlePlugin plugin;
    private String prefix;
    private String setLevel;
    private String checkpoint;
    private String errorCheckpoint;
    private String errorContinue;
    private String errorSetLvl;
    private String errorExit;

    public MessagesUtil(PuzzlePlugin plugin) {
        this.plugin = plugin;
    }

    public void load() {
        this.prefix = plugin.getMessages().getString("prefix");
        this.setLevel = plugin.getMessages().getString("setlevel");
        this.checkpoint = plugin.getMessages().getString("checkpoint");

        this.errorCheckpoint = plugin.getMessages().getString("errorCheckpoint");
        this.errorContinue = plugin.getMessages().getString("errorContinue");
        this.errorSetLvl = plugin.getMessages().getString("errorSetLvl");
        this.errorExit = plugin.getMessages().getString("errorExit");
    }

    public String getErrorExit() {
        return errorExit;
    }

    public String getErrorSetLvl() {
        return errorSetLvl;
    }

    public String getErrorContinue() {
        return errorContinue;
    }

    public String getErrorCheckpoint() {
        return errorCheckpoint;
    }

    public String getCheckpoint(String player, String level) {
        return checkpoint.replace("{player}", player).replace("{level}", level);
    }

    public String getSetLevel(int level) {
        return setLevel.replace("{level}", String.valueOf(level));
    }

    public String getPrefix() {
        return prefix;
    }
}
