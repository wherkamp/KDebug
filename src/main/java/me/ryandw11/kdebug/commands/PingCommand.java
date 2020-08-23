package me.ryandw11.kdebug.commands;

import org.kakara.core.command.CommandSender;
import org.kakara.core.mod.Mod;
import org.kakara.core.mod.game.ModCommand;
import org.kakara.core.server.Server;

import java.util.Collections;

public class PingCommand extends ModCommand {
    public PingCommand(Mod mod){
        super(Collections.singleton("pong"), "Test command.", mod, "ping");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        commandSender.sendMessage("Pong!");
    }
}
