package aldrigos.mc.worldedit.commands;

import aldrigos.mc.worldedit.*;
import cn.nukkit.Player;
import cn.nukkit.command.*;
import cn.nukkit.math.Vector3;

public class ExpandCmd extends Command {
    private final WorldEdit api;

    public ExpandCmd(WorldEditPlugin p){
        super("/expand");
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
            Messages.PARAM_MISS.send(player, "//expand <num> <up|down|north|south|east|west>");
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

        Vector3 p1y, p2y, p1z, p2z, p1x, p2x;
        if(sel.P1.y < sel.P2.y){
            p1y = sel.P1;
            p2y = sel.P2;
        } else {
            p1y = sel.P2;
            p2y = sel.P2;
        }

        if(sel.P1.z < sel.P2.z){
            p1z = sel.P1;
            p2z = sel.P2;
        } else {
            p1z = sel.P2;
            p2z = sel.P1;
        }

        if(sel.P1.x < sel.P2.x){
            p1x = sel.P1;
            p2x = sel.P2;
        } else {
            p1x = sel.P2;
            p2x = sel.P1;
        }

        switch (args[1].toLowerCase()){
            case "up":
                p2y.y += num;
                break;
            case "down":
                p1y.y += num;
                break;
            case "north":
                p1z.z -= num;
                break;
            case "south":
                p2z.z += num;
                break;
            case "east":
                p2x.x += num;
                break;
            case "west":
                p1x.x -= num;
                break;
            default:
                Messages.PARAM_INVALID.send(player, args[1]);
                return false;
        }

        Messages.REGION_EXPANDED.send(player);
        return true;
    }
}
