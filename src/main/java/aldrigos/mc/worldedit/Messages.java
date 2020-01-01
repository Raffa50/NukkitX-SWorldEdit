package aldrigos.mc.worldedit;

import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public enum Messages {
    NO_PERM(TextFormat.RED+"[WE]No permission for %s"),
    PARAM_MISS(TextFormat.RED+"[WE]Missing parameter. Usage: %s"),
    PARAM_INVALID(TextFormat.RED+"[WE]Invalid parameter %s"),
    SELECTION_INVALID(TextFormat.RED+"[WE]Invalid selection"),
    SET_P1(TextFormat.DARK_PURPLE+"[WE]First position set %s"),
    SET_P2(TextFormat.DARK_PURPLE+"[WE]Second position set %s"),
    REGION_EXPANDED(TextFormat.DARK_PURPLE+"[WE]Region expanded"),
    REGION_SHIFTED(TextFormat.DARK_PURPLE+"[WE]Region shift"),
    PLAYERCMD(TextFormat.RED+"[WE]This is a player command");

    private final String message;

    Messages(String msg){
        message = msg + TextFormat.RESET;
    }

    @Override
    public String toString(){
        return message;
    }

    public void send(CommandSender to, Object... args){
        to.sendMessage(String.format(this.toString(), args));
    }
}
