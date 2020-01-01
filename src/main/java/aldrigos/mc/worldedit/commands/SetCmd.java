package aldrigos.mc.worldedit.commands;

import aldrigos.mc.worldedit.*;
import cn.nukkit.Player;
import cn.nukkit.command.*;

public class SetCmd extends Command {
    private final WorldEdit api;

    public SetCmd(WorldEditPlugin p){
        super("/set");
        api = p.getApi();
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.isPlayer()){
            Messages.PLAYERCMD.send(sender);
            return false;
        }
        var player = (Player)sender;

        if(args.length == 0){
            Messages.PARAM_MISS.send(sender, "//set <blockId>");
            return false;
        }

        if(!sender.hasPermission("we.edit")){
            Messages.NO_PERM.send(sender, "we.edit");
            return false;
        }

        var sel = api.getSelection(player.getId());
        if(sel == null || !sel.isComplete()) {
            Messages.SELECTION_INVALID.send(sender);
            return false;
        }

        int blockId;
        try {
            blockId = Integer.parseUnsignedInt(args[0]);
        }catch(NumberFormatException e){
            Messages.PARAM_INVALID.send(sender, args[0]);
            return false;
        }

        api.set(player.getLevel().getId(), sel, blockId);

        return true;
    }
}
