package me.ryandw11.kdebug.commands;

import me.ryandw11.kdebug.ColorUtil;
import org.kakara.core.Kakara;
import org.kakara.core.command.Command;
import org.kakara.core.command.CommandSender;
import org.kakara.core.mod.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModsCommand extends org.kakara.core.mod.game.ModCommand {
    public ModsCommand(Mod mod){
        super(Collections.singleton("modlist"), "List the mods installed.", mod, "mods");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        int page = 0;
        List<Mod> commandList = new ArrayList<>(Kakara.getModManager().getLoadedMods()).stream()
                .sorted(Comparator.comparing(Mod::getName)).collect(Collectors.toList());
        if(strings.length == 1){
            try{
                page = Integer.parseInt(strings[0]);
                page--;
            }catch (NumberFormatException ex){
                commandSender.sendMessage(ColorUtil.RED + "Invalid page number!");
                return;
            }
            if(page < 0){
                commandSender.sendMessage(ColorUtil.RED + "Invalid page number!");
                return;
            }
            if(page * 5 > commandList.size()){
                commandSender.sendMessage(ColorUtil.RED + "Invalid page number!");
                return;
            }
        }

        commandSender.sendMessage("=========[MOD LIST #" + (page + 1) + "]==========");
        int numOfCmds = Kakara.getModManager().getLoadedMods().size();
        for(int i = page * 5; i < (page * 5) + 5; i++){
            if(i >= numOfCmds) break;
            Mod mod = commandList.get(i);
            commandSender.sendMessage(" - " + mod.getName() + " (" + mod.getVersion() + ")");
        }
        if((page * 5) + 5 < commandList.size()){
            commandSender.sendMessage("View more mods by doing /mods " + (page + 2) + ".");
        }

    }
}
