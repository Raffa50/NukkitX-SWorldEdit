package aldrigos.mc.worldedit.listeners;

import aldrigos.mc.worldedit.*;
import cn.nukkit.event.*;
import cn.nukkit.event.player.PlayerTeleportEvent;

import java.util.Map;

public class PlayerWorldChangeListener implements Listener {
    private final Map<Long, Cuboid> selection;

    public PlayerWorldChangeListener(WorldEditPlugin p){
        selection = p.getSelection();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWorldChange(PlayerTeleportEvent e){
        selection.remove(e.getPlayer().getId());
    }
}
