package aldrigos.mc.worldedit.commands;

import aldrigos.mc.worldedit.*;
import cn.nukkit.Player;
import cn.nukkit.command.*;

public class ShiftCmd extends Command {
    private final WorldEdit api;

    public ShiftCmd(WorldEditPlugin p){
        super("/shift");
        api = p.getApi();
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.isPlayer()){
            Messages.PLAYERCMD.send(sender);
            return false;
        }
        var player = (Player)sender;
        var sel = api.getSelection(player.getId());

        if(args.length < 2){
            Messages.PARAM_MISS.send(player, "//shift <num> <up|down|north|south|east|west>");
            return false;
        }

        int num;
        try {
            num = Integer.parseUnsignedInt(args[0]);
        }catch(NumberFormatException e){
            Messages.PARAM_INVALID.send(player, args[0]);
            return false;
        }

        if(sel == null || !sel.isComplete()){
            Messages.SELECTION_INVALID.send(sender);
            return false;
        }

        switch (args[1].toLowerCase()){
            case "up":
                sel.P2.y += num;
                sel.P1.y += num;
                break;
            case "down":
                sel.P2.y -= num;
                sel.P1.y -= num;
                break;
            case "east":
                sel.P1.x += num;
                sel.P2.x += num;
                break;
            case "west":
                sel.P1.x -= num;
                sel.P2.x -= num;
                break;
            case "south":
                sel.P1.z += num;
                sel.P2.z += num;
                break;
            case "north":
                sel.P1.z -= num;
                sel.P2.z -= num;
                break;
        }

        Messages.REGION_SHIFTED.send(player);
        return true;
    }
}
